package es.codeurjc.web.mapper;

import es.codeurjc.web.dto.user.UserDTO;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;

import es.codeurjc.web.dto.user.CreateUserDTO;
import es.codeurjc.web.dto.user.UserBasicDTO;
import es.codeurjc.web.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    List<UserDTO> toDTOs(Collection<User> users);

    User toDomain(UserDTO userDTO);

    List<UserDTO> toDTO(List<User> all);

    User toDomain(CreateUserDTO user);

    CreateUserDTO toCreateUserRequest(User user);

    UserBasicDTO toUserBasicDTO(User updatedUser);
}
