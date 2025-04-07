package es.codeurjc.web.mapper;

import es.codeurjc.web.dto.user.UserDTO;
import es.codeurjc.web.dto.user.CreateUserDTO;
import es.codeurjc.web.entities.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRoles(),user.getReviews());
        return userDTO;
    }

    public static User fromCreateDTO(CreateUserDTO dto) {
        return new User(dto.username(), dto.password(), "USER");
    }
}
