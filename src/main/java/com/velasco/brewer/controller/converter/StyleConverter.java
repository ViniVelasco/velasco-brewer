package com.velasco.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.velasco.brewer.model.Style;

public class StyleConverter implements Converter<String, Style> {

	@Override
	public Style convert(String source) {
		if(!StringUtils.isEmpty(source)) {
			Style style = new Style();
			style.setId(Long.valueOf(source));
			return style;
		}
		
		return null;
	}

}
