package es.codeurjc.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialBlob;

import es.codeurjc.web.entities.*;
import java.io.File;
import java.nio.file.Files;
import java.sql.Blob;

@Service
public class SampleDataService {
	@Autowired
	private MoviesService moviesService;

	@Autowired
	private CastService castService;

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private UserService userService;

	private Cast c[] = new Cast[4];
	private Movie m[] = new Movie[3];
	@SuppressWarnings("unused")
	private Review r[] = new Review[7];

	private final static String IMAGES_PATH = "images/";

	@PostConstruct
	public void init() {

		// User
		User user = new User("user1", "password", "admin");
		userService.save(user);
		try {
			// Cast
			c = initCast();
			// Movies
			m = initMovies();
			// Reviews
			r = initReviews(user);
			// Movie list
			ArrayList<Movie> movies1 = new ArrayList<Movie>(),
					movies2 = new ArrayList<Movie>(),
					movies3 = new ArrayList<Movie>(),
					movies4 = new ArrayList<Movie>();
			// Add movies list to cast entities
			addMoviesToCast(movies1, movies2, movies3, movies4);
			// Cast list
			ArrayList<Cast> cast1 = new ArrayList<>(),
					cast2 = new ArrayList<>(),
					cast3 = new ArrayList<>();
			// Add cast list to movies entities
			addCastToMovies(cast1, cast2, cast3);
			// Save movies entities in moviesService (no images)
			moviesService.save(m[0]);
			moviesService.save(m[1]);
			moviesService.save(m[2]);
			// Save cast entities in castService (no images)
			castService.save(c[0]);
			castService.save(c[1]);
			castService.save(c[2]);
			castService.save(c[3]);
			// Agregar imágenes a cast y movies y guardarlos de nuevo usando File y Blob
			File imageFile1 = new File(IMAGES_PATH + "cast-images/image-1.jpg"),
					imageFile2 = new File(IMAGES_PATH + "cast-images/image-0.jpg"),
					imageFile3 = new File(IMAGES_PATH + "cast-images/image-3.jpg"),
					imageFile4 = new File(IMAGES_PATH + "cast-images/image-2.jpg"),
					imageFile5 = new File(IMAGES_PATH + "movies-images/image-0.jpg"),
					imageFile6 = new File(IMAGES_PATH + "movies-images/image-1.jpg"),
					imageFile7 = new File(IMAGES_PATH + "movies-images/image-2.jpg");
			byte[] imageBytes1 = Files.readAllBytes(imageFile1.toPath()),
					imageBytes2 = Files.readAllBytes(imageFile2.toPath()),
					imageBytes3 = Files.readAllBytes(imageFile3.toPath()),
					imageBytes4 = Files.readAllBytes(imageFile4.toPath()),
					imageBytes5 = Files.readAllBytes(imageFile5.toPath()),
					imageBytes6 = Files.readAllBytes(imageFile6.toPath()),
					imageBytes7 = Files.readAllBytes(imageFile7.toPath());
			Blob imageBlob1 = new SerialBlob(imageBytes1),
					imageBlob2 = new SerialBlob(imageBytes2),
					imageBlob3 = new SerialBlob(imageBytes3),
					imageBlob4 = new SerialBlob(imageBytes4),
					imageBlob5 = new SerialBlob(imageBytes5),
					imageBlob6 = new SerialBlob(imageBytes6),
					imageBlob7 = new SerialBlob(imageBytes7);

			c[0].setCastImage(imageBlob1);
			c[1].setCastImage(imageBlob2);
			c[2].setCastImage(imageBlob3);
			c[3].setCastImage(imageBlob4);
			m[0].setMovieImage(imageBlob5);
			m[1].setMovieImage(imageBlob6);
			m[2].setMovieImage(imageBlob7);

			castService.save(c[0]);
			castService.save(c[1]);
			castService.save(c[2]);
			castService.save(c[3]);
			moviesService.save(m[0]);
			moviesService.save(m[1]);
			moviesService.save(m[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Cast[] initCast() {
		String castName1 = "Robert Downey Jr",
				castName2 = "Maximiliano Cloritix",
				castName3 = "Santiago Segura",
				castName4 = "Pablo Motos";
		String castBio1 = "Robert John Downey Jr. es un actor, productor y cantante estadounidense. Inició su carrera a temprana edad en varios filmes dirigidos por su padre, Robert Downey Sr., y en su infancia estudió actuación en varias academias de Nueva York.",
				castBio2 = "Genio inventor y gran actor, mueve masas como el que más.",
				castBio3 = "Santiago Segura Silva es un actor y cineasta español, popular por su saga de Torrente.",
				castBio4 = "Pablo Motos Burgos es un actor de televisión, locutor de radio, humorista y empresario español. Desde 2006 es el presentador y actor del programa de entrevistas La madriguera.";
		String castBirthDate1 = "1965-06-05",
				castBirthDate2 = "2034-12-24",
				castBirthDate3 = "1900-1-6",
				castBirthDate4 = "1867-12-12";
		String castCountry1 = "EE.UU",
				castCountry2 = "España",
				castCountry3 = "Uzbekistán",
				castCountry4 = "India";
		Cast cInit[] = new Cast[4];
		cInit[0] = new Cast(castName1, castBio1, castBirthDate1, castCountry1);
		cInit[1] = new Cast(castName2, castBio2, castBirthDate2, castCountry2);
		cInit[2] = new Cast(castName3, castBio3, castBirthDate3, castCountry3);
		cInit[3] = new Cast(castName4, castBio4, castBirthDate4, castCountry4);

		castService.save(cInit[0]);
		castService.save(cInit[1]);
		castService.save(cInit[2]);
		castService.save(cInit[3]);
		return cInit;
	}

	private Movie[] initMovies() {
		String movieName1 = "Mortadelo y Filemon Contra Jimmy El Cachondo",
				movieName2 = "Torrente 10",
				movieName3 = "Vengadores: Guardianes de las patatas";
		String movieArg1 = "La T.I.A acaba de sufrir uno de sus peores asaltos criminales, un ataque de risa en toda regla perpetrado por Jimmy el Cachondo para poder hacerse con un documento ultrasecreto.",
				movieArg2 = "Torrente sale de la cárcel en el año 2018 para encontrarse una España totalmente diferente de la que él conocía. Ante este 'shock' decide pasar a ser un 'fuera de la ley' y atracar un casino.",
				movieArg3 = "Taki y Mitsuha descubren un día que durante el sueño sus cuerpos se intercambian, y comienzan a comunicarse por medio de notas.";
		int movieYear1 = 2000,
				movieYear2 = 3015,
				movieYear3 = 2014;
		String movieTrailer1 = "https: www.youtube.com/watch?v=aNgdO0CQAVw&ab_channel=WarnerBros.PicturesEspa%C3%B1a",
				movieTrailer2 = "https: www.youtube.com/watch?v=ppiadC1sSik&ab_channel=SonyPicturesEspa%C3%B1a",
				movieTrailer3 = "https: www.youtube.com/watch?v=osY929PCs2o&ab_channel=Netflix";
		Movie mInit[] = new Movie[3];
		mInit[0] = new Movie(movieName1, movieArg1, movieYear1, movieTrailer1);
		mInit[1] = new Movie(movieName2, movieArg2, movieYear2, movieTrailer2);
		mInit[2] = new Movie(movieName3, movieArg3, movieYear3, movieTrailer3);
		moviesService.save(mInit[0]);
		moviesService.save(mInit[1]);
		moviesService.save(mInit[2]);
		return mInit;
	}

	private Review[] initReviews(User user) {
		String reviewTitle1 = "Muy buena",
				reviewTitle2 = "Salí llorando",
				reviewTitle3 = "Casi me duermo",
				reviewTitle4 = "No estuvo nada, nada mal";
		String reviewText1 = "La verdad me ha sorprendido mucho, que profunda...",
				reviewText2 = "Me rociaron gas pimienta y salí llorando. 0/10",
				reviewText3 = "Llevo 6 días sin poder dormir y vine a la peli para curar mi insomnio",
				reviewText4 = "Ni tan mal, vine buscando cobre y en efecto encontré cobre, pero de buena calidad.";
		Review rInit[] = new Review[7];
		rInit[0] = new Review(reviewTitle1, reviewText1, m[0], user);
		rInit[1] = new Review(reviewTitle2, reviewText2, m[0], user);
		rInit[2] = new Review(reviewTitle3, reviewText3, m[1], user);
		rInit[3] = new Review(reviewTitle4, reviewText4, m[2], user);
		rInit[4] = new Review(reviewTitle1, reviewText1, m[2], user);
		rInit[5] = new Review(reviewTitle4, reviewText4, m[0], user);
		rInit[6] = new Review(reviewTitle3, reviewText3, m[1], user);
		reviewService.save(rInit[0]);
		reviewService.save(rInit[1]);
		reviewService.save(rInit[2]);
		reviewService.save(rInit[3]);
		reviewService.save(rInit[4]);
		reviewService.save(rInit[5]);
		reviewService.save(rInit[6]);
		return rInit;
	}

	private void addMoviesToCast(ArrayList<Movie> movies1, ArrayList<Movie> movies2, ArrayList<Movie> movies3,
			ArrayList<Movie> movies4) {
		movies1.add(m[0]);
		movies1.add(m[1]);
		movies1.add(m[2]);
		movies2.add(m[0]);
		movies2.add(m[2]);
		movies3.add(m[1]);
		movies4.add(m[0]);
		movies4.add(m[0]);

		c[0].setMovies(movies1);
		c[1].setMovies(movies2);
		c[2].setMovies(movies3);
		c[3].setMovies(movies4);
	}

	private void addCastToMovies(ArrayList<Cast> cast1, ArrayList<Cast> cast2,
			ArrayList<Cast> cast3) {
		cast1.add(c[0]);
		cast1.add(c[1]);
		cast1.add(c[3]);
		cast2.add(c[1]);
		cast2.add(c[3]);
		cast2.add(c[2]);
		cast3.add(c[0]);
		cast3.add(c[1]);
		cast3.add(c[2]);

		m[0].setCast(cast1);
		m[1].setCast(cast2);
		m[2].setCast(cast3);
	}
}