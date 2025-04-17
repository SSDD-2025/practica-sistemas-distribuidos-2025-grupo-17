package es.codeurjc.web.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.web.entities.Review;
import es.codeurjc.web.entities.User;
import es.codeurjc.web.dto.user.UserDTO;
import es.codeurjc.web.dto.review.ReviewDTO;
import es.codeurjc.web.mapper.ReviewMapper;
import es.codeurjc.web.mapper.UserMapper;
import es.codeurjc.web.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewMapper reviewMapper;

	@Autowired
	private UserMapper userMapper;

	public UserService() {

	}

	public Collection<UserDTO> findAll() {
		return toDTOs(userRepository.findAll());
	}

	public UserDTO findById(long id) {
		return toDTO(userRepository.findById(id).orElseThrow());
	}

	public UserDTO findByUsername(String username) {
		return toDTO(userRepository.findByUsername(username).orElseThrow());
	}

	public boolean exist(long id) {
		return userRepository.existsById(id);
	}

	public UserDTO save(UserDTO user) {
		return toDTO(userRepository.save(userMapper.toDomain(user)));
	}

	public UserDTO save(String username, String password, String role) {
		return toDTO(userRepository.save(new User(username, password, role)));
	}

	public UserDTO deleteById(long id) {
		UserDTO userDTO = toDTO(userRepository.findById(id).orElseThrow());
		userRepository.deleteById(id);
		return userDTO;
	}

	public void deleteReviews(UserDTO userDTO) {
		List<Review> reviews = userDTO.reviews();
		User user = userRepository.findByUsername(userDTO.username()).orElseThrow();
		for (Review review : reviews) {
			reviewService.deleteById(review.getId(), toDTO(user));
		}
	}

	public UserDTO update(String oldUsername, String newPassword, String newUsername) {
		User oldUser = userRepository.findByUsername(oldUsername).orElseThrow();
		oldUser.setUsername(newUsername);
		oldUser.setPassword(newPassword);
		return toDTO(userRepository.save(oldUser));
	}

	public Collection<ReviewDTO> getReviews(UserDTO user) {
		return reviewMapper.toDTOs(user.reviews());
	}

	private UserDTO toDTO(User user) {
		return userMapper.toDTO(user);
	}

	private Collection<UserDTO> toDTOs(Collection<User> users) {
		return userMapper.toDTOs(users);
	}
}
