package es.codeurjc.web.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

import es.codeurjc.web.services.*;
import jakarta.servlet.http.HttpServletRequest;
import es.codeurjc.web.entities.*;

@Controller
public class CastController {

	@Autowired
	private CastService castService;

	@Autowired
	private MoviesService moviesService;

	@GetMapping("/castList")
	public String showCastList(Model model,HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			model.addAttribute("logged", true);
		}
		model.addAttribute("cast", castService.findAll());
		return "castList_template";
	}

	@GetMapping("/cast/{id}")
	public String showCast(Model model, @PathVariable long id) {

		Optional<Cast> op = castService.findById(id);
		if (op.isPresent()) {
			Cast cast = op.get();
			model.addAttribute("cast", cast);
			return "cast_template";
		} else {
			return "castNotFound_template";
		}
	}

	@GetMapping("/cast/{id}/image")
	public ResponseEntity<Object> downloadCastImage(@PathVariable int id) throws SQLException {
		Optional<Cast> op = castService.findById(id);

		if (op.isPresent() && op.get().getCastImage() != null) {

			Blob image = op.get().getCastImage();
			Resource file = new InputStreamResource(image.getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(image.length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/cast/new")
	public String newCastForm(Model model) {
		model.addAttribute("movies", moviesService.findAll());
		return "new_or_modify_cast_template";
	}

	@PostMapping("/cast/new")
	public String newCast(Model model, @RequestParam(value = "castMovies", required = false) List<Long> castMovies,
			@RequestParam String castName, @RequestParam String castBiography,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date castBirthDate,
			@RequestParam String castOriginCountry, MultipartFile castImage) throws IOException {

		castService.save(castService.createCast(castName, castBiography, castBirthDate, castOriginCountry, castMovies),
				castImage);

		return "cast_created_template";
	}

	@PostMapping("/cast/{id}/delete")
	public String deleteCast(Model model, @PathVariable long id) throws IOException {
		Optional<Cast> op = castService.findById(id);
		if (op.isPresent()) {
			Cast cast = op.get();
			castService.removeMovies(cast);
			castService.deleteById(id);
			return "cast_deleted_template";
		} else {
			return "castNotFound_template";
		}
	}

	@GetMapping("/cast/{id}/modify")
	public String modifyCastForm(Model model, @PathVariable long id) {
		Optional<Cast> op = castService.findById(id);
		if (op.isPresent()) {
			Cast cast = op.get();
			model.addAttribute("cast", cast);
			model.addAttribute("allMovies", moviesService.findAll());
	
			// Si el actor tiene una imagen, pasar su URL al modelo
			if (cast.getCastImage() != null) {
				model.addAttribute("currentImageUrl", "/cast/" + id + "/image");
			}
	
			return "new_or_modify_cast_template";
		} else {
			return "castNotFound_template";
		}
	}
	

	@PostMapping("/cast/{id}/modify")
	public String modifyCast(Model model, @RequestParam(value = "castMovies", required = false) List<Long> castMovies,
			@PathVariable long id, @RequestParam String castName, @RequestParam String castBiography,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date castBirthDate,
			@RequestParam String castOriginCountry, @RequestParam(required = false) MultipartFile castImage) 
			throws IOException, SQLException {
	
		Optional<Cast> op = castService.findById(id);
		if (op.isPresent()) {
			Cast oldCast = op.get();
			Blob oldCastImage = oldCast.getCastImage(); // Guardamos la imagen actual
	
			castService.removeMovies(oldCast);
			Cast updatedCast = castService.createCast(castName, castBiography, castBirthDate, castOriginCountry, castMovies);
			updatedCast.setId(id);
	
			// Si no se sube una nueva imagen, reutilizar la anterior
			if (castImage == null || castImage.isEmpty()) {
				updatedCast.setCastImage(oldCastImage);
			} else {
				castService.save(updatedCast, castImage);
			}
	
			return "cast_modified_template";
		} else {
			return "castNotFound_template";
		}
	}
	
}