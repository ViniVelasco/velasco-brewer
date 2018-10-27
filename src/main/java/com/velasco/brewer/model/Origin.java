package com.velasco.brewer.model;

public enum Origin {
	
	NACIONAL("Nacional"),
	INTERNACIONAL("Internacional");
	
	private String description;
	
	Origin(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

}
