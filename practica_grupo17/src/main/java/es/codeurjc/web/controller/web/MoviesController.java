package es.codeurjc.web.controller.web;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.util.List;

import es.codeurjc.web.services.*;
import es.codeurjc.web.entities.*;

@Controller
public class MoviesController {

	@Autowired
	private MoviesService moviesService;

	@Autowired
	private CastService castService;

	@GetMapping("/movies/{id}")
	public String showMovie(Model model, @PathVariable long id) {
		Optional<Movie> movie = moviesService.findById(id);
		if (!movie.isPresent()) {
			return "movieNotFound_template";
		} else {
			Movie mov = movie.get();
			model.addAttribute("movie", mov);
			return "movie_template";
		}
	}

	@GetMapping("/movie/{id}/image")
	public ResponseEntity<Object> downloadMovieImage(@PathVariable int id) throws SQLException {

		Optional<Movie> op = moviesService.findById(id);

		if (op.isPresent() && op.get().getMovieImage() != null) {

			Blob image = op.get().getMovieImage();
			Resource file = new InputStreamResource(image.getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(image.length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/movie/new")
	public String newMovieForm(Model model) {
		model.addAttribute("cast", castService.findAll());
		return "new_or_modify_movie_template";
	}

	@PostMapping("/movie/new")
	public String newMovie(Model model, @RequestParam String movieName, @RequestParam String movieArgument,
			@RequestParam int movieYear, @RequestParam(value = "movieCast", required = false) List<Long> movieCast,
			@RequestParam String movieTrailer, MultipartFile movieImage) throws IOException {

		if (movieName == null || movieName.trim().isEmpty()) {
			model.addAttribute("error", "El título de la película es obligatorio");
			model.addAttribute("cast", castService.findAll());
			return "new_or_modify_movie_template";
		}
		moviesService.save(moviesService.createMovie(movieName, movieArgument, movieYear, movieCast, movieTrailer),
				movieImage);

		return "movie_created_template";
	}

	@PostMapping("/movie/{id}/delete")
	public String deleteMovie(Model model, @PathVariable long id) throws IOException {
		Optional<Movie> movie = moviesService.findById(id);
		if (movie.isPresent()) {
			Movie mov = movie.get();
			moviesService.removeCast(mov);
			moviesService.deleteById(id);
			return "movie_deleted_template";
		} else {
			return "movieNotFound_template";
		}
	}

	@GetMapping("/movie/{id}/modify")
	public String modifyMovieForm(Model model, @PathVariable long id) {
		Optional<Movie> movie = moviesService.findById(id);
		if (movie.isPresent()) {
			Movie mov = movie.get();
			model.addAttribute("movie", mov);
			model.addAttribute("allCast", castService.findAll());
	
			if (mov.getMovieImage() != null) {
				model.addAttribute("currentImageUrl", "/movie/" + id + "/image");
			}
	
			return "new_or_modify_movie_template";
		} else {
			return "movieNotFound_template";
		}
	}

	@PostMapping("/movie/{id}/modify")
	public String modifyMovie(Model model, @PathVariable long id,
							  @RequestParam String movieName,
							  @RequestParam String movieArgument,
							  @RequestParam int movieYear,
							  @RequestParam(value = "movieCast", required = false) List<Long> movieCast,
							  @RequestParam String movieTrailer,
							  @RequestParam(required = false) MultipartFile movieImage)
			throws IOException, SQLException {
	
		Optional<Movie> op = moviesService.findById(id);
		if (op.isPresent()) {
			Movie oldMovie = op.get();
			Blob oldMovieImage = oldMovie.getMovieImage();
	
			Movie updatedMovie = moviesService.createMovie(movieName, movieArgument, movieYear, movieCast, movieTrailer);
			updatedMovie.setId(id);
			oldMovie.getReviews().forEach(updatedMovie::addReview);
	
			
			if (movieCast == null || movieCast.isEmpty()) {
				
				updatedMovie.setCast(oldMovie.getCast());
			}
	
			
			if (movieImage == null || movieImage.isEmpty()) {
				updatedMovie.setMovieImage(oldMovieImage);
				moviesService.save(updatedMovie); 
			} else {
				moviesService.save(updatedMovie, movieImage);
			}
	
			return "movie_modified_template";
		} else {
			return "movieNotFound_template";
		}
	}
	
}