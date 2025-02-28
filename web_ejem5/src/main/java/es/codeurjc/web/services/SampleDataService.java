package es.codeurjc.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.web.entities.User;
import jakarta.annotation.PostConstruct;

@Service
public class SampleDataService {

	@Autowired
	private UserService userService; 
	
	@PostConstruct
	public void init() {
		User user = new User("user1", "password","admin");

		userService.save(user);
	}
}