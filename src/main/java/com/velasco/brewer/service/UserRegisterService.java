package com.velasco.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.velasco.brewer.model.User;
import com.velasco.brewer.repository.Users;
import com.velasco.brewer.service.exception.EmailUserAlreadyRegisteredException;

@Service
public class UserRegisterService {
	
	@Autowired
	private Users users;

	@Transactional
	public void save(User user) {
		Optional<User> existentClient = users.findByEmailIgnoreCase(user.getEmail());
		if(existentClient.isPresent()) {
			throw new EmailUserAlreadyRegisteredException("Email já cadastrado");
		}
		
		users.save(user);
	}
}
