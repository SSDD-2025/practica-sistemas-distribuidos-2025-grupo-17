package es.codeurjc.web.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.web.entities.Review;
import es.codeurjc.web.entities.User;
import es.codeurjc.web.dto.user.UserDTO;
import es.codeurjc.web.dto.user.CreateUserDTO;
import es.codeurjc.web.mapper.UserMapper;
import es.codeurjc.web.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReviewService reviewService;

	public UserService() {

	}

	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public boolean exist(long id) {
		return userRepository.existsById(id);
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void deleteById(long id) {
		userRepository.deleteById(id);
	}

	public void deleteReviews(User user) {
		List<Review> reviews = user.getReviews();
		for (Review review : reviews) {
			reviewService.deleteById(review.getId());
		}
	}

	public List<UserDTO> findAllDTO() {
		return userRepository.findAll().stream()
			.map(UserMapper::toDTO)
			.collect(Collectors.toList());
	}

	public Optional<UserDTO> findDTOById(long id) {
		return userRepository.findById(id)
			.map(UserMapper::toDTO);
	}

	public UserDTO createFromDTO(CreateUserDTO dto) {
		User user = UserMapper.fromCreateDTO(dto);
		userRepository.save(user);
		return UserMapper.toDTO(user);
	}
}
