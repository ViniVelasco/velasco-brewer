package com.velasco.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.velasco.brewer.model.State;

public class StateConverter implements Converter<String, State> {

	@Override
	public State convert(String source) {
		if(!StringUtils.isEmpty(source)) {
			State state = new State();
			state.setId(Long.valueOf(source));
			return state;
		}
		
		return null;
	}

}
