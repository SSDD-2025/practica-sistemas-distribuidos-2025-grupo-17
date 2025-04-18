package es.codeurjc.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.codeurjc.web.entities.Movie;
import es.codeurjc.web.entities.Review;
import es.codeurjc.web.entities.User;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByMovieId(Long movieId, Pageable pageable);
    List<Review> findByAuthor(User user);
    List<Review> findByMovie(Movie movie);
}