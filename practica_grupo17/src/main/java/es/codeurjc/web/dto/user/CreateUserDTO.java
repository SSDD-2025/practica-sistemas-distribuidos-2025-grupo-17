package es.codeurjc.web.dto.user;

import java.util.List;

public record CreateUserDTO(
        String username,
        String password,
        List<String> roles) {
}
