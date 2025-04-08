package es.codeurjc.web.controller.web;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.web.services.*;
import es.codeurjc.web.dto.movie.MovieDTO;
import es.codeurjc.web.dto.review.CreateReviewDTO;
import es.codeurjc.web.entities.*;
import es.codeurjc.web.mapper.MovieMapper;
import es.codeurjc.web.repository.ReviewRepository;

@Controller
public class ReviewController {

	@Autowired
	private UserService userService;

	@Autowired
	private MoviesService moviesService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private MovieMapper movieMapper;

	@GetMapping("/myReviews")
	public String showMyReviews(Model model) {
		model.addAttribute("logged", true);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Optional<User> user = userService.findByUsername(username);
		if (user.isPresent()) {
			model.addAttribute("user", user.get());
		}
		return "my_reviews_template";
	}

	@GetMapping("/movies/{id}/review/new")
	public String newReviewForm(Model model, @PathVariable int id) {
		model.addAttribute("movie", moviesService.findById(id));
		return "new_review_template";
	}

	@PostMapping("/movies/{id}/review/new")
	public String newReview(Model model, @PathVariable int id, @RequestParam String reviewTitle,
			@RequestParam String reviewText) throws IOException {

		MovieDTO movieDTO = moviesService.findById(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Aquí siento que falta algo con el user. Pendiente de revisar y descomentar
		String username = auth.getName();
		User user = userService.findByUsername(username).get();
		CreateReviewDTO createReviewDTO = new CreateReviewDTO(reviewTitle, reviewText, movieDTO.id());
		reviewService.save(createReviewDTO);

		return "review_created_template";
	}

	@PostMapping("/movies/{id}/review/{idReview}/delete")
	public String deleteReview(Model model, @PathVariable long id, @PathVariable long idReview) throws IOException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username).get();
		MovieDTO movieDTO = moviesService.findById(id);
		Review review = reviewRepository.findById(idReview).orElseThrow(() -> new IOException());
		if (review.getAuthor().equals(user) || user.getRoles().contains("ADMIN")) {
			movieMapper.toDomain(movieDTO).removeReview(review);
			user.removeReview(review);
			reviewRepository.deleteById(idReview);

			model.addAttribute("movie", movieDTO);

			return "review_deleted_template";
		}

		return "redirect:/error?status=403&resource=No%20se%20puede%20borrar%20una%20review%20de%20otro%20usuario";
	}
}