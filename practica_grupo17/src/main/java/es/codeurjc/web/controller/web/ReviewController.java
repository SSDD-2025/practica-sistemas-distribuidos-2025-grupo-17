package es.codeurjc.web.controller.web;

import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;
import java.security.Principal;

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
import jakarta.servlet.http.HttpServletRequest;
import es.codeurjc.web.dto.user.UserDTO;

@Controller
public class ReviewController {

	@Autowired
	private UserService userService;

	@Autowired
	private MoviesService moviesService;

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/myReviews")
	public String showMyReviews(Model model) {
		model.addAttribute("logged", true);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		UserDTO user = userService.findByUsername(username);
		model.addAttribute("user", user);
		return "my_reviews_template";
	}

	@GetMapping("/movies/{id}/review/new")
	public String newReviewForm(Model model, @PathVariable int id) {
		model.addAttribute("movie", moviesService.findById(id));
		return "new_review_template";
	}

	@PostMapping("/movies/{id}/review/new")
	public String newReview(Model model, HttpServletRequest request, @PathVariable long id,
			@RequestParam String reviewTitle,
			@RequestParam String reviewText) throws IOException {
		Principal principal = request.getUserPrincipal();
		reviewService.addReview(id, reviewTitle, reviewText, principal);
		return "review_created_template";
	}

	@PostMapping("/movies/{id}/review/{idReview}/delete")
	public String deleteReview(Model model, @PathVariable long id, @PathVariable long idReview,
			HttpServletRequest request) throws IOException {

		if (reviewService.exist(idReview)) {
			Principal principal = request.getUserPrincipal();
			UserDTO userDTO = userService.findByUsername(principal.getName());
			try {
				reviewService.deleteById(idReview, userDTO);
			} catch (AccessDeniedException e) {
				System.out.println(e.getMessage());
				return "redirect:/error?status=403&resource=No%20se%20puede%20borrar%20una%20review%20de%20otro%20usuario";
			}
			model.addAttribute("movie", moviesService.findById(id));
			return "review_deleted_template";
		} else {
			return "redirect:/error?status=403&resource=No%20se%20puede%20borrar%20una%20review%20de%20otro%20usuario";
		}
	}
}