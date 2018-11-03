package com.velasco.brewer.service.event.beer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.velasco.brewer.storage.PhotoStorage;

@Component
public class BeerListener {

	@Autowired
	private PhotoStorage photoStorage;
	
	@EventListener(condition = "#event.hasPhoto()")
	public void beerSaved(BeerSavedEvent event) {
		photoStorage.save(event.getBeer().getPhoto());
	}
	
}
