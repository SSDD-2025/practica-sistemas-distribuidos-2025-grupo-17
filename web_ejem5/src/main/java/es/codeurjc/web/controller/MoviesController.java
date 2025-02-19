package es.codeurjc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MoviesController {

	private static final String MOVIES_IMAGES_FOLDER = "movies_images";
	private static final String CAST_IMAGES_FOLDER = "cast_images";

	@Autowired
	private MoviesService moviesService;
	
	@Autowired
	private ImageService imageService;

	@GetMapping("/")
	public String showMoviesList(Model model, HttpSession session) {

		model.addAttribute("movies", moviesService.findAll());
		model.addAttribute("cast",castService.findAll());

		return "home_template";
	}

	@GetMapping("/cast/{id}")
	public String showMovie(Model model, @PathVariable long id) {

		Cast cast = castService.findById(id);

		model.addAttribute("cast", cast);

		return "cast_template";
	}

	@GetMapping("/movies/{id}")
	public String showMovie(Model model, @PathVariable long id) {

		Movie movie = moviesService.findById(id);

		model.addAttribute("movie", movie);

		return "movie_template";
	}

	@GetMapping("/cast/{id}/image")	
	public ResponseEntity<Object> downloadImage(@PathVariable int id) throws MalformedURLException {

		return imageService.createResponseFromImage(CAST_IMAGES_FOLDER, id);		
	}

	@GetMapping("/movie/{id}/image")	
	public ResponseEntity<Object> downloadImage(@PathVariable int id) throws MalformedURLException {

		return imageService.createResponseFromImage(MOVIES_IMAGES_FOLDER, id);		
	}
	/*
	@GetMapping("/post/new")
	public String newPostForm(Model model) {

		model.addAttribute("user", userSession.getUser());

		return "new_post";
	}
	
	@PostMapping("/post/new")
	public String newPost(Model model, Post post, MultipartFile image) throws IOException {

		postService.save(post);
		
		imageService.saveImage(POSTS_FOLDER, post.getId(), image);
		
		userSession.setUser(post.getUser());
		userSession.incNumPosts();
		
		model.addAttribute("numPosts", userSession.getNumPosts());

		return "saved_post";
	}

	@PostMapping("/post/{id}/delete")
	public String deletePost(Model model, @PathVariable long id) throws IOException {

		postService.deleteById(id);

		imageService.deleteImage(POSTS_FOLDER, id);

		return "deleted_post";
	}
		*/
}
