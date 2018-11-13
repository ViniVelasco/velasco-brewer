package com.velasco.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.velasco.brewer.model.City;
import com.velasco.brewer.repository.Cities;
import com.velasco.brewer.service.exception.NameCityAlreadyRegisteredException;

@Service
public class CitiesRegisterService {
	
	@Autowired
	private Cities cities;
	
	@Transactional
	public void save(City city) {
		Optional<City> citiesOptional = cities.findByNameIgnoreCase(city.getName());
		
		if(citiesOptional.isPresent()) {
			throw new NameCityAlreadyRegisteredException("Nome da cidade já cadastrada");
		}
		
		cities.save(city);
	}

}