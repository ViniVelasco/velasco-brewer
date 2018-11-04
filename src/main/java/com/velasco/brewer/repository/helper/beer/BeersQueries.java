package com.velasco.brewer.repository.helper.beer;

import com.velasco.brewer.repository.filter.BeerFilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.velasco.brewer.model.Beer;

public interface BeersQueries {

	public Page<Beer> filter(BeerFilter filter, Pageable pageable);
}
