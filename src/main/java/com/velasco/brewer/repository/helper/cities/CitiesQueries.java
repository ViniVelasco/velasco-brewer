package com.velasco.brewer.repository.helper.cities;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.velasco.brewer.model.City;
import com.velasco.brewer.repository.filter.CityFilter;

public interface CitiesQueries {
	public Page<City> filter(CityFilter filter, Pageable pageable);
}
