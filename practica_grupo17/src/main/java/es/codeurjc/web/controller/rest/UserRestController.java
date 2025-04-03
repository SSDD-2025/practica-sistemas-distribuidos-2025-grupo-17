package es.codeurjc.web.controller.rest;

import java.net.URI;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.codeurjc.web.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import es.codeurjc.web.entities.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
	public ResponseEntity<User> me(HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		
		if(principal != null) {
			return ResponseEntity.ok(userService.findByUsername(principal.getName()).orElseThrow());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(user);
    }

    @DeleteMapping("/me")
    public User deleteUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        userService.deleteById(user.getId());
        return user;
    }

    @PutMapping("/me")
    public User replaceUser(HttpServletRequest request, @RequestBody User updatedUser) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        updatedUser.setId(user.getId());
        userService.save(updatedUser);
        return updatedUser;
    }

}