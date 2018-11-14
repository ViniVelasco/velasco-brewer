package com.velasco.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.velasco.brewer.model.City;
import com.velasco.brewer.model.State;
import com.velasco.brewer.repository.helper.cities.CitiesQueries;

public interface Cities extends JpaRepository<City, Long>, CitiesQueries {

	public List<City> findByStateId(Long id);

	public Optional<City> findByNameAndState(String name, State state);
}
