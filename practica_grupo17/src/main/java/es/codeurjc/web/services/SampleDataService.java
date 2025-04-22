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
	private Cast c[] = new Cast[20];
	private Movie m[] = new Movie[20];
	private Review r[] = new Review[60];

	@PostConstruct
	public void init() throws IOException {
		User admin = new User(username, password, "ADMIN");
		userRepository.save(admin);
		User user = new User("user", passwordEncoder.encode("passUser"), "USER");
		userRepository.save(user);
		User otherUser = new User("otherUser", passwordEncoder.encode("passOtherUser"), "USER");
		userRepository.save(otherUser);

		try {
			// Cast
			c = initCast();
			// Movies
			m = initMovies();
			// Reviews
			r = initReviews(admin, user, otherUser);

			// Movie list
			ArrayList<Movie> movies1 = new ArrayList<>(),
					movies2 = new ArrayList<>(),
					movies3 = new ArrayList<>(),
					movies4 = new ArrayList<>(),
					movies5 = new ArrayList<>(),
					movies6 = new ArrayList<>(),
					movies7 = new ArrayList<>(),
					movies8 = new ArrayList<>(),
					movies9 = new ArrayList<>(),
					movies10 = new ArrayList<>(),
					movies11 = new ArrayList<>(),
					movies12 = new ArrayList<>(),
					movies13 = new ArrayList<>(),
					movies14 = new ArrayList<>(),
					movies15 = new ArrayList<>(),
					movies16 = new ArrayList<>(),
					movies17 = new ArrayList<>(),
					movies18 = new ArrayList<>(),
					movies19 = new ArrayList<>(),
					movies20 = new ArrayList<>();

			// Add movies list to cast entities
			addMoviesToCast(
					movies1, movies2, movies3, movies4, movies5,
					movies6, movies7, movies8, movies9, movies10,
					movies11, movies12, movies13, movies14, movies15,
					movies16, movies17, movies18, movies19, movies20
			);

			// Cast list
			ArrayList<Cast> cast1 = new ArrayList<>(),
					cast2 = new ArrayList<>(),
					cast3 = new ArrayList<>(),
					cast4 = new ArrayList<>(),
					cast5 = new ArrayList<>(),
					cast6 = new ArrayList<>(),
					cast7 = new ArrayList<>(),
					cast8 = new ArrayList<>(),
					cast9 = new ArrayList<>(),
					cast10 = new ArrayList<>(),
					cast11 = new ArrayList<>(),
					cast12 = new ArrayList<>(),
					cast13 = new ArrayList<>(),
					cast14 = new ArrayList<>(),
					cast15 = new ArrayList<>(),
					cast16 = new ArrayList<>(),
					cast17 = new ArrayList<>(),
					cast18 = new ArrayList<>(),
					cast19 = new ArrayList<>(),
					cast20 = new ArrayList<>();

			// Add cast list to movies entities
			addCastToMovies(
					cast1, cast2, cast3, cast4,
					cast5, cast6, cast7, cast8,
					cast9, cast10, cast11, cast12,
					cast13, cast14, cast15, cast16,
					cast17, cast18, cast19, cast20
			);

			// Save cast, movies, and reviews
			for (Cast cast : c) {
				castRepository.save(cast);
			}
			for (Movie movie : m) {
				moviesRepository.save(movie);
			}
			for (Review review : r) {
				reviewRepository.save(review);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("Error al inicializar datos: " + e.getMessage(), e);
		}
	}

	private Cast[] initCast() throws IOException {
		String castName1 = "Robert Downey Jr",
				castName2 = "Maximiliano Cloritix",
				castName3 = "Santiago Segura",
				castName4 = "Pablo Motos",
				castName5 = "Emma Watson",
				castName6 = "Leonardo DiCaprio",
				castName7 = "Scarlett Johansson",
				castName8 = "Morgan Freeman",
				castName9 = "Tom Hanks",
				castName10 = "Natalie Portman",
				castName11 = "Keanu Reeves",
				castName12 = "Jennifer Lawrence",
				castName13 = "Will Smith",
				castName14 = "Angelina Jolie",
				castName15 = "Christian Bale",
				castName16 = "Meryl Streep",
				castName17 = "Brad Pitt",
				castName18 = "Johnny Depp",
				castName19 = "Gal Gadot",
				castName20 = "Dwayne Johnson";

		String castBio1 = "Robert John Downey Jr. es un actor, productor y cantante estadounidense. Inició su carrera a temprana edad en varios filmes dirigidos por su padre, Robert Downey Sr., y en su infancia estudió actuación en varias academias de Nueva York.",
				castBio2 = "Genio inventor y gran actor, mueve masas como el que más.",
				castBio3 = "Santiago Segura Silva es un actor y cineasta español, popular por su saga de Torrente.",
				castBio4 = "Pablo Motos Burgos es un actor de televisión, locutor de radio, humorista y empresario español. Desde 2006 es el presentador y actor del programa de entrevistas La madriguera.",
				castBio5 = "Emma Charlotte Duerre Watson es una actriz, modelo y activista británica conocida por interpretar a Hermione Granger en la saga de Harry Potter.",
				castBio6 = "Leonardo Wilhelm DiCaprio es un actor y productor de cine estadounidense. Conocido por Titanic, El lobo de Wall Street, entre otros.",
				castBio7 = "Scarlett Ingrid Johansson es una actriz y cantante estadounidense. Es una de las actrices mejor pagadas del mundo desde 2018.",
				castBio8 = "Morgan Freeman es un actor, director y narrador estadounidense, conocido por su voz distintiva y presencia en pantalla.",
				castBio9 = "Thomas Jeffrey Hanks es un actor y cineasta estadounidense. Es uno de los actores más reconocidos de Hollywood.",
				castBio10 = "Natalie Portman es una actriz y directora nacida en Israel y nacionalizada estadounidense. Ganadora del Óscar por Black Swan.",
				castBio11 = "Keanu Charles Reeves es un actor, director, productor y músico canadiense. Conocido por sus papeles en The Matrix y John Wick.",
				castBio12 = "Jennifer Shrader Lawrence es una actriz estadounidense. Fue la actriz mejor pagada del mundo en 2015 y 2016.",
				castBio13 = "Willard Carroll Smith Jr. es un actor, rapero y productor estadounidense. Ha sido nominado a varios premios Óscar y Grammy.",
				castBio14 = "Angelina Jolie Voight es una actriz, directora y activista estadounidense. Ha recibido múltiples premios, incluidos un Óscar y tres Globos de Oro.",
				castBio15 = "Christian Charles Philip Bale es un actor británico conocido por sus intensas transformaciones físicas para sus papeles.",
				castBio16 = "Mary Louise 'Meryl' Streep es una actriz estadounidense. Es ampliamente considerada como una de las mejores actrices de todos los tiempos.",
				castBio17 = "William Bradley Pitt es un actor y productor de cine estadounidense. Ha ganado múltiples premios, incluido un Óscar.",
				castBio18 = "John Christopher Depp II es un actor, productor y músico estadounidense. Es conocido por su papel como Jack Sparrow en Piratas del Caribe.",
				castBio19 = "Gal Gadot-Varsano es una actriz, productora y modelo israelí. Es conocida por interpretar a Wonder Woman.",
				castBio20 = "Dwayne Douglas Johnson, conocido como 'The Rock', es un actor, productor y exluchador profesional estadounidense.";

		String castBirthDate1 = "1965-06-05",
				castBirthDate2 = "2034-12-24",
				castBirthDate3 = "1900-1-6",
				castBirthDate4 = "1867-12-12",
				castBirthDate5 = "1990-04-15",
				castBirthDate6 = "1974-11-11",
				castBirthDate7 = "1984-11-22",
				castBirthDate8 = "1937-06-01",
				castBirthDate9 = "1956-07-09",
				castBirthDate10 = "1981-06-09",
				castBirthDate11 = "1964-09-02",
				castBirthDate12 = "1990-08-15",
				castBirthDate13 = "1968-09-25",
				castBirthDate14 = "1975-06-04",
				castBirthDate15 = "1974-01-30",
				castBirthDate16 = "1949-06-22",
				castBirthDate17 = "1963-12-18",
				castBirthDate18 = "1963-06-09",
				castBirthDate19 = "1985-04-30",
				castBirthDate20 = "1972-05-02";

		String castCountry1 = "EE.UU",
				castCountry2 = "España",
				castCountry3 = "Uzbekistán",
				castCountry4 = "India",
				castCountry5 = "Reino Unido",
				castCountry6 = "EE.UU",
				castCountry7 = "EE.UU",
				castCountry8 = "EE.UU",
				castCountry9 = "EE.UU",
				castCountry10 = "Israel",
				castCountry11 = "Canadá",
				castCountry12 = "EE.UU",
				castCountry13 = "EE.UU",
				castCountry14 = "EE.UU",
				castCountry15 = "Reino Unido",
				castCountry16 = "EE.UU",
				castCountry17 = "EE.UU",
				castCountry18 = "EE.UU",
				castCountry19 = "Israel",
				castCountry20 = "EE.UU";

		Cast cInit[] = new Cast[20];
		cInit[0] = new Cast(castName1, castBio1, castBirthDate1, castCountry1);
		cInit[1] = new Cast(castName2, castBio2, castBirthDate2, castCountry2);
		cInit[2] = new Cast(castName3, castBio3, castBirthDate3, castCountry3);
		cInit[3] = new Cast(castName4, castBio4, castBirthDate4, castCountry4);
		cInit[4] = new Cast(castName5, castBio5, castBirthDate5, castCountry5);
		cInit[5] = new Cast(castName6, castBio6, castBirthDate6, castCountry6);
		cInit[6] = new Cast(castName7, castBio7, castBirthDate7, castCountry7);
		cInit[7] = new Cast(castName8, castBio8, castBirthDate8, castCountry8);
		cInit[8] = new Cast(castName9, castBio9, castBirthDate9, castCountry9);
		cInit[9] = new Cast(castName10, castBio10, castBirthDate10, castCountry10);
		cInit[10] = new Cast(castName11, castBio11, castBirthDate11, castCountry11);
		cInit[11] = new Cast(castName12, castBio12, castBirthDate12, castCountry12);
		cInit[12] = new Cast(castName13, castBio13, castBirthDate13, castCountry13);
		cInit[13] = new Cast(castName14, castBio14, castBirthDate14, castCountry14);
		cInit[14] = new Cast(castName15, castBio15, castBirthDate15, castCountry15);
		cInit[15] = new Cast(castName16, castBio16, castBirthDate16, castCountry16);
		cInit[16] = new Cast(castName17, castBio17, castBirthDate17, castCountry17);
		cInit[17] = new Cast(castName18, castBio18, castBirthDate18, castCountry18);
		cInit[18] = new Cast(castName19, castBio19, castBirthDate19, castCountry19);
		cInit[19] = new Cast(castName20, castBio20, castBirthDate20, castCountry20);

		Resource image1 = new ClassPathResource("/static/cast_images/image_1.jpg");
		Resource image2 = new ClassPathResource("/static/cast_images/image_0.jpg");
		Resource image3 = new ClassPathResource("/static/cast_images/image_3.jpg");
		Resource image4 = new ClassPathResource("/static/cast_images/image_2.jpg");
		Resource image5 = new ClassPathResource("/static/cast_images/image_4.jpg");
		Resource image6 = new ClassPathResource("/static/cast_images/image_5.jpg");
		Resource image7 = new ClassPathResource("/static/cast_images/image_6.jpg");
		Resource image8 = new ClassPathResource("/static/cast_images/image_7.jpg");
		Resource image9 = new ClassPathResource("/static/cast_images/image_8.jpg");
		Resource image10 = new ClassPathResource("/static/cast_images/image_9.jpg");
		Resource image11 = new ClassPathResource("/static/cast_images/image_10.jpg");
		Resource image12 = new ClassPathResource("/static/cast_images/image_11.jpg");
		Resource image13 = new ClassPathResource("/static/cast_images/image_12.jpg");
		Resource image14 = new ClassPathResource("/static/cast_images/image_13.jpg");
		Resource image15 = new ClassPathResource("/static/cast_images/image_14.jpg");
		Resource image16 = new ClassPathResource("/static/cast_images/image_15.jpg");
		Resource image17 = new ClassPathResource("/static/cast_images/image_16.jpg");
		Resource image18 = new ClassPathResource("/static/cast_images/image_17.jpg");
		Resource image19 = new ClassPathResource("/static/cast_images/image_18.jpg");
		Resource image20 = new ClassPathResource("/static/cast_images/image_19.jpg");

		cInit[0].setCastImage(BlobProxy.generateProxy(image1.getInputStream(), image1.contentLength()));
		cInit[1].setCastImage(BlobProxy.generateProxy(image2.getInputStream(), image2.contentLength()));
		cInit[2].setCastImage(BlobProxy.generateProxy(image3.getInputStream(), image3.contentLength()));
		cInit[3].setCastImage(BlobProxy.generateProxy(image4.getInputStream(), image4.contentLength()));
		cInit[4].setCastImage(BlobProxy.generateProxy(image5.getInputStream(), image5.contentLength()));
		cInit[5].setCastImage(BlobProxy.generateProxy(image6.getInputStream(), image6.contentLength()));
		cInit[6].setCastImage(BlobProxy.generateProxy(image7.getInputStream(), image7.contentLength()));
		cInit[7].setCastImage(BlobProxy.generateProxy(image8.getInputStream(), image8.contentLength()));
		cInit[8].setCastImage(BlobProxy.generateProxy(image9.getInputStream(), image9.contentLength()));
		cInit[9].setCastImage(BlobProxy.generateProxy(image10.getInputStream(), image10.contentLength()));
		cInit[10].setCastImage(BlobProxy.generateProxy(image11.getInputStream(), image11.contentLength()));
		cInit[11].setCastImage(BlobProxy.generateProxy(image12.getInputStream(), image12.contentLength()));
		cInit[12].setCastImage(BlobProxy.generateProxy(image13.getInputStream(), image13.contentLength()));
		cInit[13].setCastImage(BlobProxy.generateProxy(image14.getInputStream(), image14.contentLength()));
		cInit[14].setCastImage(BlobProxy.generateProxy(image15.getInputStream(), image15.contentLength()));
		cInit[15].setCastImage(BlobProxy.generateProxy(image16.getInputStream(), image16.contentLength()));
		cInit[16].setCastImage(BlobProxy.generateProxy(image17.getInputStream(), image17.contentLength()));
		cInit[17].setCastImage(BlobProxy.generateProxy(image18.getInputStream(), image18.contentLength()));
		cInit[18].setCastImage(BlobProxy.generateProxy(image19.getInputStream(), image19.contentLength()));
		cInit[19].setCastImage(BlobProxy.generateProxy(image20.getInputStream(), image20.contentLength()));

		return cInit;
	}


	private Movie[] initMovies() throws IOException {
		String movieName1 = "Mortadelo y Filemon Contra Jimmy El Cachondo",
				movieName2 = "Torrente 10",
				movieName3 = "Vengadores: Guardianes de las patatas",
				movieName4 = "Iron Man",
				movieName5 = "Avengers: Endgame",
				movieName6 = "Lucy",
				movieName7 = "La La Land",
				movieName8 = "The Amazing Spider-Man",
				movieName9 = "Doctor Strange",
				movieName10 = "Deadpool",
				movieName11 = "Barbie",
				movieName12 = "Oppenheimer",
				movieName13 = "Interstellar",
				movieName14 = "Dune",
				movieName15 = "Tenet",
				movieName16 = "Mad Max: Fury Road",
				movieName17 = "Gladiator",
				movieName18 = "El laberinto del fauno",
				movieName19 = "Inception",
				movieName20 = "The Grand Budapest Hotel";

		String movieArg1 = "La T.I.A acaba de sufrir uno de sus peores asaltos criminales, un ataque de risa en toda regla perpetrado por Jimmy el Cachondo para poder hacerse con un documento ultrasecreto.",
				movieArg2 = "Torrente sale de la cárcel en el año 2018 para encontrarse una España totalmente diferente de la que él conocía. Ante este 'shock' decide pasar a ser un 'fuera de la ley' y atracar un casino.",
				movieArg3 = "Taki y Mitsuha descubren un día que durante el sueño sus cuerpos se intercambian, y comienzan a comunicarse por medio de notas.",
				movieArg4 = "Después de ser rescatado por un grupo de terroristas, un millonario fabricante de armas crea una armadura mecanizada para luchar contra el crimen.",
				movieArg5 = "Después de los devastadores eventos de 'Avengers: Infinity War', los Vengadores restantes se enfrentan a su mayor desafío aún.",
				movieArg6 = "Una joven se convierte en una poderosa portadora de habilidades sobrehumanas tras una operación experimental.",
				movieArg7 = "Un pianista de jazz y una aspirante a actriz se enamoran mientras persiguen sus sueños en Los Ángeles.",
				movieArg8 = "Peter Parker gana habilidades arácnidas y lucha por encontrar su lugar como superhéroe en Nueva York.",
				movieArg9 = "Stephen Strange descubre un mundo oculto de magia y dimensiones alternativas tras un accidente automovilístico.",
				movieArg10 = "Un ex-agente con un sentido del humor muy peculiar se convierte en el antihéroe más irreverente de Marvel.",
				movieArg11 = "Barbie se aventura fuera de Barbieland hacia el mundo real y vive una experiencia que cambiará su vida.",
				movieArg12 = "La historia del físico J. Robert Oppenheimer y su papel en el desarrollo de la bomba atómica.",
				movieArg13 = "Un grupo de astronautas viaja a través de un agujero de gusano en busca de un nuevo hogar para la humanidad.",
				movieArg14 = "Un joven noble se convierte en el mesías de un planeta desértico en medio de una guerra interplanetaria.",
				movieArg15 = "Un agente especial debe manipular el flujo del tiempo para prevenir el fin del mundo.",
				movieArg16 = "En un mundo post-apocalíptico, Max se une a una rebelde para huir de un tirano y liberar a su pueblo.",
				movieArg17 = "Un general romano traicionado busca venganza en la arena como gladiador.",
				movieArg18 = "Una niña escapa del horror de la guerra civil española a través de un oscuro mundo de fantasía.",
				movieArg19 = "Un ladrón especializado en robar secretos entra en los sueños de sus víctimas.",
				movieArg20 = "Un conserje de hotel y su joven aprendiz se ven envueltos en una loca aventura durante los años 30.";

		int movieYear1 = 2000,
				movieYear2 = 3015,
				movieYear3 = 2014,
				movieYear4 = 2008,
				movieYear5 = 2019,
				movieYear6 = 2014,
				movieYear7 = 2016,
				movieYear8 = 2012,
				movieYear9 = 2016,
				movieYear10 = 2016,
				movieYear11 = 2023,
				movieYear12 = 2023,
				movieYear13 = 2014,
				movieYear14 = 2021,
				movieYear15 = 2020,
				movieYear16 = 2015,
				movieYear17 = 2000,
				movieYear18 = 2006,
				movieYear19 = 2010,
				movieYear20 = 2014;

		String movieTrailer1 = "https://www.youtube.com/watch?v=qz0TDMd_cB0",
				movieTrailer2 = "https://www.youtube.com/watch?v=qz0TDMd_cB0",
				movieTrailer3 = "https://www.youtube.com/watch?v=qz0TDMd_cB0",
				movieTrailer4 = "https://www.youtube.com/watch?v=8ugaeA-nMTc",
				movieTrailer5 = "https://www.youtube.com/watch?v=TcMBFSGVi1c",
				movieTrailer6 = "https://www.youtube.com/watch?v=MVt32qoyhi0",
				movieTrailer7 = "https://www.youtube.com/watch?v=0pdqf4P9MB8",
				movieTrailer8 = "https://www.youtube.com/watch?v=atCfTRMyjGU",
				movieTrailer9 = "https://www.youtube.com/watch?v=HSzx-zryEgM",
				movieTrailer10 = "https://www.youtube.com/watch?v=ONHBaC-pfsk",
				movieTrailer11 = "https://www.youtube.com/watch?v=pBk4NYhWNMM",
				movieTrailer12 = "https://www.youtube.com/watch?v=uYPbbksJxIg",
				movieTrailer13 = "https://www.youtube.com/watch?v=zSWdZVtXT7E",
				movieTrailer14 = "https://www.youtube.com/watch?v=n9xhJrPXop4",
				movieTrailer15 = "https://www.youtube.com/watch?v=L3pk_TBkihU",
				movieTrailer16 = "https://www.youtube.com/watch?v=hEJnMQG9ev8",
				movieTrailer17 = "https://www.youtube.com/watch?v=owK1qxDselE",
				movieTrailer18 = "https://www.youtube.com/watch?v=EqYiSlkvRuw",
				movieTrailer19 = "https://www.youtube.com/watch?v=YoHD9XEInc0",
				movieTrailer20 = "https://www.youtube.com/watch?v=1Fg5iWmQjwk";

		Movie[] mInit = new Movie[20];
		mInit[0] = new Movie(movieName1, movieArg1, movieYear1, movieTrailer1);
		mInit[1] = new Movie(movieName2, movieArg2, movieYear2, movieTrailer2);
		mInit[2] = new Movie(movieName3, movieArg3, movieYear3, movieTrailer3);
		mInit[3] = new Movie(movieName4, movieArg4, movieYear4, movieTrailer4);
		mInit[4] = new Movie(movieName5, movieArg5, movieYear5, movieTrailer5);
		mInit[5] = new Movie(movieName6, movieArg6, movieYear6, movieTrailer6);
		mInit[6] = new Movie(movieName7, movieArg7, movieYear7, movieTrailer7);
		mInit[7] = new Movie(movieName8, movieArg8, movieYear8, movieTrailer8);
		mInit[8] = new Movie(movieName9, movieArg9, movieYear9, movieTrailer9);
		mInit[9] = new Movie(movieName10, movieArg10, movieYear10, movieTrailer10);
		mInit[10] = new Movie(movieName11, movieArg11, movieYear11, movieTrailer11);
		mInit[11] = new Movie(movieName12, movieArg12, movieYear12, movieTrailer12);
		mInit[12] = new Movie(movieName13, movieArg13, movieYear13, movieTrailer13);
		mInit[13] = new Movie(movieName14, movieArg14, movieYear14, movieTrailer14);
		mInit[14] = new Movie(movieName15, movieArg15, movieYear15, movieTrailer15);
		mInit[15] = new Movie(movieName16, movieArg16, movieYear16, movieTrailer16);
		mInit[16] = new Movie(movieName17, movieArg17, movieYear17, movieTrailer17);
		mInit[17] = new Movie(movieName18, movieArg18, movieYear18, movieTrailer18);
		mInit[18] = new Movie(movieName19, movieArg19, movieYear19, movieTrailer19);
		mInit[19] = new Movie(movieName20, movieArg20, movieYear20, movieTrailer20);

		Resource image1 = new ClassPathResource("/static/movies_images/image_0.jpg");
		Resource image2 = new ClassPathResource("/static/movies_images/image_1.jpg");
		Resource image3 = new ClassPathResource("/static/movies_images/image_2.jpg");
		Resource image4 = new ClassPathResource("/static/movies_images/image_3.jpg");
		Resource image5 = new ClassPathResource("/static/movies_images/image_4.jpg");
		Resource image6 = new ClassPathResource("/static/movies_images/image_5.jpg");
		Resource image7 = new ClassPathResource("/static/movies_images/image_6.jpg");
		Resource image8 = new ClassPathResource("/static/movies_images/image_7.jpg");
		Resource image9 = new ClassPathResource("/static/movies_images/image_8.jpg");
		Resource image10 = new ClassPathResource("/static/movies_images/image_9.jpg");
		Resource image11 = new ClassPathResource("/static/movies_images/image_10.jpg");
		Resource image12 = new ClassPathResource("/static/movies_images/image_11.jpg");
		Resource image13 = new ClassPathResource("/static/movies_images/image_12.jpg");
		Resource image14 = new ClassPathResource("/static/movies_images/image_13.jpg");
		Resource image15 = new ClassPathResource("/static/movies_images/image_14.jpg");
		Resource image16 = new ClassPathResource("/static/movies_images/image_15.jpg");
		Resource image17 = new ClassPathResource("/static/movies_images/image_16.jpg");
		Resource image18 = new ClassPathResource("/static/movies_images/image_17.jpg");
		Resource image19 = new ClassPathResource("/static/movies_images/image_18.jpg");
		Resource image20 = new ClassPathResource("/static/movies_images/image_19.jpg");

		mInit[0].setMovieImage(BlobProxy.generateProxy(image1.getInputStream(), image1.contentLength()));
		mInit[1].setMovieImage(BlobProxy.generateProxy(image2.getInputStream(), image2.contentLength()));
		mInit[2].setMovieImage(BlobProxy.generateProxy(image3.getInputStream(), image3.contentLength()));
		mInit[3].setMovieImage(BlobProxy.generateProxy(image4.getInputStream(), image4.contentLength()));
		mInit[4].setMovieImage(BlobProxy.generateProxy(image5.getInputStream(), image5.contentLength()));
		mInit[5].setMovieImage(BlobProxy.generateProxy(image6.getInputStream(), image6.contentLength()));
		mInit[6].setMovieImage(BlobProxy.generateProxy(image7.getInputStream(), image7.contentLength()));
		mInit[7].setMovieImage(BlobProxy.generateProxy(image8.getInputStream(), image8.contentLength()));
		mInit[8].setMovieImage(BlobProxy.generateProxy(image9.getInputStream(), image9.contentLength()));
		mInit[9].setMovieImage(BlobProxy.generateProxy(image10.getInputStream(), image10.contentLength()));
		mInit[10].setMovieImage(BlobProxy.generateProxy(image11.getInputStream(), image11.contentLength()));
		mInit[11].setMovieImage(BlobProxy.generateProxy(image12.getInputStream(), image12.contentLength()));
		mInit[12].setMovieImage(BlobProxy.generateProxy(image13.getInputStream(), image13.contentLength()));
		mInit[13].setMovieImage(BlobProxy.generateProxy(image14.getInputStream(), image14.contentLength()));
		mInit[14].setMovieImage(BlobProxy.generateProxy(image15.getInputStream(), image15.contentLength()));
		mInit[15].setMovieImage(BlobProxy.generateProxy(image16.getInputStream(), image16.contentLength()));
		mInit[16].setMovieImage(BlobProxy.generateProxy(image17.getInputStream(), image17.contentLength()));
		mInit[17].setMovieImage(BlobProxy.generateProxy(image18.getInputStream(), image18.contentLength()));
		mInit[18].setMovieImage(BlobProxy.generateProxy(image19.getInputStream(), image19.contentLength()));
		mInit[19].setMovieImage(BlobProxy.generateProxy(image20.getInputStream(), image20.contentLength()));

		return mInit;
	}


	private Review[] initReviews(User admin, User user, User otherUser) {
		String reviewTitle1 = "Muy buena",
				reviewTitle2 = "Salí llorando",
				reviewTitle3 = "Casi me duermo",
				reviewTitle4 = "No estuvo nada, nada mal",
				reviewTitle5 = "De lo mejor que he visto",
				reviewTitle6 = "No era lo que esperaba",
				reviewTitle7 = "¡Una joya escondida!",
				reviewTitle8 = "Entretenida, sin más",
				reviewTitle9 = "Tremenda experiencia",
				reviewTitle10 = "Pensé que sería peor";

		String reviewText1 = "La verdad me ha sorprendido mucho, que profunda...",
				reviewText2 = "Me rociaron gas pimienta y salí llorando. 0/10",
				reviewText3 = "Llevo 6 días sin poder dormir y vine a la peli para curar mi insomnio",
				reviewText4 = "Ni tan mal, vine buscando cobre y en efecto encontré cobre, pero de buena calidad.",
				reviewText5 = "No esperaba nada y me dio todo. Bellísima.",
				reviewText6 = "Me quedé igual que cuando entré. Sin pena ni gloria.",
				reviewText7 = "Recomendada 100%. No entiendo por qué no es más famosa.",
				reviewText8 = "Tuvo sus momentos, pero nada que destaque demasiado.",
				reviewText9 = "Salí con una sonrisa en la cara. Qué peliculón.",
				reviewText10 = "Pensé que sería infumable, pero me sorprendió para bien.";

		Review rInit[] = new Review[60];
		/* Pre changes code
		rInit[0] = new Review(reviewTitle1, reviewText1, m[0], admin);
		rInit[1] = new Review(reviewTitle2, reviewText2, m[0], user);
		rInit[2] = new Review(reviewTitle3, reviewText3, m[1], otherUser);
		rInit[3] = new Review(reviewTitle4, reviewText4, m[2], admin);
		rInit[4] = new Review(reviewTitle1, reviewText1, m[2], user);
		rInit[5] = new Review(reviewTitle4, reviewText4, m[0], otherUser);
		rInit[6] = new Review(reviewTitle3, reviewText3, m[1], user);

		rInit[0] = new Review(reviewTitle1, reviewText1, m[0], admin);
		rInit[1] = new Review(reviewTitle2, reviewText2, m[0], user);
		rInit[2] = new Review(reviewTitle3, reviewText3, m[1], otherUser);
		*/

		String[] titles = {reviewTitle1, reviewTitle2, reviewTitle3, reviewTitle4,  reviewTitle5, reviewTitle6, reviewTitle7, reviewTitle8, reviewTitle9, reviewTitle10};
		String[] texts = {reviewText1, reviewText2, reviewText3, reviewText4, reviewText5, reviewText6, reviewText7, reviewText8, reviewText9, reviewText10};


		int index = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 3; j++) {
				String title = titles[(i * 3 + j) % titles.length];
				String text = texts[(i * 3 + j) % texts.length];
				if (i % 3 == 0) {
					rInit[index++] = new Review(title, text, m[i], admin);
				} else if (i % 3 == 1) {
					rInit[index++] = new Review(title, text, m[i], user);
				} else {
					rInit[index++] = new Review(title, text, m[i], otherUser);
				}
			}
		}



		return rInit;
	}

	private void addMoviesToCast(
			ArrayList<Movie> movies1, ArrayList<Movie> movies2, ArrayList<Movie> movies3,
			ArrayList<Movie> movies4, ArrayList<Movie> movies5, ArrayList<Movie> movies6,
			ArrayList<Movie> movies7, ArrayList<Movie> movies8, ArrayList<Movie> movies9,
			ArrayList<Movie> movies10, ArrayList<Movie> movies11, ArrayList<Movie> movies12,
			ArrayList<Movie> movies13, ArrayList<Movie> movies14, ArrayList<Movie> movies15,
			ArrayList<Movie> movies16, ArrayList<Movie> movies17, ArrayList<Movie> movies18,
			ArrayList<Movie> movies19, ArrayList<Movie> movies20
	) {

		movies1.add(m[0]);
		movies1.add(m[1]);
		movies1.add(m[2]);
		movies1.add(m[3]);
		movies1.add(m[7]);

		movies2.add(m[0]);
		movies2.add(m[2]);
		movies2.add(m[5]);
		movies2.add(m[6]);
		movies2.add(m[9]);

		movies3.add(m[1]);
		movies3.add(m[4]);
		movies3.add(m[8]);
		movies3.add(m[10]);

		movies4.add(m[0]);
		movies4.add(m[0]);
		movies4.add(m[3]);
		movies4.add(m[11]);
		movies4.add(m[12]);

		movies5.add(m[13]);
		movies5.add(m[4]);
		movies5.add(m[5]);
		movies5.add(m[14]);

		movies6.add(m[15]);
		movies6.add(m[6]);
		movies6.add(m[7]);
		movies6.add(m[8]);

		movies7.add(m[9]);
		movies7.add(m[16]);
		movies7.add(m[17]);
		movies7.add(m[2]);

		movies8.add(m[10]);
		movies8.add(m[11]);
		movies8.add(m[12]);
		movies8.add(m[3]);

		movies9.add(m[13]);
		movies9.add(m[14]);
		movies9.add(m[15]);
		movies9.add(m[5]);

		movies10.add(m[16]);
		movies10.add(m[17]);
		movies10.add(m[18]);
		movies10.add(m[19]);
		movies11.add(m[0]);
		movies11.add(m[4]);
		movies11.add(m[8]);
		movies11.add(m[12]);

		movies12.add(m[1]);
		movies12.add(m[5]);
		movies12.add(m[9]);
		movies12.add(m[13]);

		movies13.add(m[2]);
		movies13.add(m[6]);
		movies13.add(m[10]);
		movies13.add(m[14]);

		movies14.add(m[3]);
		movies14.add(m[7]);
		movies14.add(m[11]);
		movies14.add(m[15]);

		movies15.add(m[4]);
		movies15.add(m[8]);
		movies15.add(m[12]);

		movies15.add(m[16]);

		movies16.add(m[5]);
		movies16.add(m[9]);
		movies16.add(m[13]);
		movies16.add(m[17]);

		movies17.add(m[6]);
		movies17.add(m[10]);
		movies17.add(m[14]);
		movies17.add(m[18]);

		movies18.add(m[7]);
		movies18.add(m[11]);
		movies18.add(m[15]);
		movies18.add(m[19]);

		movies19.add(m[0]);
		movies19.add(m[8]);
		movies19.add(m[12]);
		movies19.add(m[16]);

		movies20.add(m[1]);
		movies20.add(m[9]);
		movies20.add(m[13]);
		movies20.add(m[17]);

		c[0].setMovies(movies1);
		c[1].setMovies(movies2);
		c[2].setMovies(movies3);
		c[3].setMovies(movies4);
		c[4].setMovies(movies5);
		c[5].setMovies(movies6);
		c[6].setMovies(movies7);
		c[7].setMovies(movies8);
		c[8].setMovies(movies9);
		c[9].setMovies(movies10);
		c[10].setMovies(movies11);
		c[11].setMovies(movies12);
		c[12].setMovies(movies13);
		c[13].setMovies(movies14);
		c[14].setMovies(movies15);
		c[15].setMovies(movies16);
		c[16].setMovies(movies17);
		c[17].setMovies(movies18);
		c[18].setMovies(movies19);
		c[19].setMovies(movies20);
	}

	private void addCastToMovies(ArrayList<Cast> cast1, ArrayList<Cast> cast2, ArrayList<Cast> cast3,
								 ArrayList<Cast> cast4, ArrayList<Cast> cast5, ArrayList<Cast> cast6,
								 ArrayList<Cast> cast7, ArrayList<Cast> cast8, ArrayList<Cast> cast9,
								 ArrayList<Cast> cast10, ArrayList<Cast> cast11, ArrayList<Cast> cast12,
								 ArrayList<Cast> cast13, ArrayList<Cast> cast14, ArrayList<Cast> cast15,
								 ArrayList<Cast> cast16, ArrayList<Cast> cast17, ArrayList<Cast> cast18,
								 ArrayList<Cast> cast19, ArrayList<Cast> cast20) {

		cast1.add(c[0]);
		cast1.add(c[1]);
		cast1.add(c[3]);

		cast2.add(c[1]);
		cast2.add(c[3]);
		cast2.add(c[2]);

		cast3.add(c[0]);
		cast3.add(c[1]);
		cast3.add(c[2]);

		cast4.add(c[0]);
		cast4.add(c[3]);
		cast4.add(c[7]);

		cast5.add(c[2]);
		cast5.add(c[4]);

		cast6.add(c[1]);
		cast6.add(c[5]);

		cast7.add(c[0]);
		cast7.add(c[5]);

		cast8.add(c[3]);
		cast8.add(c[7]);

		cast9.add(c[1]);
		cast9.add(c[6]);

		cast10.add(c[0]);
		cast10.add(c[6]);
		cast10.add(c[9]);

		cast11.add(c[3]);
		cast11.add(c[8]);

		cast12.add(c[2]);
		cast12.add(c[7]);

		cast13.add(c[5]);
		cast13.add(c[8]);

		cast14.add(c[0]);
		cast14.add(c[4]);

		cast15.add(c[9]);
		cast15.add(c[6]);

		cast16.add(c[7]);
		cast16.add(c[5]);

		cast17.add(c[4]);
		cast17.add(c[8]);

		cast18.add(c[3]);
		cast18.add(c[9]);

		cast19.add(c[2]);
		cast19.add(c[6]);

		cast20.add(c[1]);
		cast20.add(c[5]);

		m[0].setCast(cast1);
		m[1].setCast(cast2);
		m[2].setCast(cast3);
		m[3].setCast(cast4);
		m[4].setCast(cast5);
		m[5].setCast(cast6);
		m[6].setCast(cast7);
		m[7].setCast(cast8);
		m[8].setCast(cast9);
		m[9].setCast(cast10);
		m[10].setCast(cast11);
		m[11].setCast(cast12);
		m[12].setCast(cast13);
		m[13].setCast(cast14);
		m[14].setCast(cast15);
		m[15].setCast(cast16);
		m[16].setCast(cast17);
		m[17].setCast(cast18);
		m[18].setCast(cast19);
		m[19].setCast(cast20);
	}

}