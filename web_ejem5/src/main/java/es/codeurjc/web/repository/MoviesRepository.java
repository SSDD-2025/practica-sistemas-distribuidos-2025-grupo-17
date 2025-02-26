package es.codeurjc.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.codeurjc.web.entities.Movie;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long> {
}