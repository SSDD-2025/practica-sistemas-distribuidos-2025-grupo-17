package es.codeurjc.web.dto.cast;

import java.util.List;

public record CreateCastDTO(

        String name,
        String biography,
        String birthDate,
        String originCountry,
        List<Long> movieIds

) {
}
