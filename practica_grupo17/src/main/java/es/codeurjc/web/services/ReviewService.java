package es.codeurjc.web.services;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import es.codeurjc.web.dto.review.ReviewDTO;
import es.codeurjc.web.dto.user.UserDTO;
import es.codeurjc.web.entities.Movie;
import es.codeurjc.web.entities.Review;
import es.codeurjc.web.entities.User;
import es.codeurjc.web.mapper.ReviewMapper;
import es.codeurjc.web.mapper.UserMapper;
import es.codeurjc.web.repository.MoviesRepository;
import es.codeurjc.web.repository.ReviewRepository;
import es.codeurjc.web.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;

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

	public Collection<ReviewDTO> findAll() {
		return toDTOs(reviewRepository.findAll());
	}

	public ReviewDTO findById(long id) {
		return toDTO(reviewRepository.findById(id).orElseThrow());
	}

	public boolean exist(long id) {
		return reviewRepository.existsById(id);
	}

	public ReviewDTO save(ReviewDTO review, UserDTO user) {
		Review newReview = reviewMapper.toDomain(review);
		newReview.setAuthor(userMapper.toDomain(user));
		return toDTO(reviewRepository.save(newReview));
	}

	public ReviewDTO deleteById(long id, UserDTO author) throws AccessDeniedException{
		Review review = reviewRepository.findById(id).orElseThrow();
		ReviewDTO reviewDTO = toDTO(review);
		if (reviewDTO.author().equals(author) || author.roles().contains("ADMIN")) {
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

	public List<ReviewDTO> findByMovieIdPaginated(long movieId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return reviewRepository.findByMovieId(movieId, pageable)
							   .stream()
							   .map(reviewMapper::toDTO)
							   .toList();
	}
}