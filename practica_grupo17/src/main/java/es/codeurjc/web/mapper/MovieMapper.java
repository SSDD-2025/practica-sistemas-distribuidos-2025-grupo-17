package es.codeurjc.web.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;

import es.codeurjc.web.dto.movie.CreateMovieDTO;
import es.codeurjc.web.dto.movie.MovieBasicDTO;
import es.codeurjc.web.dto.movie.MovieDTO;
import es.codeurjc.web.entities.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO toDTO(Movie movie);

    List<MovieDTO> toDTOs(Collection<Movie> movies);

    Movie toDomain(MovieDTO movieDTO);

    List<MovieDTO> toDTO(List<Movie> all);

    Movie toDomain(CreateMovieDTO film);

    CreateMovieDTO toCreateMovieRequest(Movie movie);

    MovieBasicDTO toMovieBasicDTO(Movie updatedMovie);

}
