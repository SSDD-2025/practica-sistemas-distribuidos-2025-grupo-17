package es.codeurjc.web.services;

import java.util.Optional;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.web.entities.Cast;
import es.codeurjc.web.repository.CastRepository;

@Service
public class CastService {
	@Autowired
	private CastRepository castRepository;

	
	public CastService() {
	}

	public Collection<Cast> findAll() {
		return castRepository.findAll();
	}

	public Optional <Cast> findById(long id) {
		return castRepository.findById(id);
	}

	public void save(Cast cast) {
		castRepository.save(cast);
	}

	public void deleteById(long id) {
		castRepository.deleteById(id);
	}

}