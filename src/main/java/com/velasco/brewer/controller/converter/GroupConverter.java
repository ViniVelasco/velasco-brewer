package com.velasco.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.velasco.brewer.model.Grupo;

public class GroupConverter implements Converter<String, Grupo> {

	@Override
	public Grupo convert(String id) {
		if(!StringUtils.isEmpty(id)) {
			Grupo group = new Grupo();
			group.setId(Long.valueOf(id));
			return group;
		}
		return null;
	}

}
