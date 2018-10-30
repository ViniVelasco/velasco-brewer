package com.velasco.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.velasco.brewer.exception.NameStyleAlreadyRegisteredException;
import com.velasco.brewer.model.Style;
import com.velasco.brewer.repository.Styles;

@Service
public class StyleRegisterService {
	
	@Autowired
	private Styles styles;
	
	@Transactional
	public Style save(Style style) {
		Optional<Style> styleOptional = styles.findByNameIgnoreCase(style.getName());
		
		if(styleOptional.isPresent()) {
			throw new NameStyleAlreadyRegisteredException("Nome do estilo já cadastrado");
		}
		
		return styles.saveAndFlush(style);
	}

}
