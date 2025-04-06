package es.codeurjc.web.mapper;

import es.codeurjc.web.dto.cast.CastDTO;
import es.codeurjc.web.dto.cast.CastBasicDTO;
import es.codeurjc.web.dto.cast.CreateCastDTO;
import es.codeurjc.web.dto.movie.MovieBasicDTO;
import es.codeurjc.web.entities.Cast;
import es.codeurjc.web.entities.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CastMapper {

    public static CastDTO toDTO(Cast cast) {
        return new CastDTO(
                cast.getId(),
                cast.getName(),
                cast.getBiography(),
                cast.getBirthDate(),
                cast.getOriginCountry(),
                cast.getMovies().stream()
                        .map(movie -> new MovieBasicDTO(movie.getId(), movie.getName(), movie.getYear(), movie.getArgument()))
                        .collect(Collectors.toList())
        );
    }

    public static CastBasicDTO toBasicDTO(Cast cast) {
        return new CastBasicDTO(
                cast.getId(),
                cast.getName(),
                cast.getBirthDate(),
                cast.getOriginCountry()
        );
    }

    public static Cast fromCreateDTO(CreateCastDTO dto, List<Movie> movies) {
        Cast cast = new Cast(dto.getName(), dto.getBiography(), dto.getBirthDate(), dto.getOriginCountry());
        for (Movie movie : movies) {
            cast.addMovie(movie);
        }
        return cast;
    }
}
