/*package es.codeurjc.web.controller.rest;

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

import es.codeurjc.web.services.*;
import es.codeurjc.web.entities.*;


@RestController
@RequestMapping("/api/cast")
public class CastRestController {
    @Autowired
    private CastService castService;

    @GetMapping("/")
    public Collection<Cast> getAllCast() {
        return castService.findAll();
    }

    @GetMapping("/{id}")
    public Cast getCast(@PathVariable long id) {
        return castService.findById(id).orElseThrow();
    }

    @PostMapping("/")
    public ResponseEntity<Cast> createCast(@RequestBody Cast cast) {
        castService.save(cast);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(cast.getId()).toUri();
        return ResponseEntity.created(location).body(cast);
    }

    @DeleteMapping("/{id}")
    public Cast deleteCast(@PathVariable long id) {
        Cast cast = castService.findById(id).orElseThrow();
        castService.deleteById(id);
        return cast;
    }

    @PutMapping("/{id}")
    public Cast replaceCast(@PathVariable long id, @RequestBody Cast updatedCast) {
        if (castService.exist(id)) {
            updatedCast.setId(id);
            castService.save(updatedCast);
            return updatedCast;
        } else {
            throw new NoSuchElementException();
        }
    }
}*/