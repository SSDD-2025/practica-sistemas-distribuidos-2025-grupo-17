package es.codeurjc.web.controller.rest;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.web.dto.review.ReviewDTO;
import es.codeurjc.web.entities.User;
import es.codeurjc.web.services.ReviewService;
import es.codeurjc.web.services.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ReviewRestController {

     @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @GetMapping("/myReviews")
    public Collection<ReviewDTO> getMyReviews(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
		User user=userService.findByUsername(principal.getName()).orElseThrow();
        return userService.getReviews(user);
    }

    @PostMapping("/myReviews")
    public ReviewDTO createReview(@RequestBody ReviewDTO review, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
		User user=userService.findByUsername(principal.getName()).orElseThrow();
        reviewService.save(review,user);
        return review;
    }

    @DeleteMapping("/myReviews/{reviewId}")
    public ReviewDTO deleteReview(@PathVariable long reviewId, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
		User user=userService.findByUsername(principal.getName()).orElseThrow();
        return reviewService.deleteById(reviewId, user);
    }

}
