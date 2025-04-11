package es.codeurjc.web.dto.user;

import java.util.List;

public record UserBasicDTO(

        Long id,
        String username,
        String password,
        List<String> roles) {

}
