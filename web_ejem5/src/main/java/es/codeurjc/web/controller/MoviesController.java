package es.codeurjc.web.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import es.codeurjc.web.services.*;
import es.codeurjc.web.entities.*;
import es.codeurjc.web.repository.MoviesRepository;

@Controller
public class MoviesController {

	private static final String MOVIES_IMAGES_FOLDER = "movies_images";

	@Autowired
	private MoviesService moviesService;

	@Autowired
	private CastService castService;

    @Autowired
    private ImageService imageService;

	@Autowired
	private MoviesRepository moviesRepository;

    @GetMapping("/movies/{id}")
    public String showMovie(Model model, @PathVariable long id) {
        Optional<Movie> movie = moviesService.findById(id);
        if (!movie.isPresent()) {
            return "movieNotFound_template";
        } else{
			model.addAttribute("movie", movie);
			return "movie_template";
		}
    }

	@GetMapping("/movie/{id}/image")
	public ResponseEntity<Object> downloadMovieImage(@PathVariable int id) throws MalformedURLException {
		return imageService.createResponseFromImage(MOVIES_IMAGES_FOLDER, id);
	}

	@GetMapping("/movie/new")
	public String newMovieForm(Model model) {
		model.addAttribute("cast", castService.findAll());
		return "new_movie_template";
	}

	@PostMapping("/movie/new")
	public String newMovie(Model model, @RequestParam String movieName, @RequestParam String movieArgument,
			@RequestParam int movieYear, @RequestParam(value = "movieCast", required = false) List<Long> movieCast,
			@RequestParam String movieTrailer, MultipartFile movieImage) throws IOException {
		Movie movie;
		if (movieCast != null) {
			List<Cast> castList = new ArrayList<Cast>();
			for (int i = 0; i < movieCast.size(); i++) {
				Optional<Cast> op = castService.findById(movieCast.get(i));
				if (op.isPresent()) {
					Cast cast = op.get();
					castList.add(cast);
				}
			}
			movie = new Movie(movieName, movieArgument, movieYear, castList, movieTrailer);
		} else {
			movie = new Movie(movieName, movieArgument, movieYear, null, movieTrailer);
		}
		moviesService.save(movie);

		imageService.saveImage(MOVIES_IMAGES_FOLDER, movie.getId(), movieImage);

		return "movie_created_template";
	}

    @PostMapping("/movie/{id}/delete")
    public String deleteMovie(Model model, @PathVariable long id) throws IOException {
		Optional<Movie> movie=moviesService.findById(id);
		if (movie.isPresent()){
			moviesService.deleteById(id);
        	imageService.deleteImage(MOVIES_IMAGES_FOLDER, id);
        	return "movie_deleted_template";
		} else{
			return "movieNotFound_template";
		}
    }

	@GetMapping("/movie/{id}/modify")
	public String modifyMovieForm(Model model, @PathVariable long id) {
		Optional<Movie> movie=moviesService.findById(id);
		if (movie.isPresent()){
			model.addAttribute("movie", movie);
			model.addAttribute("allCast", castService.findAll());
			return "modify_movie_template";
		} else{
			return "movieNotFound_template";
		}
	}

	@PostMapping("/movie/{id}/modify")
	public String modifyMovie(Model model, @PathVariable long id, @RequestParam String movieName,
			@RequestParam String movieArgument, @RequestParam int movieYear,
			@RequestParam(value = "movieCast", required = false) List<Long> movieCast,
			@RequestParam String movieTrailer, MultipartFile movieImage) throws IOException {
		Movie oldMovie = moviesService.findById(id).orElseThrow();
		Movie updatedMovie;
		List<Cast> castList=new ArrayList<>();
		if (movieCast!=null){
			for (int i = 0; i < movieCast.size(); i++) {
				Optional<Cast> op2 = castService.findById(movieCast.get(i));
				if (op2.isPresent()) {
					Cast cast = op2.get();
					castList.add(cast);
				}
			}
			updatedMovie=new Movie(movieName, movieArgument, movieYear, castList, movieTrailer);
		} else{
			updatedMovie=new Movie(movieName, movieArgument, movieYear, null, movieTrailer);
		}
		updatedMovie.setId(id);
		oldMovie.getReviews().forEach(c -> updatedMovie.addReview(c));
		moviesRepository.save(updatedMovie);

		imageService.saveImage(MOVIES_IMAGES_FOLDER, updatedMovie.getId(), movieImage);

		model.addAttribute("movie", moviesService.findById(id));

		return "movie_modified_template";

	}
}