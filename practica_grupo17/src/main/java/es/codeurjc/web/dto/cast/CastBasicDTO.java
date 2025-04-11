package es.codeurjc.web.dto.cast;

public record CastBasicDTO(

        Long id,
        String name,
        String biography,
        String birthDate,
        String originCountry) {
}
