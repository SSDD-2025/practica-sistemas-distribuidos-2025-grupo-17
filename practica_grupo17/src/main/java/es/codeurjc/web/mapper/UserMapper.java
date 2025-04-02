package es.codeurjc.web.mapper;

import es.codeurjc.web.dto.user.UserDTO;
import es.codeurjc.web.dto.user.CreateUserDTO;
import es.codeurjc.web.entities.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles());
        return dto;
    }

    public static User fromCreateDTO(CreateUserDTO dto) {
        // Asignamos rol por defecto: "USER"
        return new User(dto.getUsername(), dto.getPassword(), "USER");
    }
}
