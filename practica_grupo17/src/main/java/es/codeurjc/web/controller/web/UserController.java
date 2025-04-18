package es.codeurjc.web.controller.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.web.dto.user.UserDTO;
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
        UserDTO userDTO = userService.findByUsername(username);
        model.addAttribute("user", userDTO);
        if (userDTO.roles().contains("ADMIN")) {
            model.addAttribute("admin", true);
        }
        if (userDTO.roles().contains("USER")) {
            model.addAttribute("registered", true);
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
        if (userService.existsByUsername(username)){
            model.addAttribute("usernameNotValid", "Nombre de usuario ya existente, elija otro");
            return "new_or_modify_user_template";
        }
        userService.save(username, passwordEncoder.encode(password), role);
        return "user_created_template";
    }

    @PostMapping("/user/delete")
    public String deleteUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserDTO userDTO = userService.findByUsername(username);
        userService.deleteReviews(userDTO);
        userService.deleteById(userDTO.id());
        return "user_deleted_template";
    }

    @GetMapping("/user/modify")
    public String modifyUserForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserDTO userDTO = userService.findByUsername(username);
        model.addAttribute("user", userDTO);
        return "new_or_modify_user_template";
    }

    @PostMapping("/user/modify")
    public String modifyUser(Model model, @RequestParam String username,
            @RequestParam String password) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String oldUsername = auth.getName();
        if (userService.existsByUsername(username)&&!username.equals(oldUsername)){
            model.addAttribute("usernameNotValid", "Nombre de usuario ya existente, elija otro");
            UserDTO userDTO = userService.findByUsername(oldUsername);
            model.addAttribute("user", userDTO);
            return "new_or_modify_user_template";
        }
        String encodedPassword = passwordEncoder.encode(password);
        userService.update(oldUsername, encodedPassword, username);
        return "user_modified_template";
    }

}
