package com.jamesheld.oboestore.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jamesheld.oboestore.dto.UserRegistrationDto;
import com.jamesheld.oboestore.models.User;



public interface UserService extends UserDetailsService {
	User findByEmail(String email);
	User save(UserRegistrationDto registration);
}
