package es.codeurjc.web.services;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.codeurjc.web.entities.Movie;

@Service
public class MoviesService {

	private ConcurrentMap<Long, Movie> movies = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();

	public MoviesService() {
		save(new Movie("Pepe", "Vendo moto", 5,null,""));
	}

	public Collection<Movie> findAll() {
		return movies.values();
	}

	public Movie findById(long id) {
		return movies.get(id);
	}

	public void save(Movie movie) {

		long id = nextId.getAndIncrement();

		movie.setId(id);

		movies.put(id, movie);
	}

	public void deleteById(long id) {
		movies.remove(id);
	}

}