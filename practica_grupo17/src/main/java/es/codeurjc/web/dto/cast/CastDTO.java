package es.codeurjc.web.dto.cast;

import java.util.List;
import es.codeurjc.web.dto.movie.MovieBasicDTO;


public record CastDTO (

    Long id,
    String name,
    String biography,
    String birthDate,
    String originCountry,
    List<MovieBasicDTO> movies,
    String image)
{}
