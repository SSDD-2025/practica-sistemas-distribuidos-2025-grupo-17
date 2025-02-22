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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import es.codeurjc.web.services.*;
import es.codeurjc.web.entities.*;

@Controller
public class MoviesController {

	private static final String MOVIES_IMAGES_FOLDER = "movies_images";

	@Autowired
	private MoviesService moviesService;
	
	@Autowired
	private CastService castService;

	@Autowired
	private ImageService imageService;

	@GetMapping("/movies/{id}")
	public String showMovie(Model model, @PathVariable long id) {

		Movie movie = moviesService.findById(id);

		model.addAttribute("movie", movie);

		return "movie_template";
	}

	@GetMapping("/movie/{id}/image")	
	public ResponseEntity<Object> downloadMovieImage(@PathVariable int id) throws MalformedURLException {

		return imageService.createResponseFromImage(MOVIES_IMAGES_FOLDER, id);		
	}

	@GetMapping("/movie/new")
	public String newMovieForm(Model model) {
		return "new_movie_template";
	}
	
	@PostMapping("/movie/new")
	public String newMovie(Model model, @RequestParam String movieName, @RequestParam String movieArgument,@RequestParam int movieYear,@RequestParam List<Cast> movieCast, @RequestParam String movieTrailer, MultipartFile image) throws IOException {

		Movie movie=new Movie(movieName,movieArgument,movieYear,movieCast,movieTrailer);
		moviesService.save(movie);
		
		imageService.saveImage(MOVIES_IMAGES_FOLDER, movie.getId(), image);

		model.addAttribute("movies", moviesService.findAll());
		model.addAttribute("cast",castService.findAll());

		return "home_template";
	}

	@PostMapping("/movie/{id}/delete")
	public String deleteMovie(Model model, @PathVariable long id) throws IOException {

		moviesService.deleteById(id);

		imageService.deleteImage(MOVIES_IMAGES_FOLDER, id);

		model.addAttribute("movies", moviesService.findAll());
		model.addAttribute("cast",castService.findAll());

		return "home_template";
	}

	@GetMapping("/movie/{id}/modify")
	public String modifyMovieForm(Model model, @PathVariable long id) {
		Movie movie=moviesService.findById(id);
		model.addAttribute("movie", movie);
		return "modify_movie_template";
	}
	
	@PostMapping("/movie/{id}/modify")
	public String modifyMovie(Model model, @PathVariable long id, @RequestParam String movieName, @RequestParam String movieArgument,@RequestParam int movieYear,@RequestParam List<Cast> movieCast, MultipartFile image) throws IOException {

		Movie movie=moviesService.findById(id);
		movie.setArgument(movieArgument);
		movie.setCast(movieCast);
		movie.setName(movieName);
		movie.setYear(movieYear);
		
		imageService.deleteImage(MOVIES_IMAGES_FOLDER, movie.getId());
		imageService.saveImage(MOVIES_IMAGES_FOLDER, movie.getId(), image);

		model.addAttribute("movie", movie);

		return "movie_template";
	}
}
