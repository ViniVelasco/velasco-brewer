package com.velasco.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.velasco.brewer.model.City;

public class CityConverter implements Converter<String, City> {

	@Override
	public City convert(String source) {
		if(!StringUtils.isEmpty(source)) {
			City city = new City();
			city.setId(Long.valueOf(source));
			return city;
		}
		
		return null;
	}

}
