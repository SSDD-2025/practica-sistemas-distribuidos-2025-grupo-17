package es.codeurjc.web.dto.user;

import java.util.List;

import es.codeurjc.web.entities.Review;

public record UserDTO (

        Long id,
        String username,
        String password,
        List<String> roles,
        List<Review> reviews)
{
}
