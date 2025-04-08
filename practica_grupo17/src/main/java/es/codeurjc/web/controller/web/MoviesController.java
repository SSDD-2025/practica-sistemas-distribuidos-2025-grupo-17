package es.codeurjc.web.controller.web;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import java.util.NoSuchElementException;

import es.codeurjc.web.services.*;
import es.codeurjc.web.dto.movie.MovieDTO;
import es.codeurjc.web.entities.*;
import es.codeurjc.web.mapper.MovieMapper;

@Controller
public class MoviesController {

	@Autowired
	private MoviesService moviesService;

	@Autowired
	private CastService castService;

	@Autowired
	private MovieMapper movieMapper;

	@GetMapping("/movies/{id}")
	public String showMovie(Model model, @PathVariable long id) {
		try {
			MovieDTO movieDTO = moviesService.findById(id);
			model.addAttribute("movie", movieDTO);
			return "movie_template";
		} catch (NoSuchElementException e) {
			return "movieNotFound_template";
		}
	}

	@GetMapping("/movies/{id}/image")
	public ResponseEntity<Object> downloadMovieImage(@PathVariable int id) throws SQLException, IOException {
		Resource movieImage;
		try {
			movieImage = new InputStreamResource(moviesService.getMovieImage(id));
		} catch (Exception e) {
			ClassPathResource resource = new ClassPathResource("static/logo.png");
			byte[] imageBytes = resource.getInputStream().readAllBytes();
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(imageBytes);
		}
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(movieImage);
	}

	@GetMapping("/movies/new")
	public String newMovieForm(Model model) {
		model.addAttribute("cast", castService.findAll());
		return "new_or_modify_movie_template";
	}

	@PostMapping("/movies/new")
	public String newMovie(Model model, @RequestParam String movieName, @RequestParam String movieArgument,
			@RequestParam int movieYear, @RequestParam(value = "movieCast", required = false) List<Long> movieCast,
			@RequestParam String movieTrailer, MultipartFile movieImage) throws IOException {

		if (movieName == null || movieName.trim().isEmpty()) {
			model.addAttribute("error", "El título de la película es obligatorio");
			model.addAttribute("cast", castService.findAll());
			return "new_or_modify_movie_template";
		}
		moviesService.save(
				movieMapper.toCreateMovieRequest(
						moviesService.createMovie(movieName, movieArgument, movieYear, movieCast, movieTrailer)),
				movieImage);

		return "movie_created_template";
	}

	@PostMapping("/movies/{id}/delete")
	public String deleteMovie(Model model, @PathVariable long id) throws IOException {
		try {
			MovieDTO movieDTO = moviesService.deleteById(id);
			model.addAttribute("movie", movieDTO);
			return "movie_deleted_template";
		} catch (NoSuchElementException e) {
			return "movieNotFound_template";
		}
	}

	@GetMapping("/movies/{id}/modify")
	public String modifyMovieForm(Model model, @PathVariable long id) {
		try {
			MovieDTO movieDTO = moviesService.findById(id);
			model.addAttribute("movie", movieDTO);
			model.addAttribute("allCast", castService.findAll());
			if (movieMapper.toDomain(movieDTO).getMovieImage() != null) {
				model.addAttribute("currentImageUrl", "/movie/" + id + "/image");
			}
			return "new_or_modify_movie_template";
		} catch (NoSuchElementException e) {
			return "movieNotFound_template";
		}
	}

	@PostMapping("/movies/{id}/modify")
	public String modifyMovie(Model model, @PathVariable long id,
			@RequestParam String movieName,
			@RequestParam String movieArgument,
			@RequestParam int movieYear,
			@RequestParam(value = "movieCast", required = false) List<Long> movieCast,
			@RequestParam String movieTrailer,
			@RequestParam(required = false) MultipartFile movieImage)
			throws IOException, SQLException {
		try {
			MovieDTO oldMovie = moviesService.findById(id);
			Blob oldMovieImage = movieMapper.toDomain(oldMovie).getMovieImage();
			Movie updatedMovie = moviesService.createMovie(movieName, movieArgument, movieYear, movieCast,
					movieTrailer);
			updatedMovie.setId(id);
			updatedMovie.setReviews(movieMapper.toDomain(oldMovie).getReviews());
			if (movieCast == null || movieCast.isEmpty()) 
				updatedMovie.setCast(movieMapper.toDomain(oldMovie).getCast());
			if (movieImage == null || movieImage.isEmpty()) {
				updatedMovie.setMovieImage(oldMovieImage);
				moviesService.save(movieMapper.toCreateMovieRequest(updatedMovie));
			} else {
				moviesService.save(movieMapper.toCreateMovieRequest(updatedMovie), movieImage);
			}
			return "movie_modified_template";
		} catch (Exception e) {
			return "movieNotFound_template";
		}
	}

}