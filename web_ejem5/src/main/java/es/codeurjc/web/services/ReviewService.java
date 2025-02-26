package es.codeurjc.web.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.web.entities.Movie;
import es.codeurjc.web.entities.Review;
import es.codeurjc.web.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired 
	private ReviewRepository reviewRepository;

	public Collection<Review> findByMovie(Movie movie){
		return reviewRepository.findByMovie(movie);
	}
	public Collection<Review> findAll() {
		return reviewRepository.findAll();
	}

	public void save(Review review) {
		reviewRepository.save(review);
	}

	public void deleteById(long id) {
		reviewRepository.deleteById(id);
	}

}