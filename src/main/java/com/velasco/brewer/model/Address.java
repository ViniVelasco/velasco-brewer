package com.velasco.brewer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Embeddable
public class Address implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "public_place")
	private String publicPlace;
	
	private String number;
	
	private String complement;
	
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "id_city")
	private City city;
	
	@Transient
	private State state;
	
	public String getPublicPlace() {
		return publicPlace;
	}
	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	public String getNameCityInitialsState() {
		if(this.city != null) {
			return this.city.getName() + "/" + this.city.getState().getInitials();
		}
		
		return null;
	}

}
