package com.velasco.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.velasco.brewer.model.User;
import com.velasco.brewer.repository.Users;
import com.velasco.brewer.service.exception.EmailUserAlreadyRegisteredException;
import com.velasco.brewer.service.exception.PasswordRequiredUserException;

@Service
public class UserRegisterService  {
	
	@Autowired
	private Users users;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void save(User user) {
		Optional<User> existentClient = users.findByEmailIgnoreCase(user.getEmail());
		
		if(existentClient.isPresent()) {
			throw new EmailUserAlreadyRegisteredException("Email já cadastrado");
		}
		
		if (user.isNew() && StringUtils.isEmpty(user.getPassword())) {
			throw new PasswordRequiredUserException("Senha é obrigatória para novo usuário");
		}
		
		if(user.isNew()) {
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			user.setConfirmPassword(user.getPassword());
		}
		
		users.save(user);
	}
}
