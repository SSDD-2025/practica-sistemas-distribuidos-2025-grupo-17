package es.codeurjc.web.services;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.web.entities.Cast;
import es.codeurjc.web.entities.Movie;
import es.codeurjc.web.repository.CastRepository;
import es.codeurjc.web.repository.MoviesRepository;

@Service
public class MoviesService {

	@Autowired
	private CastRepository castRepository;

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

	public boolean exist(long id) {
		return moviesRepository.existsById(id);
	}

	public void save(Movie movie) {
		moviesRepository.save(movie);
	}

	public void save(Movie movie, MultipartFile movieImage) throws IOException {
		if (!movieImage.isEmpty()) {
			movie.setMovieImage(BlobProxy.generateProxy(movieImage.getInputStream(), movieImage.getSize()));
		}
		this.save(movie);
	}

	public void save(Movie movie, Blob movieImage) throws IOException, SQLException {
		if (movieImage != null) {
			movie.setMovieImage(movieImage);
		}
		this.save(movie);
	}

	public void deleteById(long id) {
		moviesRepository.deleteById(id);
	}

	public Movie createMovie(String movieName, String movieArgument, int movieYear, List<Long> movieCast,
			String movieTrailer) {
		Movie movie = new Movie(movieName, movieArgument, movieYear, movieTrailer);
		if (movieCast != null) {
			for (int i = 0; i < movieCast.size(); i++) {
				Optional<Cast> op = castRepository.findById(movieCast.get(i));
				if (op.isPresent()) {
					Cast cast = op.get();
					movie.addCast(cast);
				}
			}
		}
		return movie;
	}

	public void removeCast(Movie movie) {
		List<Cast> castList = movie.getCast();
		for (Cast cast : castList) {
			cast.removeMovie(movie);
		}
		movie.setCast(null);
	}

}