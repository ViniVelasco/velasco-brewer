package com.velasco.brewer.model;

import com.velasco.brewer.model.validation.group.CpfGroup;
import com.velasco.brewer.model.validation.group.CnpjGroup;

public enum PeopleTyple {
	
	FISICA("Física", "CPF", "000.000.000-00", CpfGroup.class),
	JURIDICA("Jurídica", "CNPJ", "00.000.000/0000-00", CnpjGroup.class);
	
	private String description;
	private String document;
	private String mask;
	private Class<?> group;
	
	PeopleTyple(String description, String document, String mask, Class<?> group){
		this.description = description;
		this.document = document;
		this.mask = mask;
		this.group = group;
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

	public Class<?> getGroup() {
		return group;
	}
	
	public static String removeMask(String cpfCnpj) {
		return cpfCnpj.replaceAll("\\.|-|/", "");
	}

}
