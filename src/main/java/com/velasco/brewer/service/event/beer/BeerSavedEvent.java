package com.velasco.brewer.service.event.beer;

import org.springframework.util.StringUtils;

import com.velasco.brewer.model.Beer;

public class BeerSavedEvent {

	private Beer beer;

	public BeerSavedEvent(Beer beer) {
		super();
		this.beer = beer;
	}

	public Beer getBeer() {
		return beer;
	}
	
	public boolean hasPhoto() {
		return !StringUtils.isEmpty(beer.getPhoto());
	}
	
}
