package es.codeurjc.web.dto.movie;

public record MovieBasicDTO(

        Long id,
        String name,
        String argument,
        int year,
        String trailer

) {}
