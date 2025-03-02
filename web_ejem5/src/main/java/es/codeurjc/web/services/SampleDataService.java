package es.codeurjc.web.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.web.entities.Movie;
import es.codeurjc.web.repository.MoviesRepository;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;

@Service
public class SampleDataService {
  @Autowired
  private MovieService movieRepository;

  @PostConstruct
  public void init(){

        // Recuperamos las películas para asociarlas a los actores
        List<Movie> allMovies = movieRepository.findAll();

        if (allMovies.size() < 3) {
            System.out.println("No hay suficientes películas en la BD. Agrega más antes de inicializar el cast.");
            return;
        }

        Movie movie1 = allMovies.get(0);
        Movie movie2 = allMovies.get(1);
        Movie movie3 = allMovies.get(2);

        List<Cast> castMembers = Arrays.asList(
            new Cast("Leonardo DiCaprio", "Ganador del Oscar, actor icónico de Hollywood.", "1974-11-11", "USA", null),
            new Cast("Scarlett Johansson", "Actriz famosa por sus roles en películas de acción y drama.", "1984-11-22", "USA", null),
            new Cast("Robert Downey Jr.", "Conocido por su papel como Iron Man en el UCM.", "1965-04-04", "USA", null),
            new Cast("Natalie Portman", "Ganadora del Oscar por 'Black Swan'.", "1981-06-09", "Israel", null),
            new Cast("Brad Pitt", "Actor y productor de Hollywood, ha ganado varios premios.", "1963-12-18", "USA", null),
            new Cast("Meryl Streep", "Reconocida como una de las mejores actrices de todos los tiempos.", "1949-06-22", "USA", null),
            new Cast("Denzel Washington", "Actor de gran trayectoria en Hollywood.", "1954-12-28", "USA", null),
            new Cast("Tom Hanks", "Ganador de múltiples premios de la Academia.", "1956-07-09", "USA", null),
            new Cast("Emma Stone", "Actriz ganadora del Oscar por 'La La Land'.", "1988-11-06", "USA", null),
            new Cast("Joaquin Phoenix", "Actor reconocido por su papel en 'Joker'.", "1974-10-28", "Puerto Rico", null)
        );

        castRepository.saveAll(castMembers);
    
        Movie inception  = new Movie("Inception","A thief who enters the dreams of others to steal secrets.",new Date(2010 - 1900, 6, 16), castMembers, "https://www.youtube.com/watch?v=LifqWf0BAOA" );
        saveMovieWithURLImage(inception, castMembers, "/images/inception.jpg");
        
  }
private Movie saveMovieWithURLImage(Movie movie,  List<Cast> castMembersForMovie, String image) throws IOException {
  movie.setImageFile(imageUtils.localImageBlob("images/"+ image));
  return moviesService.save(movie,null);

  }
}
