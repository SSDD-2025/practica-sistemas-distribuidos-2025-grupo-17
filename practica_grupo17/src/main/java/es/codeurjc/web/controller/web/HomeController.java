package es.codeurjc.web.controller.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.web.dto.user.UserDTO;
import es.codeurjc.web.services.*;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String showMoviesList(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			UserDTO userDTO = userService.findByUsername(principal.getName());
			model.addAttribute("user", userDTO);
			model.addAttribute("admin", userDTO.roles().contains("ADMIN"));
			model.addAttribute("registered", userDTO.roles().contains("REGISTERED"));
			model.addAttribute("logged", true);
		}

		return "home_template";
	}
	
}
