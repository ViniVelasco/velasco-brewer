package com.velasco.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.velasco.brewer.model.Beer;
import com.velasco.brewer.repository.Beers;

@Service
public class BeerRegisterService {
	
	@Autowired
	private Beers beers;
	
	@Transactional
	public void save(Beer beer) {
		beers.save(beer);
	}

}
