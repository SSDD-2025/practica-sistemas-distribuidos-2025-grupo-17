package es.codeurjc.web.services;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.codeurjc.web.entities.Cast;

@Service
public class CastService {

	private ConcurrentMap<Long, Cast> castMap = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();

	public CastService() {
		//save(new Cast("Pepe", "Vendo moto", "Barata, barata"));
	}

	public Collection<Cast> findAll() {
		return castMap.values();
	}

	public Cast findById(long id) {
		return castMap.get(id);
	}

	public void save(Cast cast) {

		long id = nextId.getAndIncrement();

		cast.setId(id);

		castMap.put(id, cast);
	}

	public void deleteById(long id) {
		castMap.remove(id);
	}

}