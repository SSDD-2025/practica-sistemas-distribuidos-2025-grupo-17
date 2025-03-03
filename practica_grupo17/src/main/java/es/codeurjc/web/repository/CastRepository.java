package es.codeurjc.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.codeurjc.web.entities.Cast;

@Repository
public interface CastRepository extends JpaRepository<Cast, Long> {
}
