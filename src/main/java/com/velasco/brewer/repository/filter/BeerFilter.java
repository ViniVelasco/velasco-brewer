package com.velasco.brewer.repository.filter;

import java.math.BigDecimal;


import com.velasco.brewer.model.Flavor;
import com.velasco.brewer.model.Origin;
import com.velasco.brewer.model.Style;

public class BeerFilter {
	
	private String sku;
	private String name;
	private Style style;
	private Flavor flavor;
	private Origin origin;
	private BigDecimal valueTo;
	private BigDecimal valueIn;
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	public Flavor getFlavor() {
		return flavor;
	}
	public void setFlavor(Flavor flavor) {
		this.flavor = flavor;
	}
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	public BigDecimal getValueTo() {
		return valueTo;
	}
	public void setValueTo(BigDecimal valueTo) {
		this.valueTo = valueTo;
	}
	public BigDecimal getValueIn() {
		return valueIn;
	}
	public void setValueIn(BigDecimal valueIn) {
		this.valueIn = valueIn;
	}

}
