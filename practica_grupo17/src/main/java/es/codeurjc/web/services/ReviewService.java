package es.codeurjc.web.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.web.entities.Review;
import es.codeurjc.web.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	public Collection<Review> findAll() {
		return reviewRepository.findAll();
	}

	public Optional<Review> findById(long id) {
		return reviewRepository.findById(id);
	}

	public void save(Review review) {
		reviewRepository.save(review);
	}

	public void deleteById(long id) {
		reviewRepository.deleteById(id);
	}

}