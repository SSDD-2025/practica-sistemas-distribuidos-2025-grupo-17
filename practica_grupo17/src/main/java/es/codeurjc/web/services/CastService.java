package es.codeurjc.web.services;

import java.util.Optional;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.web.entities.Cast;
import es.codeurjc.web.entities.Movie;
import es.codeurjc.web.repository.CastRepository;
import es.codeurjc.web.repository.MoviesRepository;

@Service
public class CastService {

	@Autowired
	private MoviesRepository moviesRepository;

	@Autowired
	private CastRepository castRepository;

	public CastService() {
	}

	public Collection<Cast> findAll() {
		return castRepository.findAll();
	}

	public Optional<Cast> findById(long id) {
		return castRepository.findById(id);
	}

	public boolean exist(long id) {
		return castRepository.existsById(id);
	}

	public void save(Cast cast) {
		castRepository.save(cast);
	}

	public void save(Cast cast, MultipartFile castImage) throws IOException {
		if (!castImage.isEmpty()) {
			cast.setCastImage(BlobProxy.generateProxy(castImage.getInputStream(), castImage.getSize()));
		}
		this.save(cast);
	}

	public void save(Cast cast, Blob castImage) throws IOException, SQLException {
		if (castImage != null) {
			cast.setCastImage(castImage);
		}
		this.save(cast);
	}

	public void deleteById(long id) {
		castRepository.deleteById(id);
	}

	public Cast createCast(String castName, String castBiography, Date castBirthDate, String castOriginCountry,
			List<Long> castMovies) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String castBirthDateCorrect = sdf.format(castBirthDate);
		Cast cast = new Cast(castName, castBiography, castBirthDateCorrect, castOriginCountry);
		if (castMovies != null) {
			for (int i = 0; i < castMovies.size(); i++) {
				Optional<Movie> op = moviesRepository.findById(castMovies.get(i));
				if (op.isPresent()) {
					Movie movie = op.get();
					cast.addMovie(movie);
				}
			}
		}
		return cast;
	}

	public void removeMovies(Cast cast) {
		List<Movie> movies = cast.getMovies();
		for (Movie movie : movies) {
			movie.removeCast(cast);
		}
		cast.setMovies(null);
	}

}