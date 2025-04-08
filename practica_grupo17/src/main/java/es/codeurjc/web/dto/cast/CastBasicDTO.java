package es.codeurjc.web.dto.cast;

public record CastBasicDTO(

        Long id,
        String name,
        String birthDate,
        String originCountry) {
}
