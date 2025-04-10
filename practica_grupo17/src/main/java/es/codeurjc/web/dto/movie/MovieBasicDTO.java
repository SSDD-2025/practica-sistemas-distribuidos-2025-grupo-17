package es.codeurjc.web.dto.movie;

public record MovieBasicDTO(
        Long id,
        String name,
        int year,
        String argument) {
}