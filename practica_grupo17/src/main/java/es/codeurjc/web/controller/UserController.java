package es.codeurjc.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.web.entities.User;
import es.codeurjc.web.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/myProfile")
    public String showMyProfile(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Optional<User> optionalUser = userService.findByUsername(username);
		if (optionalUser.isPresent()) {
            User user=optionalUser.get();
			model.addAttribute("user", user);
            if (user.getRoles().contains("ADMIN")){
                model.addAttribute("admin", true);
            }
            if (user.getRoles().contains("USER")){
                model.addAttribute("user", true);
            }
		}
		return "my_profile_template";
    }

}
