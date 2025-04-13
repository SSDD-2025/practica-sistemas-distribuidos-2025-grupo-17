package es.codeurjc.web.controller.rest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.web.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import es.codeurjc.web.dto.user.UserDTO;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public UserDTO me(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        UserDTO user = userService.findByUsername(principal.getName());

        return user;
    }

    @PostMapping("/")
    public UserDTO createUser(@RequestBody UserDTO user) {
        return userService.save(user);
    }

    @DeleteMapping("/me")
    public UserDTO deleteUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        UserDTO user = userService.findByUsername(principal.getName());
        userService.deleteById(user.id());
        return user;
    }

    @PutMapping("/me")
    public UserDTO replaceUser(HttpServletRequest request, @RequestBody UserDTO toUpdateUser) {
        Principal principal = request.getUserPrincipal();
        UserDTO user = userService.findByUsername(principal.getName());
        UserDTO updatedUser = new UserDTO(user.id(), toUpdateUser.username(), toUpdateUser.roles(),
                toUpdateUser.reviews());
        return userService.save(updatedUser);
    }

}