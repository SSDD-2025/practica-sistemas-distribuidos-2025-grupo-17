package es.codeurjc.web.services;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;

import es.codeurjc.web.entities.*;
import es.codeurjc.web.repository.CastRepository;
import es.codeurjc.web.repository.MoviesRepository;
import es.codeurjc.web.repository.ReviewRepository;
import es.codeurjc.web.repository.UserRepository;

import java.io.IOException;

@Service
public class SampleDataService {
	// Users
	@Value("${security.user}")
	private String username;

	@Value("${security.encodedPassword}")
	private String password;

	//Autowireds

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MoviesRepository moviesRepository;

	@Autowired
	private CastRepository castRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	// Samples
	private Cast c[] = new Cast[4];
	private Movie m[] = new Movie[3];
	private Review r[] = new Review[7];

	@PostConstruct
	public void init() throws IOException{

		User admin=new User(username,password, "ADMIN");
		userRepository.save(admin);
		User user=new User("user",passwordEncoder.encode("passUser"), "USER");
        userRepository.save(user);
		User otherUser=new User("otherUser",passwordEncoder.encode("passOtherUser"),"USER");
        userRepository.save(otherUser);

		try {
			// Cast
			c = initCast();
			// Movies
			m = initMovies();
			// Reviews
			r = initReviews(admin,user,otherUser);

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

			//Save cast and movies
			castRepository.save(c[0]);
			castRepository.save(c[1]);
			castRepository.save(c[2]);
			castRepository.save(c[3]);
			moviesRepository.save(m[0]);
			moviesRepository.save(m[1]);
			moviesRepository.save(m[2]);
			reviewRepository.save(r[0]);
			reviewRepository.save(r[1]);
			reviewRepository.save(r[2]);
			reviewRepository.save(r[3]);
			reviewRepository.save(r[4]);
			reviewRepository.save(r[5]);
			reviewRepository.save(r[6]);

			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Cast[] initCast() throws IOException{
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
		Resource image1 = new ClassPathResource("/static/cast_images/image_1.jpg");
		Resource image2 = new ClassPathResource("/static/cast_images/image_0.jpg");
		Resource image3 = new ClassPathResource("/static/cast_images/image_3.jpg");
		Resource image4 = new ClassPathResource("/static/cast_images/image_2.jpg");
		cInit[0].setCastImage(BlobProxy.generateProxy(image1.getInputStream(), image1.contentLength()));
		cInit[1].setCastImage(BlobProxy.generateProxy(image2.getInputStream(), image2.contentLength()));
		cInit[2].setCastImage(BlobProxy.generateProxy(image3.getInputStream(), image3.contentLength()));
		cInit[3].setCastImage(BlobProxy.generateProxy(image4.getInputStream(), image4.contentLength()));
		return cInit;
	}

	private Movie[] initMovies() throws IOException {
		String movieName1 = "Mortadelo y Filemon Contra Jimmy El Cachondo",
				movieName2 = "Torrente 10",
				movieName3 = "Vengadores: Guardianes de las patatas";
		String movieArg1 = "La T.I.A acaba de sufrir uno de sus peores asaltos criminales, un ataque de risa en toda regla perpetrado por Jimmy el Cachondo para poder hacerse con un documento ultrasecreto.",
				movieArg2 = "Torrente sale de la cárcel en el año 2018 para encontrarse una España totalmente diferente de la que él conocía. Ante este 'shock' decide pasar a ser un 'fuera de la ley' y atracar un casino.",
				movieArg3 = "Taki y Mitsuha descubren un día que durante el sueño sus cuerpos se intercambian, y comienzan a comunicarse por medio de notas.";
		int movieYear1 = 2000,
				movieYear2 = 3015,
				movieYear3 = 2014;
		String movieTrailer1 = "https://www.youtube.com/watch?v=qz0TDMd_cB0",
				movieTrailer2 = "https://www.youtube.com/watch?v=qz0TDMd_cB0",
				movieTrailer3 = "https://www.youtube.com/watch?v=qz0TDMd_cB0";
		Movie mInit[] = new Movie[3];
		mInit[0] = new Movie(movieName1, movieArg1, movieYear1, movieTrailer1);
		mInit[1] = new Movie(movieName2, movieArg2, movieYear2, movieTrailer2);
		mInit[2] = new Movie(movieName3, movieArg3, movieYear3, movieTrailer3);
		Resource image1 = new ClassPathResource("/static/movies_images/image_0.jpg");
		Resource image2 = new ClassPathResource("/static/movies_images/image_1.jpg");
		Resource image3 = new ClassPathResource("/static/movies_images/image_2.jpg");
		mInit[0].setMovieImage(BlobProxy.generateProxy(image1.getInputStream(), image1.contentLength()));
		mInit[1].setMovieImage(BlobProxy.generateProxy(image2.getInputStream(), image2.contentLength()));
		mInit[2].setMovieImage(BlobProxy.generateProxy(image3.getInputStream(), image3.contentLength()));
		return mInit;
	}

	private Review[] initReviews(User admin, User user, User otherUser) {
		String reviewTitle1 = "Muy buena",
				reviewTitle2 = "Salí llorando",
				reviewTitle3 = "Casi me duermo",
				reviewTitle4 = "No estuvo nada, nada mal";
		String reviewText1 = "La verdad me ha sorprendido mucho, que profunda...",
				reviewText2 = "Me rociaron gas pimienta y salí llorando. 0/10",
				reviewText3 = "Llevo 6 días sin poder dormir y vine a la peli para curar mi insomnio",
				reviewText4 = "Ni tan mal, vine buscando cobre y en efecto encontré cobre, pero de buena calidad.";
		Review rInit[] = new Review[7];
		rInit[0] = new Review(reviewTitle1, reviewText1, m[0], admin);
		rInit[1] = new Review(reviewTitle2, reviewText2, m[0], user);
		rInit[2] = new Review(reviewTitle3, reviewText3, m[1], otherUser);
		rInit[3] = new Review(reviewTitle4, reviewText4, m[2], admin);
		rInit[4] = new Review(reviewTitle1, reviewText1, m[2], user);
		rInit[5] = new Review(reviewTitle4, reviewText4, m[0], otherUser);
		rInit[6] = new Review(reviewTitle3, reviewText3, m[1], user);
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