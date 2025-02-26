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

import java.util.ArrayList;
import java.util.List;

import es.codeurjc.web.services.*;
import es.codeurjc.web.entities.*;

@Controller
public class MoviesController {

	private static final String MOVIES_IMAGES_FOLDER = "movies_images";

	@Autowired
	private MoviesService moviesService;
	
	@Autowired
	private ReviewService reviewService;

	@Autowired
	private CastService castService;

	@Autowired
	private ImageService imageService;

	@GetMapping("/movies/{id}")
	public String showMovie(Model model, @PathVariable long id) {

		Movie movie = moviesService.findById(id);

		model.addAttribute("movie", movie);
		model.addAttribute("movieReviews", reviewService.findByMovie(movie));

		return "movie_template";
	}

	@GetMapping("/movie/{id}/image")	
	public ResponseEntity<Object> downloadMovieImage(@PathVariable int id) throws MalformedURLException {

		return imageService.createResponseFromImage(MOVIES_IMAGES_FOLDER, id);		
	}

	@GetMapping("/movie/new")
	public String newMovieForm(Model model) {
		model.addAttribute("cast",castService.findAll());
		return "new_movie_template";
	}
	
	@PostMapping("/movie/new")
	public String newMovie(Model model, @RequestParam String movieName, @RequestParam String movieArgument,@RequestParam int movieYear,@RequestParam(value = "movieCast", required = false) List<Long> movieCast, @RequestParam String movieTrailer, MultipartFile movieImage) throws IOException {
		Movie movie;
		if (movieCast!=null){
			List<Cast> castList=new ArrayList<Cast>();
			for (int i=0;i<movieCast.size();i++){
				castList.add(castService.findById(movieCast.get(i)));
			}
			movie=new Movie(movieName,movieArgument,movieYear,castList,movieTrailer);
		} else{
			movie=new Movie(movieName,movieArgument,movieYear,null,movieTrailer);
		}
		moviesService.save(movie);
		
		imageService.saveImage(MOVIES_IMAGES_FOLDER, movie.getId(), movieImage);

		return "movie_created_template";
	}

	@PostMapping("/movie/{id}/delete")
	public String deleteMovie(Model model, @PathVariable long id) throws IOException {

		moviesService.deleteById(id);

		imageService.deleteImage(MOVIES_IMAGES_FOLDER, id);

		return "movie_deleted_template";
	}

	@GetMapping("/movie/{id}/modify")
	public String modifyMovieForm(Model model, @PathVariable long id) {
		Movie movie=moviesService.findById(id);
		model.addAttribute("movie", movie);
		model.addAttribute("allCast", castService.findAll());
		return "modify_movie_template";
	}
	
	@PostMapping("/movie/{id}/modify")
	public String modifyMovie(Model model, @PathVariable long id, @RequestParam String movieName, @RequestParam String movieArgument,@RequestParam int movieYear,@RequestParam(value = "movieCast", required = false) List<Long> movieCast, @RequestParam String movieTrailer, MultipartFile movieImage) throws IOException {
		Movie movie=moviesService.findById(id);
		if (movieCast!=null){
			List<Cast> castList=new ArrayList<Cast>();
			for (int i=0;i<movieCast.size();i++){
				castList.add(castService.findById(movieCast.get(i)));
			}
			movie.setCast(castList);
		} else{
			movie.setCast(null);
		}
		movie.setArgument(movieArgument);
		movie.setName(movieName);
		movie.setYear(movieYear);
		movie.setTrailer(movieTrailer);
		
		imageService.deleteImage(MOVIES_IMAGES_FOLDER, movie.getId());
		imageService.saveImage(MOVIES_IMAGES_FOLDER, movie.getId(), movieImage);

		model.addAttribute("movie", moviesService.findById(id));

		return "movie_modified_template";
	}
}
