package es.codeurjc.web.controller.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.web.services.*;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
	private MoviesService moviesService;

	@Autowired
	private CastService castService;

	@GetMapping("/")
	public String showMoviesList(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			model.addAttribute("logged", true);
		}
		model.addAttribute("movies", moviesService.findAll());
		model.addAttribute("cast", castService.findAll());

		return "home_template";
	}
}