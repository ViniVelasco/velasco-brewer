package com.velasco.brewer.repository.helper.style;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.velasco.brewer.model.Style;
import com.velasco.brewer.repository.filter.StyleFilter;

public interface StylesQueries {
	public Page<Style> filter(StyleFilter filter, Pageable pageable);
}
