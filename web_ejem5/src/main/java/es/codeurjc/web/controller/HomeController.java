package es.codeurjc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.web.services.*;

@Controller
public class HomeController {

    @Autowired
	private MoviesService moviesService;
	
	@Autowired
	private CastService castService;

	@Autowired
	private ReviewService reviewService;

    @GetMapping("/")
	public String showMoviesList(Model model) {

		model.addAttribute("movies", moviesService.findAll());
		model.addAttribute("cast",castService.findAll());
		model.addAttribute("reviews",reviewService.findAll());

		return "home_template";
	}
}