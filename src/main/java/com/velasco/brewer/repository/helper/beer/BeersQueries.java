package com.velasco.brewer.repository.helper.beer;

import com.velasco.brewer.repository.filter.BeerFilter;

import java.util.List;

import com.velasco.brewer.model.Beer;

public interface BeersQueries {

	public List<Beer> filter(BeerFilter filter);
}
