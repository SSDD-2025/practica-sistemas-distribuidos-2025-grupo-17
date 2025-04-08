package es.codeurjc.web.controller.rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.codeurjc.web.dto.review.CreateReviewDTO;
import es.codeurjc.web.dto.review.ReviewDTO;
import es.codeurjc.web.entities.Review;
import es.codeurjc.web.services.ReviewService;

@RestController
@RequestMapping("/api")
public class ReviewRestController {

    @Autowired
    private ReviewService reviewService;
    
    @PostMapping("/movies/{movieId}/reviews/")
    public ReviewDTO createReviewByMovie(@RequestBody CreateReviewDTO review) {
        return reviewService.save(review);
    }

    /*@DeleteMapping("/movies/{movieId}/reviews/{id}")
    public Review deleteReviewByMovie(@PathVariable long id) {
        Review review = reviewService.findById(id).orElseThrow();
        reviewService.deleteById(id);
        return review;
    }*/
    @PostMapping("/users/{userId}/reviews/")
    public ReviewDTO createReviewByUser(@RequestBody CreateReviewDTO review) {
        return reviewService.save(review);
    }
/*
    @DeleteMapping("/users/{userId}/reviews/{id}")
    public Review deleteReviewByUser(@PathVariable long id) {
        Review review = reviewService.findById(id).orElseThrow();
        reviewService.deleteById(id);
        return review;
    }*/
}
