package com.velasco.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.velasco.brewer.model.State;

public interface States extends JpaRepository<State, Long> {
	
	public Optional<State> findByNameIgnoreCase(String name);
	
}
