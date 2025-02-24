package es.codeurjc.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.web.services.*;
import es.codeurjc.web.entities.*;

@Controller
public class ReviewController {

    @Autowired
	private MoviesService moviesService;

    @Autowired
	private ReviewService reviewService;

	@GetMapping("/myReviews")
	public String  showMyReviews(Model model) {
		model.addAttribute("reviews", reviewService.findAll());
		return "my_reviews_template.html";
	}

    @GetMapping("/movie/{id}/review/new")
	public String newReviewForm(Model model, @PathVariable int id) {
		model.addAttribute("movie", moviesService.findById(id));
		return "new_review_template";
	}
	
	@PostMapping("/movie/{id}/review/new")
	public String newReview(Model model,@PathVariable int id, @RequestParam String reviewTitle, @RequestParam String reviewText) throws IOException {

		Review review=new Review(reviewTitle,reviewText);
		reviewService.save(review);

		Movie movie = moviesService.findById(id);
		
		model.addAttribute("movie", movie);

		return "review_created_template";
	}

    @PostMapping("/movie/{id}/review/{idReview}/delete")
	public String deleteReview(Model model, @PathVariable long id,@PathVariable long idReview) throws IOException {

		reviewService.deleteById(idReview);

		Movie movie = moviesService.findById(id);

		model.addAttribute("movie", movie);

		return "review_deleted_template";
	}
}