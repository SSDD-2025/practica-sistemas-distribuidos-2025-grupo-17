package es.codeurjc.web.dto.review;

import es.codeurjc.web.dto.movie.MovieBasicDTO;
import es.codeurjc.web.dto.user.UserBasicDTO;

import es.codeurjc.web.dto.movie.MovieBasicDTO;
import es.codeurjc.web.dto.user.UserBasicDTO;

public record ReviewDTO(

    Long id,
    String title,
    String text,
    UserBasicDTO author,
    MovieBasicDTO movie)
{}
