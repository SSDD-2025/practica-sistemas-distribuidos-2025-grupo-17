package es.codeurjc.web.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.web.services.*;
import es.codeurjc.web.entities.*;

@Controller
public class MoviesController {

	private static final String MOVIES_IMAGES_FOLDER = "movies_images";
	private static final String CAST_IMAGES_FOLDER = "cast_images";

	@Autowired
	private MoviesService moviesService;
	
	@Autowired
	private CastService castService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ImageService imageService;

	@GetMapping("/")
	public String showMoviesList(Model model) {

		model.addAttribute("movies", moviesService.findAll());
		model.addAttribute("cast",castService.findAll());

		return "home_template";
	}

	@GetMapping("/cast/{id}")
	public String showCast(Model model, @PathVariable long id) {

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
	public ResponseEntity<Object> downloadCastImage(@PathVariable int id) throws MalformedURLException {

		return imageService.createResponseFromImage(CAST_IMAGES_FOLDER, id);		
	}

	@GetMapping("/movie/{id}/image")	
	public ResponseEntity<Object> downloadMovieImage(@PathVariable int id) throws MalformedURLException {

		return imageService.createResponseFromImage(MOVIES_IMAGES_FOLDER, id);		
	}

	@GetMapping("/review/new")
	public String newReviewForm(Model model) {
		return "new_review_template";
	}
	
	@PostMapping("/review/new")
	public String newReview(Model model, Review review) throws IOException {

		reviewService.save(review);

		return "movie_template";
	}

	@GetMapping("/movie/new")
	public String newMovieForm(Model model) {
		return "new_movie_template";
	}
	
	@PostMapping("/movie/new")
	public String newMovie(Model model, Movie movie, MultipartFile image) throws IOException {

		moviesService.save(movie);
		
		imageService.saveImage(MOVIES_IMAGES_FOLDER, movie.getId(), image);

		return "home_template";
	}

	@GetMapping("/cast/new")
	public String newCastForm(Model model) {
		return "new_cast_template";
	}
	
	@PostMapping("/cast/new")
	public String newCast(Model model, Cast cast, MultipartFile image) throws IOException {

		castService.save(cast);
		
		imageService.saveImage(CAST_IMAGES_FOLDER, cast.getId(), image);

		return "home_template";
	}
	/*
	@PostMapping("/post/{id}/delete")
	public String deletePost(Model model, @PathVariable long id) throws IOException {

		postService.deleteById(id);

		imageService.deleteImage(POSTS_FOLDER, id);

		return "deleted_post";
	}
		*/
}
