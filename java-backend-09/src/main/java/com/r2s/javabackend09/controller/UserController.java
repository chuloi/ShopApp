package com.r2s.javabackend09.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.javabackend09.dto.request.UserRequestDTO;
import com.r2s.javabackend09.dto.response.UserResponseDTO;
import com.r2s.javabackend09.exception.UserAlreadyExistsException;
import com.r2s.javabackend09.exception.UserNotFoundException;
import com.r2s.javabackend09.exception.ValidationException;
import com.r2s.javabackend09.model.User;
import com.r2s.javabackend09.service.UserService;
import com.r2s.javabackend09.utils.ResponseCode;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping(path = "")
	public ResponseEntity<?> getAll() {
		return BaseResponseController.success(this.userService.getAll());
	}

	@GetMapping(path = "/findById")
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getByRequestParam(
			@RequestParam(name = "id", required = false, defaultValue = "0") int id) {
		try {
			User foundUser = this.userService.findById(id);
			return BaseResponseController.success(new UserResponseDTO(foundUser));
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

	@PostMapping("")
	public ResponseEntity<?> addUser(@RequestBody UserRequestDTO user) {
		try {
			User addedUser = this.userService.saveUser(user);
			return BaseResponseController.success(new UserResponseDTO(addedUser));
		} catch (UserAlreadyExistsException e) {
			return BaseResponseController.fail(ResponseCode.USER_ALREADY_EXISTS.getCode(),
					ResponseCode.USER_ALREADY_EXISTS.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

//
	@PutMapping(path = "")
	public ResponseEntity<?> updateUser(@RequestBody User newUser) {
		try {
			User updatedUser = this.userService.updateUser(newUser);
			return BaseResponseController.success(updatedUser);
		} catch (UserAlreadyExistsException e) {
			return BaseResponseController.fail(ResponseCode.USER_ALREADY_EXISTS.getCode(),
					ResponseCode.USER_ALREADY_EXISTS.getMessage());
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "id") int id) {
		try {
			return BaseResponseController.success(this.userService.deleteUser(id));
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		}
	}
	
	@PostMapping("/register-user")
	public ResponseEntity<?> signUp(@RequestBody UserRequestDTO userDTO) {
		return this.addUser(userDTO);
	}

	
}
