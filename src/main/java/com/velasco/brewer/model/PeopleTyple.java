package com.velasco.brewer.model;

public enum PeopleTyple {
	
	FISICA("Física", "CPF", "000.000.000-00"),
	JURIDICA("Jurídica", "CNPJ", "00.000.000/0000-00");
	
	private String description;
	private String document;
	private String mask;
	
	PeopleTyple(String description, String document, String mask){
		this.description = description;
		this.document = document;
		this.mask = mask;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}
	
	
	
	

}
