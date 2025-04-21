package es.codeurjc.web.services;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import es.codeurjc.web.dto.movie.MovieDTO;
import es.codeurjc.web.dto.review.CreateReviewDTO;
import es.codeurjc.web.dto.review.ReviewDTO;
import es.codeurjc.web.dto.user.UserDTO;
import es.codeurjc.web.entities.Movie;
import es.codeurjc.web.entities.Review;
import es.codeurjc.web.entities.User;
import es.codeurjc.web.mapper.MovieMapper;
import es.codeurjc.web.mapper.ReviewMapper;
import es.codeurjc.web.mapper.UserMapper;
import es.codeurjc.web.repository.MoviesRepository;
import es.codeurjc.web.repository.ReviewRepository;
import es.codeurjc.web.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.data.domain.Page;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private ReviewMapper reviewMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MoviesRepository moviesRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MovieMapper movieMapper;

	public Collection<ReviewDTO> findAll() {
		return toDTOs(reviewRepository.findAll());
	}

	public ReviewDTO findById(long id) {
		return toDTO(reviewRepository.findById(id).orElseThrow());
	}

	public boolean exist(long id) {
		return reviewRepository.existsById(id);
	}

	public ReviewDTO save(CreateReviewDTO review, UserDTO user) {
		Review newReview = reviewMapper.toDomain(review);
		newReview.setAuthor(userMapper.toDomain(user));
		newReview.setMovie(moviesRepository.findById(review.movieId()).orElseThrow());
		return reviewMapper.toDTO(reviewRepository.save(newReview));
	}

	public Collection<ReviewDTO> getReviews(UserDTO user) {
		List<Review> reviews=reviewRepository.findByAuthor(userMapper.toDomain(user));
		return reviewMapper.toDTO(reviews);
	}

	public Collection<ReviewDTO> getReviews(MovieDTO movie) {
		List<Review> reviews=reviewRepository.findByMovie(movieMapper.toDomain(movie));
		return reviewMapper.toDTO(reviews);
	}

	public ReviewDTO deleteById(long id, UserDTO author) throws AccessDeniedException{
		Review review = reviewRepository.findById(id).orElseThrow();
		ReviewDTO reviewDTO = toDTO(review);
		if (reviewDTO.author().id().equals(author.id())) {
			User user = review.getAuthor();
			user.removeReview(review);
			userRepository.save(user);
			Movie movie = review.getMovie();
			movie.removeReview(review);
			moviesRepository.save(movie);
			reviewRepository.delete(review);
		}else{
			throw new AccessDeniedException("No tiene permiso para eliminar reseñas ajenas.");
		}
		return reviewDTO;
	}

	public ReviewDTO addReview(long movieId, String title, String text, Principal principal) {
		Movie movie = moviesRepository.findById(movieId).orElseThrow();
		User user = userRepository.findByUsername(principal.getName()).orElseThrow();
		Review review = new Review(title, text, movie, user);
		return toDTO(reviewRepository.save(review));
	}

	private ReviewDTO toDTO(Review review) {
		return reviewMapper.toDTO(review);
	}

	private Collection<ReviewDTO> toDTOs(Collection<Review> review) {
		return reviewMapper.toDTOs(review);
	}

	public Page<ReviewDTO> findByMovieIdPaginated(long movieId, Pageable pageable) {
		return reviewRepository.findByMovieId(movieId, pageable)
							   .map(reviewMapper::toDTO);
	}
}