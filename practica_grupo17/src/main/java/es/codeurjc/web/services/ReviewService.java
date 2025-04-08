package es.codeurjc.web.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.web.dto.review.CreateReviewDTO;
import es.codeurjc.web.dto.review.ReviewDTO;
import es.codeurjc.web.entities.Review;
import es.codeurjc.web.mapper.ReviewMapper;
import es.codeurjc.web.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private ReviewMapper reviewMapper;

	public Collection<ReviewDTO> findAll() {
		return toDTOs(reviewRepository.findAll());
	}

	public ReviewDTO findById(long id) {
		return toDTO(reviewRepository.findById(id).orElseThrow());
	}

	public ReviewDTO save(CreateReviewDTO review) {
		Review newReview = reviewMapper.toDomain(review);
		return toDTO(reviewRepository.save(newReview));
	}

	public ReviewDTO deleteById(long id) {
		Review review = reviewRepository.findById(id).orElseThrow();
		ReviewDTO reviewDTO = toDTO(review);
		reviewRepository.deleteById(id);
		return reviewDTO;
	}

	private ReviewDTO toDTO(Review review) {
		return reviewMapper.toDTO(review);
	}

	private Collection<ReviewDTO> toDTOs(Collection<Review> review) {
		return reviewMapper.toDTOs(review);
	}
}