package com.velasco.brewer.repository.helper.user;

import java.util.List;
import java.util.Optional;

import com.velasco.brewer.model.User;


public interface UsersQueries {

	public Optional<User> byEmailAndActive(String email);
	
	public List<String> permissions(User user);
	
}
