package es.codeurjc.web.dto.movie;

import java.util.List;

public record CreateMovieDTO (

    String name,
    String argument,
    int year,
    String trailer,
    List<Long> castIds
){}
