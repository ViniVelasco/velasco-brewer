package com.velasco.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.velasco.brewer.model.User;
import com.velasco.brewer.repository.helper.user.UsersQueries;

@Repository
public interface Users extends JpaRepository<User, Long>, UsersQueries {

	public Optional<User> findByEmailIgnoreCase(String email);
	
}
