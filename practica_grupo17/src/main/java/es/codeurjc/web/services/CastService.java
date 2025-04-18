package es.codeurjc.web.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.web.dto.cast.CastBasicDTO;
import es.codeurjc.web.dto.cast.CastDTO;
import es.codeurjc.web.dto.cast.CreateCastDTO;
import es.codeurjc.web.entities.Cast;
import es.codeurjc.web.entities.Movie;
import es.codeurjc.web.mapper.CastMapper;
import es.codeurjc.web.repository.CastRepository;
import es.codeurjc.web.repository.MoviesRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Service
public class CastService {

	@Autowired
	private MoviesRepository moviesRepository;

	@Autowired
	private CastRepository castRepository;

	@Autowired
	private CastMapper castMapper;

	public CastService() {
	}

	public Collection<CastDTO> findAll() {
		return toDTOs(castRepository.findAll());
	}

	public CastDTO findById(long id) {
		return toDTO(castRepository.findById(id).orElseThrow());
	}

	public boolean exist(long id) {
		return castRepository.existsById(id);
	}

	public CastDTO save(CastDTO castDTO) {
		if (castDTO.id() != null)
			throw new IllegalArgumentException();
		Cast cast = toDomain(castDTO);
		castRepository.save(cast);
		return toDTO(cast);
	}

	public CastDTO save(CreateCastDTO cast, MultipartFile castImage) throws IOException, SQLException {
		if (castImage != null && castImage.getSize() > 0) {
			return this.save(cast, BlobProxy.generateProxy(castImage.getInputStream(), castImage.getSize()));
		}
		return this.save(cast, (Blob) null);
	}

	public CastDTO save(CreateCastDTO cast, Blob castImage) throws IOException, SQLException {
		Cast newCast = toDomain(cast);
		if (castImage != null) {
			newCast.setCastImage(castImage);
		}
		return toDTO(castRepository.save(newCast));
	}

	public CastDTO save(CreateCastDTO cast) throws IOException, SQLException {
		return this.save(cast, (Blob) null);
	}

	public CastDTO update(long castId, CastBasicDTO cast) throws IOException {
		return this.update(castId, cast, null);
	}

	public CastDTO update(long movieId, CastBasicDTO cast, MultipartFile castImage) throws IOException {
		Cast toUpdateMovie = castRepository.findById(movieId).orElseThrow();
		toUpdateMovie.setName(cast.name());
		toUpdateMovie.setBirthDate(cast.birthDate());
		toUpdateMovie.setOriginCountry(cast.originCountry());
		if (castImage != null && castImage.getSize() > 0) {
			Blob blobImage = BlobProxy.generateProxy(castImage.getInputStream(), castImage.getSize());
			toUpdateMovie.setCastImage(blobImage);
		}
		return toDTO(castRepository.save(toUpdateMovie));
	}

	public CastDTO updateWeb(long castId, CastBasicDTO cast, MultipartFile castImage, List<Long> movieIds,
			Date birthDate) throws IOException {
		Cast oldCast = castRepository.findById(castId).orElseThrow();
		Blob oldCastImage = oldCast.getCastImage();
		removeMovies(oldCast);
		Cast toUpdateCast = createCast(cast.name(), cast.biography(), birthDate, cast.originCountry(), movieIds);
		toUpdateCast.setId(castId);
		if (castImage != null && !castImage.isEmpty()) {
			Blob blobImage = BlobProxy.generateProxy(castImage.getInputStream(), castImage.getSize());
			toUpdateCast.setCastImage(blobImage);
			return toDTO(castRepository.save(toUpdateCast));
		} else {
			toUpdateCast.setCastImage(oldCastImage);
			return toDTO(castRepository.save(toUpdateCast));
		}
	}

	public CastDTO deleteById(long id) {
		Cast cast = castRepository.findById(id).orElseThrow();
		removeMovies(cast);
		CastDTO castDTO = toDTO(cast);
		castRepository.deleteById(id);
		return castDTO;
	}

	public Cast createCast(String castName, String castBiography, Date castBirthDate, String castOriginCountry,
			List<Long> castMovies) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String castBirthDateCorrect = sdf.format(castBirthDate);
		Cast cast = new Cast(castName, castBiography, castBirthDateCorrect, castOriginCountry);
		if (castMovies != null) {
			for (int i = 0; i < castMovies.size(); i++) {
				Movie movie = moviesRepository.findById(castMovies.get(i)).orElseThrow();
				cast.addMovie(movie);
			}
		}
		return cast;
	}

	public InputStream getCastImage(long id) throws SQLException {
		Cast cast = castRepository.findById(id).orElseThrow();
		Blob blob = cast.getCastImage();
		try {
			return blob.getBinaryStream();
		} catch (SQLException e) {
			throw new SQLException("Error getting image from database", e);
		}
	}

	public void createCastImage(long id, InputStream inputStream, long size) {

		Cast cast = castRepository.findById(id).orElseThrow();

		cast.setCastImage(BlobProxy.generateProxy(inputStream, size));

		castRepository.save(cast);
	}

	public void replaceCastImage(long id, InputStream inputStream, long size) {

		Cast cast = castRepository.findById(id).orElseThrow();

		if (cast.getCastImage() == null) {
			throw new NoSuchElementException();
		}

		cast.setCastImage(BlobProxy.generateProxy(inputStream, size));

		castRepository.save(cast);
	}

	public void deleteCastImage(long id) {

		Cast cast = castRepository.findById(id).orElseThrow();

		if (cast.getCastImage() == null) {
			throw new NoSuchElementException();
		}

		cast.setCastImage(null);

		castRepository.save(cast);
	}

	public void removeMovies(Cast cast) {
		List<Movie> movies = cast.getMovies();
		for (Movie movie : movies) {
			movie.removeCast(cast);
		}
		cast.setMovies(null);
	}

	private CastDTO toDTO(Cast cast) {
		return castMapper.toDTO(cast);
	}

	private Cast toDomain(CastDTO castDTO) {
		return castMapper.toDomain(castDTO);
	}

	private Cast toDomain(CreateCastDTO castDTO) {
		Cast newCast = new Cast(castDTO.name(), castDTO.biography(), castDTO.birthDate(), castDTO.originCountry());
		if (castDTO.movieIds() != null && castDTO.movieIds().size() > 0) {
			List<Long> movieIds = castDTO.movieIds();
			addMovie(newCast, movieIds);
		}
		return newCast;
	}

	private void addMovie(Cast newCast, List<Long> movieIds) {
		for (Long singId : movieIds) {
			Movie movie = moviesRepository.findById(singId).orElseThrow();
			newCast.addMovie(movie);
		}
	}

	private Collection<CastDTO> toDTOs(Collection<Cast> Cast) {
		return castMapper.toDTOs(Cast);
	}

	public CreateCastDTO createCastDTO(String name, String biography, Date birthDate, String originCountry,
			List<Long> moviesIds) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String castBirthDateCorrect = sdf.format(birthDate);
		return new CreateCastDTO(name, biography, castBirthDateCorrect, originCountry, moviesIds);
	}

	public Page<CastDTO> findAllPaginated(Pageable pageable) {
		return castRepository.findAll(pageable)
							 .map(castMapper::toDTO);
	}

}
