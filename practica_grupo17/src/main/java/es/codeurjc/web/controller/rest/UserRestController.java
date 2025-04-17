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
import es.codeurjc.web.entities.*;
import es.codeurjc.web.mapper.UserMapper;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/me")
	public UserDTO me(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
		User user=userService.findByUsername(principal.getName()).orElseThrow();
		
		return userMapper.toDTO(user);
	}

    @PostMapping("/")
    public UserDTO createUser(@RequestBody User user) {
        userService.save(user);
        return userMapper.toDTO(user);
    }

    @DeleteMapping("/me")
    public UserDTO deleteUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        userService.deleteById(user.getId());
        return userMapper.toDTO(user);
    }

    @PutMapping("/me")
    public UserDTO replaceUser(HttpServletRequest request, @RequestBody User updatedUser) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        updatedUser.setId(user.getId());
        userService.save(updatedUser);
        return userMapper.toDTO(updatedUser);
    }

}