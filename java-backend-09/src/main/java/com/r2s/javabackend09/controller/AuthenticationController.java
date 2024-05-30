package com.r2s.javabackend09.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.javabackend09.dto.request.AuthRequest;
import com.r2s.javabackend09.dto.response.AuthResponse;
import com.r2s.javabackend09.exception.UserNotFoundException;
import com.r2s.javabackend09.exception.ValidationException;
import com.r2s.javabackend09.model.User;
import com.r2s.javabackend09.model.UserSessionManager;
import com.r2s.javabackend09.repository.UserRepository;
import com.r2s.javabackend09.service.UserService;
import com.r2s.javabackend09.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserSessionManager sessionManager;

	@PostMapping("/login")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) {
	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

	    String token = JwtUtils.generateToken(authRequest.getUsername());

	    User userSession = new User();
	    userSession.setUserName(authRequest.getUsername());
	    userSession.setPassWord(authRequest.getPassword());
	    sessionManager.addSession(userSession);

	    AuthResponse authResponse = new AuthResponse(token, "Đăng nhập thành công");
	    return BaseResponseController.success(authResponse);
	}
	@GetMapping("/getuserlogin")
	public List<User> getUserLogin() {
	    List<User> loggedInUsers = sessionManager.getLoggedInUsers();
	    return loggedInUsers;
	}
	
}
