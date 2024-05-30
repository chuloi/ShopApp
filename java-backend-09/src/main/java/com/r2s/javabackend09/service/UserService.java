package com.r2s.javabackend09.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.r2s.javabackend09.dto.request.UserRequestDTO;
import com.r2s.javabackend09.dto.response.UserResponseDTO;
import com.r2s.javabackend09.exception.UserAlreadyExistsException;
import com.r2s.javabackend09.exception.UserNotFoundException;
import com.r2s.javabackend09.exception.ValidationException;
import com.r2s.javabackend09.model.User;
import com.r2s.javabackend09.repository.RoleRepository;
import com.r2s.javabackend09.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<UserResponseDTO> getAll() {
		return this.userRepository.findAll().stream().map(UserResponseDTO::new).toList();
	}

	private void validateUser(User user) throws ValidationException {
		if (Objects.isNull(user)) {
			throw new ValidationException("user is null");
		}

	

		if (Objects.isNull(user.getName()) || user.getName().isBlank()) {
			throw new ValidationException("user.name cannot be blank");
		}

		if (Objects.isNull(user.getUserName()) || user.getUserName().isBlank()) {
			throw new ValidationException("user.userName cannot be blank");
		}
	}

	private void validateUser(UserRequestDTO user) throws ValidationException {
		if (Objects.isNull(user)) {
			throw new ValidationException("user is null");
		}


		if (Objects.isNull(user.getName()) || user.getName().isBlank()) {
			throw new ValidationException("user.name cannot be blank");
		}

		if (Objects.isNull(user.getUserName()) || user.getUserName().isBlank()) {
			throw new ValidationException("user.userName cannot be blank");
		}

		if (Objects.isNull(user.getPassWord()) || user.getPassWord().isBlank()) {
			throw new ValidationException("user.passWord cannot be blank");
		}
	}

	public User saveUser(UserRequestDTO user) throws UserAlreadyExistsException, ValidationException {
		this.validateUser(user);

		Optional<User> foundUser = this.userRepository.findByUserName(user.getUserName());
		if (foundUser.isPresent()) {
			throw new UserAlreadyExistsException();
		}

		User insertedUser = user.toUser();
		insertedUser.setPassWord(this.passwordEncoder.encode(user.getPassWord()));
		insertedUser.setRoles(List.of(this.roleRepository.findById(2).get()));
		return this.userRepository.save(insertedUser);
	}

	public User findById(Integer id) throws ValidationException, UserNotFoundException {
		if (Objects.isNull(id) || id <= 0) {
			throw new ValidationException("user.id must be positive");
		}

		Optional<User> foundUser = this.userRepository.findById(id);
		if (foundUser.isEmpty()) {
			throw new UserNotFoundException();
		}

		return foundUser.get();
	}

	public List<User> findByName(String name, Pageable pageable) throws ValidationException {
		if (Objects.isNull(name)) {
			throw new ValidationException("name cannot be null");
		}

		if (name.isBlank()) {
			return this.userRepository.findAll(pageable).stream().map(x -> x).toList();
		}
		return this.userRepository.findByNameContains(name, pageable);
	}
	

	public User updateUser(User newUser) throws ValidationException, UserNotFoundException, UserAlreadyExistsException {
		validateUser(newUser);

		Optional<User> foundUser = this.userRepository.findById(newUser.getId());
		if (foundUser.isEmpty()) {
			throw new UserNotFoundException();
		}

		foundUser = this.userRepository.findByUserName(newUser.getUserName());
		if (foundUser.isPresent() && !newUser.getId().equals(foundUser.get().getId())) {
			throw new UserAlreadyExistsException();
		}

		return this.userRepository.save(newUser);
	}

	public boolean deleteUser(Integer id) throws UserNotFoundException {
		Optional<User> foundUser = this.userRepository.findById(id);
		if (foundUser.isEmpty()) {
			throw new UserNotFoundException();
		}

		this.userRepository.deleteById(id);
		return true;
	}


}
