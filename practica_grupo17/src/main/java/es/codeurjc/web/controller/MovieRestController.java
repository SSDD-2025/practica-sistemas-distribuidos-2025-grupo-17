/*package es.codeurjc.web.controller;

import java.net.URI;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.PostConstruct;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.codeurjc.web.services.*;
import es.codeurjc.web.entities.*;

@RestController
@RequestMapping("/movies")
public class MovieRestController {

    @Autowired
    private MoviesService moviesService;

    @Autowired
    private CastService reviewService;

    

    @GetMapping("/")
    public Collection<Movie> getMovies() {
        return moviesService.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable long id) {
        return moviesService.findById(id).orElseThrow();
    }

    @PostMapping("/")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        moviesService.save(movie);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(movie.getId()).toUri();
        return ResponseEntity.created(location).body(movie);
    }

    @DeleteMapping("/{id}")
    public Movie deleteMovie(@PathVariable long id) {
        Movie movie = moviesService.findById(id).orElseThrow();
        moviesService.deleteById(id);
        return movie;
    }

    @PutMapping("/{id}")
    public Movie replaceMovie(@PathVariable long id, @RequestBody Movie updatedMovie) {
        if (moviesService.exist(id)) {
            updatedMovie.setId(id);
            moviesService.save(updatedMovie);
            return updatedMovie;
        } else {
            throw new NoSuchElementException();
        }
    }



    @GetMapping("/")
    public Collection<Movie> getCastList() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public Movie getCast(@PathVariable long id) {
        return moviesService.findById(id).orElseThrow();
    }

    @PostMapping("/")
    public ResponseEntity<Movie> createCast(@RequestBody Movie movie) {
        moviesService.save(movie);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(movie.getId()).toUri();
        return ResponseEntity.created(location).body(movie);
    }

    @DeleteMapping("/{id}")
    public Movie deleteCast(@PathVariable long id) {
        Movie movie = moviesService.findById(id).orElseThrow();
        moviesService.deleteById(id);
        return movie;
    }

    @PutMapping("/{id}")
    public Movie replaceCast(@PathVariable long id, @RequestBody Movie updatedMovie) {
        if (moviesService.exist(id)) {
            updatedMovie.setId(id);
            moviesService.save(updatedMovie);
            return updatedMovie;
        } else {
            throw new NoSuchElementException();
        }
    }

}*/