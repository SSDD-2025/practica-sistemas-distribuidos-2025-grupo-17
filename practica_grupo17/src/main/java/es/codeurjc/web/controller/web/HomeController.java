package es.codeurjc.web.controller.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.web.dto.movie.MovieDTO;
import es.codeurjc.web.dto.user.UserDTO;
import es.codeurjc.web.services.*;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
	private MoviesService moviesService;

	@Autowired
	private CastService castService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String showMoviesList(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			UserDTO userDTO = userService.findByUsername(principal.getName());
			model.addAttribute("user", userDTO);
			model.addAttribute("admin", userDTO.roles().contains("ADMIN"));
			model.addAttribute("registered", userDTO.roles().contains("REGISTERED"));
			model.addAttribute("logged", true);
		}

		List<MovieDTO> allMovies = new ArrayList<>(moviesService.findAll());
		List<MovieDTO> firstPage = allMovies.stream().limit(5).toList();
		model.addAttribute("movies", firstPage);

		model.addAttribute("cast", castService.findAll());

		return "home_template";
	}
}
