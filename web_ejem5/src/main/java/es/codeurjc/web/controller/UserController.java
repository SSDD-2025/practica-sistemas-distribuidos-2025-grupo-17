package es.codeurjc.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.web.services.*;
import es.codeurjc.web.entities.*;

@Controller
public class UserController {

    @Autowired
	private MoviesService moviesService;
	
	@Autowired
	private CastService castService;

    @Autowired
	private UserService userService;

    @GetMapping("/user/{id}")
	public String showUser(Model model, @PathVariable long id) {

		User user = userService.findById(id);

		model.addAttribute("user", user);

		return "user_template";
	}

    @GetMapping("/user/new")
	public String newUserForm(Model model) {
		return "new_user_template";
	}
	
	@PostMapping("/user/new")
	public String newUser(Model model, @RequestParam String userName,@RequestParam String userPassword,@RequestParam boolean userAdmin) throws IOException {

		User user=new User(userName,userPassword,userAdmin);
		userService.save(user);

		model.addAttribute("movies", moviesService.findAll());
		model.addAttribute("cast",castService.findAll());

		return "home_template";
	}

    @PostMapping("/user/{id}/delete")
	public String deleteUser(Model model, @PathVariable long id) throws IOException {

		userService.deleteById(id);

		model.addAttribute("movies", moviesService.findAll());
		model.addAttribute("cast",castService.findAll());

		return "home_template";
	}
}