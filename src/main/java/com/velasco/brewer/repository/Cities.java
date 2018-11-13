package com.velasco.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.velasco.brewer.model.City;

public interface Cities extends JpaRepository<City, Long> {

	public List<City> findByStateId(Long id);

	public Optional<City> findByNameIgnoreCase(String name);
}
