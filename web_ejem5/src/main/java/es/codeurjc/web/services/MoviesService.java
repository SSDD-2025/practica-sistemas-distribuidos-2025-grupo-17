package es.codeurjc.board.service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.codeurjc.board.model.Post;

@Service
public class MoviesService {

	private ConcurrentMap<Long, Movie> movies = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();

	public MoviesService() {
		save(new Movie("Pepe", "Vendo moto", "Barata, barata"));
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

		movies.put(id, post);
	}

	public void deleteById(long id) {
		movies.remove(id);
	}

}