package com.velasco.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.velasco.brewer.model.User;

@Repository
public interface Users extends JpaRepository<User, Long> {

	public Optional<User> findByEmailIgnoreCase(String email);
}
