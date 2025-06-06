package es.codeurjc.web.controller.web;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import java.util.NoSuchElementException;

import es.codeurjc.web.services.*;
import jakarta.servlet.http.HttpServletRequest;
import es.codeurjc.web.dto.cast.CastBasicDTO;
import es.codeurjc.web.dto.cast.CastDTO;
import es.codeurjc.web.dto.user.UserDTO;
import es.codeurjc.web.mapper.CastMapper;

@Controller
public class CastController {

	@Autowired
	private CastService castService;

	@Autowired
	private MoviesService moviesService;

	@Autowired
	private CastMapper castMapper;

	@Autowired
	private UserService userService;

	@GetMapping("/castList")
	public String showCastList(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			UserDTO userDTO = userService.findByUsername(principal.getName());
			model.addAttribute("user", userDTO);
			model.addAttribute("admin", userDTO.roles().contains("ADMIN"));
			model.addAttribute("registered", userDTO.roles().contains("REGISTERED"));
			model.addAttribute("logged", true);
		}
		model.addAttribute("cast", castService.findAll());
		return "castList_template";
	}

	@GetMapping("/cast/{id}")
	public String showCast(Model model, @PathVariable long id, Principal principal) {
		try {
			CastDTO castDTO = castService.findById(id);
			model.addAttribute("cast", castDTO);
			if (principal != null) {
				UserDTO userDTO = userService.findByUsername(principal.getName());
				model.addAttribute("user", userDTO);
				model.addAttribute("admin", userDTO.roles().contains("ADMIN"));
				model.addAttribute("registered", userDTO.roles().contains("REGISTERED"));
			}
			return "cast_template";
		} catch (NoSuchElementException e) {
			return "castNotFound_template";
		}
	}

	@GetMapping("/cast/{id}/image")
	public ResponseEntity<Object> downloadCastImage(@PathVariable int id) throws SQLException, IOException {
		Resource castImage;
		try {
			castImage = new InputStreamResource(castService.getCastImage(id));
		} catch (Exception e) {
			ClassPathResource resource = new ClassPathResource("static/not_available.png");
			byte[] imageBytes = resource.getInputStream().readAllBytes();
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(imageBytes);
		}
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(castImage);
	}

	@GetMapping("/cast/new")
	public String newCastForm(Model model) {
		model.addAttribute("movies", moviesService.findAll());
		return "new_or_modify_cast_template";
	}

	@PostMapping("/cast/new")
	public String newCast(Model model,
			@RequestParam(value = "castMovies", required = false) List<Long> castMovies,
			@RequestParam String castName,
			@RequestParam String castBiography,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date castBirthDate,
			@RequestParam String castOriginCountry,
			MultipartFile castImage)
			throws IOException, SQLException {

		castService.save(
				castService.createCastDTO(castName, castBiography, castBirthDate, castOriginCountry, castMovies),
				castImage);

		return "cast_created_template";
	}

	@PostMapping("/cast/{id}/delete")
	public String deleteCast(Model model, @PathVariable long id) throws IOException {
		try {
			CastDTO castDTO = castService.deleteById(id);
			model.addAttribute("cast", castDTO);
			return "cast_deleted_template";
		} catch (NoSuchElementException e) {
			return "castNotFound_template";
		}
	}

	@GetMapping("/cast/{id}/modify")
	public String modifyCastForm(Model model, @PathVariable long id) {
		try {
			CastDTO castDTO = castService.findById(id);
			model.addAttribute("cast", castDTO);
			model.addAttribute("allMovies", moviesService.findAll());
			if (castMapper.toDomain(castDTO).getCastImage() != null) {
				model.addAttribute("currentImageUrl", "/cast/" + id + "/image");
			}
			return "new_or_modify_cast_template";
		} catch (NoSuchElementException e) {
			return "castNotFound_template";
		}
	}

	@PostMapping("/cast/{id}/modify")
	public String modifyCast(Model model,
			@RequestParam(value = "castMovies", required = false) List<Long> castMovies,
			@PathVariable long id,
			@RequestParam String castName,
			@RequestParam String castBiography,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date castBirthDate,
			@RequestParam String castOriginCountry,
			@RequestParam(required = false) MultipartFile castImage)
			throws IOException, SQLException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String castBirthDateCorrect = sdf.format(castBirthDate);
			CastBasicDTO updatedCast = new CastBasicDTO(id, castName, castBiography, castBirthDateCorrect,
					castOriginCountry);
			castService.updateWeb(id, updatedCast, castImage, castMovies, castBirthDate);
			return "cast_modified_template";
		} catch (Exception e) {
			return "castNotFound_template";
		}
	}

}