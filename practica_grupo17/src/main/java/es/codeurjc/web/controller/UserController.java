package es.codeurjc.web.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.web.entities.User;
import es.codeurjc.web.services.UserService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/myProfile")
    public String showMyProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> optionalUser = userService.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
            if (user.getRoles().contains("ADMIN")) {
                model.addAttribute("admin", true);
            }
            if (user.getRoles().contains("USER")) {
                model.addAttribute("registered", true);
            }
        }
        return "my_profile_template";
    }

    @GetMapping("/user/new")
    public String newUserForm(Model model) {
        return "new_or_modify_user_template";
    }

    @PostMapping("/user/new")
    public String newUser(Model model, @RequestParam String username, @RequestParam String password,
            @RequestParam String role) throws IOException {
        userService.save(new User(username, passwordEncoder.encode(password), role));
        return "user_created_template";
    }

    @PostMapping("/user/delete")
    public String deleteUser(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> optionalUser = userService.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userService.deleteReviews(user);
            userService.deleteById(user.getId());
        }
        return "user_deleted_template";
    }

    @GetMapping("/user/modify")
    public String modifyUserForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> optionalUser = userService.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
        }
        return "new_or_modify_user_template";
    }

    @PostMapping("/user/modify")
    public String modifyUser(Model model, @RequestParam String username,
            @RequestParam String password) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usernameAuth = auth.getName();
        Optional<User> optionalUser = userService.findByUsername(usernameAuth);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            userService.save(user);
        }
        return "user_modified_template";
    }

}
