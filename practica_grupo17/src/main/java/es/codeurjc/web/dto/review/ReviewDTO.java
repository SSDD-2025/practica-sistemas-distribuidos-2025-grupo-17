package es.codeurjc.web.dto.review;

public record ReviewDTO (

    Long id,
    String title,
    String text,
    String authorUsername,
    Long movieId)
{}
