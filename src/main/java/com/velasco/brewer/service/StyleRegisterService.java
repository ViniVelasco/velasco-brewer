package com.velasco.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.velasco.brewer.model.Style;
import com.velasco.brewer.repository.Styles;

@Service
public class StyleRegisterService {
	
	@Autowired
	private Styles styles;
	
	@Transactional
	public void save(Style style) {
		styles.save(style);
	}

}
