package es.codeurjc.web.controller.rest;

import java.net.URI;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.codeurjc.web.services.ReviewService;
import es.codeurjc.web.services.UserService;
import es.codeurjc.web.entities.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public Collection<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userService.findById(id).orElseThrow();
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(user);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable long id) {
        User user = userService.findById(id).orElseThrow();
        userService.deleteById(id);
        return user;
    }

    @PutMapping("/{id}")
    public User replaceUser(@PathVariable long id, @RequestBody User updatedUser) {
        if (userService.exist(id)) {
            updatedUser.setId(id);
            userService.save(updatedUser);
            return updatedUser;
        } else {
            throw new NoSuchElementException();
        }
    }

    @PostMapping("/{movieId}/reviews/")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        reviewService.save(review);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(review.getId()).toUri();
        return ResponseEntity.created(location).body(review);
    }

    @DeleteMapping("{movieId}/reviews/{reviewId}")
    public Review deleteReview(@PathVariable long id) {
        Review review = reviewService.findById(id).orElseThrow();
        reviewService.deleteById(id);
        return review;
    }

}