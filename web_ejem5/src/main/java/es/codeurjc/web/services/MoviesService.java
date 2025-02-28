package es.codeurjc.web.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.web.entities.Movie;
import es.codeurjc.web.repository.MoviesRepository;

@Service
public class MoviesService {

	@Autowired
	private MoviesRepository moviesRepository;

	public MoviesService() {
	}

	public Collection<Movie> findAll() {
		return moviesRepository.findAll();
	}

	public Optional<Movie> findById(long id) {
		return moviesRepository.findById(id);
	}

	public void save(Movie movie) {
		moviesRepository.save(movie);
	}

	public void deleteById(long id) {
		moviesRepository.deleteById(id);
	}

}