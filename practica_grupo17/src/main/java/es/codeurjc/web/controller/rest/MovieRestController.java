package es.codeurjc.web.controller.rest;

import java.io.IOException;
import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

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
    public Collection<MovieDTO> getAllMovies() {
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
        return moviesService.deleteById(id);
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

    @PostMapping("/{id}/image")
    public ResponseEntity<Object> createMovieImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {

        moviesService.createMovieImage(id, imageFile.getInputStream(), imageFile.getSize());

        URI location = fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getMovieImage(@PathVariable long id) throws SQLException, IOException {

        Resource postImage = (Resource) moviesService.getMovieImage(id);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(postImage);

    }

    @PutMapping("/{id}/image")
    public ResponseEntity<Object> replaceMovieImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {

        moviesService.replaceMovieImage(id, imageFile.getInputStream(), imageFile.getSize());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<Object> deleteMovieImage(@PathVariable long id) throws IOException {

        moviesService.deleteMovieImage(id);

        return ResponseEntity.noContent().build();
    }

}
