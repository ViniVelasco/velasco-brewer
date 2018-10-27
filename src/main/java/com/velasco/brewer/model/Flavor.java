package com.velasco.brewer.model;

public enum Flavor {
	ADOCICADA("Adocicada"),
	AMARGA("Amarga"),
	FORTE("Forte"), 
	FRUTADA("Frutada"),
	SUAVE("Suave");
	
	private String description;
	
	Flavor(String description){
		this.description = description;
	}
	
	public String getDescricao() {
		return description;
	}
}
