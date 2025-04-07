package es.codeurjc.web.dto.user;

import java.util.List;

import es.codeurjc.web.entities.Review;

public record UserDTO (

        long id,
        String username,
        String password,
        List<String> roles,
        List<Review> reviews)
{
}
