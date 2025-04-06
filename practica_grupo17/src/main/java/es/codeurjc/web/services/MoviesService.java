package es.codeurjc.web.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.web.dto.movie.CreateMovieDTO;
import es.codeurjc.web.dto.movie.MovieBasicDTO;
import es.codeurjc.web.dto.movie.MovieDTO;
import es.codeurjc.web.entities.Cast;
import es.codeurjc.web.entities.Movie;
import es.codeurjc.web.entities.Review;
import es.codeurjc.web.entities.User;
import es.codeurjc.web.mapper.MovieMapper;
import es.codeurjc.web.mapper.CastMapper;
import es.codeurjc.web.repository.CastRepository;
import es.codeurjc.web.repository.MoviesRepository;
import es.codeurjc.web.repository.UserRepository;

@Service
public class MoviesService {

	@Autowired
	private CastRepository castRepository;

	@Autowired
	private MoviesRepository moviesRepository;

	@Autowired
	private CastMapper castMapper;

	@Autowired
	private MovieMapper movieMapper;

	@Autowired
	private UserRepository userRepository;

	public MoviesService() {
	}

	public Collection<MovieDTO> findAll() {
		return movieMapper.toDTOs(moviesRepository.findAll());
	}

	public MovieDTO findById(long id) {
		return movieMapper.toDTO(moviesRepository.findById(id).orElseThrow());
	}

	public boolean exist(long id) {
		return moviesRepository.existsById(id);
	}

	public MovieDTO save(MovieDTO movieDTO) {
		if (movieDTO.getId() != null)
			throw new IllegalArgumentException();
		Movie movie = movieMapper.toDomain(movieDTO);
		moviesRepository.save(movie);
		return movieMapper.toDTO(movie);
	}

	public MovieDTO save(CreateMovieDTO movie, Blob imageField) {
		if (movie.getName() == null || movie.getName().isEmpty()) {
			throw new IllegalArgumentException("The title is empty");
		}
		Movie newMovie = movieMapper.toDomain(movie);
		newMovie.setMovieImage(imageField);
		return movieMapper.toDTO(moviesRepository.save(newMovie));
	}

	public MovieDTO save(CreateMovieDTO movie, MultipartFile movieImage) throws IOException {
		if (movieImage != null && movieImage.getSize() > 0) {
			return this.save(movie, BlobProxy.generateProxy(movieImage.getInputStream(), movieImage.getSize()));
		}
		return this.save(movie, (Blob) null);
	}

	public MovieDTO save(CreateMovieDTO movie) throws IOException, SQLException {
		return this.save(movie, (Blob) null);
	}

	public MovieDTO deleteById(long id) {
		Movie movie = moviesRepository.findById(id).orElseThrow();
		removeReviews(movie);
		removeCast(movie);
		MovieDTO movieDTO = movieMapper.toDTO(movie);
		moviesRepository.deleteById(id);
		return movieDTO;
	}

	private void removeReviews(Movie movie) {
		for (Review review : movie.getReviews()) {
			User user = review.getAuthor();
			user.getReviews().remove(review);
			userRepository.save(user);
		}
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

	public MovieDTO update(long movieId, MovieBasicDTO movie) throws IOException {
		return this.update(movieId, movie, null);
	}

	public MovieDTO update(long movieId, MovieBasicDTO movie, MultipartFile movieImage) throws IOException {
		Movie toUpdateMovie = moviesRepository.findById(movieId).orElseThrow();
		toUpdateMovie.setName(movie.getName());
		toUpdateMovie.setArgument(movie.getArgument());
		toUpdateMovie.setYear(movie.getYear());
		if (movieImage != null && movieImage.getSize() > 0) {
			Blob blobImage = BlobProxy.generateProxy(movieImage.getInputStream(), movieImage.getSize());
			toUpdateMovie.setMovieImage(blobImage);
		}
		return movieMapper.toDTO(moviesRepository.save(toUpdateMovie));
	}

	public void removeCast(Movie movie) {
		List<Cast> castList = movie.getCast();
		for (Cast cast : castList) {
			cast.removeMovie(movie);
		}
		movie.setCast(null);
	}

	public InputStream getMovieImage(long id) throws SQLException {
		Movie movie = moviesRepository.findById(id).orElseThrow();
		Blob blob = movie.getMovieImage();
		try {
			return blob.getBinaryStream();
		} catch (SQLException e) {
			throw new SQLException("Error getting image from database", e);
		}
	}

	public void createMovieImage(long id, InputStream inputStream, long size) {

		Movie movie = moviesRepository.findById(id).orElseThrow();

		movie.setMovieImage(BlobProxy.generateProxy(inputStream, size));

		moviesRepository.save(movie);
	}

	public void replaceMovieImage(long id, InputStream inputStream, long size) {

		Movie movie = moviesRepository.findById(id).orElseThrow();

		if (movie.getMovieImage() == null) {
			throw new NoSuchElementException();
		}

		movie.setMovieImage(BlobProxy.generateProxy(inputStream, size));

		moviesRepository.save(movie);
	}

	public void deleteMovieImage(long id) {

		Movie movie = moviesRepository.findById(id).orElseThrow();

		if (movie.getMovieImage() == null) {
			throw new NoSuchElementException();
		}

		movie.setMovieImage(null);

		moviesRepository.save(movie);
	}
}