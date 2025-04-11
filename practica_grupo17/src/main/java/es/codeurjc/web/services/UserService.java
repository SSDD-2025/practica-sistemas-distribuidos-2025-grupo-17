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
import es.codeurjc.web.dto.review.ReviewDTO;
import es.codeurjc.web.dto.user.CreateUserDTO;
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

	public Collection<ReviewDTO> getReviews(User user){
		return reviewMapper.toDTOs(user.getReviews());
	}

	public List<UserDTO> findAllDTO() {
		return userMapper.toDTOs(userRepository.findAll());
	}

	public UserDTO findDTOById(long id) {
		return userMapper.toDTO(userRepository.findById(id).orElseThrow());
	}

	public UserDTO createFromDTO(CreateUserDTO dto) {
		User user = userMapper.toDomain(dto);
		userRepository.save(user);
		return userMapper.toDTO(user);
	}
}
