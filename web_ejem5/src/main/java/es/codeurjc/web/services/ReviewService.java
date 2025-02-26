package es.codeurjc.web.services;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.codeurjc.web.entities.Review;

@Service
public class ReviewService {

	private ConcurrentMap<Long, Review> reviews = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();

	public ReviewService() {
		save(new Review("Pepe", "Vendo moto",null));
	}

	public Collection<Review> findAll() {
		return reviews.values();
	}

	public Review findById(long id) {
		return reviews.get(id);
	}

	public void save(Review review) {

		long id = nextId.getAndIncrement();

		review.setId(id);

		reviews.put(id, review);
	}

	public void deleteById(long id) {
		reviews.remove(id);
	}

}