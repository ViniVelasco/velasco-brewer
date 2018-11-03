package com.velasco.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.velasco.brewer.model.Beer;
import com.velasco.brewer.repository.Beers;
import com.velasco.brewer.service.event.beer.BeerSavedEvent;

@Service
public class BeerRegisterService {
	
	@Autowired
	private Beers beers;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void save(Beer beer) {
		beers.save(beer);
		
		publisher.publishEvent(new BeerSavedEvent(beer));
	}

}
