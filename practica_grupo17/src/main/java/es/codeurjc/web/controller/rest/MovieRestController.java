package es.codeurjc.web.controller.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

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

import es.codeurjc.web.services.*;
import es.codeurjc.web.dto.movie.CreateMovieDTO;
import es.codeurjc.web.dto.movie.MovieBasicDTO;
import es.codeurjc.web.dto.movie.MovieDTO;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

    @Autowired
    private MoviesService moviesService;

    @GetMapping("/")
    public Collection<MovieDTO> getMovies() {
        return moviesService.findAll();
    }

    @GetMapping("/{id}")
    public MovieDTO getMovie(@PathVariable long id) {
        return moviesService.findById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDTO createMovie(@RequestBody CreateMovieDTO movie) throws IOException, SQLException {
        return moviesService.save(movie);
    }

    @DeleteMapping("/{id}")
    public MovieDTO deleteMovie(@PathVariable long id) {
        return moviesService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> replaceMovie(@PathVariable long id, @RequestBody MovieBasicDTO updatedMovieDTO)
            throws IOException {
        if (moviesService.exist(id)) {
            MovieDTO movie = moviesService.update(id, updatedMovieDTO);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
