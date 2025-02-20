package es.codeurjc.web.services;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.codeurjc.web.entities.User;

@Service
public class UserService {

	private ConcurrentMap<Long, User> users = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();

	public UserService() {
		//save(new Review("Pepe", "Vendo moto", "Barata, barata"));
	}

	public Collection<User> findAll() {
		return users.values();
	}

	public User findById(long id) {
		return users.get(id);
	}

	public void save(User user) {

		long id = nextId.getAndIncrement();

		user.setId(id);

		users.put(id, user);
	}

	public void deleteById(long id) {
		users.remove(id);
	}

}