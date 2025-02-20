package es.codeurjc.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.web.services.*;
import es.codeurjc.web.entities.*;

@Controller
public class ReviewController {

    @Autowired
	private MoviesService moviesService;

    @Autowired
	private ReviewService reviewService;

    @GetMapping("/movie/{id}/review/new")
	public String newReviewForm(Model model) {
		return "new_review_template";
	}
	
	@PostMapping("/movie/{id}/review/new")
	public String newReview(Model model, Review review,@PathVariable int id) throws IOException {

		reviewService.save(review);

		Movie movie = moviesService.findById(id);

		model.addAttribute("movie", movie);

		return "movie_template";
	}

    @PostMapping("/movie/{id}/review/{idReview}/delete")
	public String deleteReview(Model model, @PathVariable long id,@PathVariable long idReview) throws IOException {

		reviewService.deleteById(idReview);

		Movie movie = moviesService.findById(id);

		model.addAttribute("movie", movie);

		return "movie_template";
	}
}