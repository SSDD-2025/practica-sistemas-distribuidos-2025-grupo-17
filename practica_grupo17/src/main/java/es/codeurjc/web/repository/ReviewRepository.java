package es.codeurjc.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.codeurjc.web.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}