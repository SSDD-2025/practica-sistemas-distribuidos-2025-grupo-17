package es.codeurjc.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import es.codeurjc.web.entities.User;
import es.codeurjc.web.repository.UserRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseUsersLoader {

    @Value("${security.user}")
    private String username;

    @Value("${security.encodedPassword}")
    private String password;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void initDatabase() {
        userRepository.save(new User(username,password, "ADMIN"));
        userRepository.save(new User("user",passwordEncoder.encode("passUser"), "USER"));
    }

}
