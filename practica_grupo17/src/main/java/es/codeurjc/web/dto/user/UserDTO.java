package es.codeurjc.web.dto.user;

import java.util.List;

import es.codeurjc.web.dto.review.ReviewBasicDTO;

public record UserDTO (

        Long id,
        String username,
        List<String> roles,
        List<ReviewBasicDTO> reviews)
{
}
