package es.codeurjc.web.dto.movie;

import java.util.List;
import es.codeurjc.web.dto.cast.CastBasicDTO;
import es.codeurjc.web.dto.review.ReviewBasicDTO;

public record MovieDTO(

        Long id,
        String name,
        String argument,
        int year,
        String trailer,
        List<CastBasicDTO> cast,
        List<ReviewBasicDTO> reviews,
        String image

) {}