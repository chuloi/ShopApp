package com.r2s.javabackend09.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.r2s.javabackend09.model.User;
import com.r2s.javabackend09.repository.UserRepository;

@Service
public class CustomUserServiceDetail implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(username);
		if (user.isPresent()) {
			return new CustomUserDetail(user.get());
		} else {
			throw new RuntimeException("User not found");
		}
	}
}
