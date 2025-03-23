package es.codeurjc.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.codeurjc.web.entities.User;
import es.codeurjc.web.services.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Nombre de usuario o contraseña incorrectos");
        }
        return "login_template";
    }

    @PostMapping("/perform_login")
    public String login(RedirectAttributes redirectAttributes, @RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.get().getPassword())) {            
            return "redirect:/"; // Redirect to home page
        } else {
            return "redirect:/login?error=true";
        }
    }
}
