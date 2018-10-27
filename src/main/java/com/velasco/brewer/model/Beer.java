package com.velasco.brewer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "beer")
public class Beer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "SKU é obrigatório")
	private String sku;
	
	@NotBlank(message = "Nome é obrigatório")
	private String name;
	
	private BigDecimal value;
	
	@Column(name = "alcohol_content")
	private BigDecimal alcoholContent;
	
	private BigDecimal commission;
	
	@Column(name = "stockQuantity")
	private BigDecimal stockQuantity;
	
	@Enumerated(EnumType.STRING)
	private Origin origin;
	
	@Enumerated(EnumType.STRING)
	private Flavor flavor;
	
	@ManyToOne
	@JoinColumn(name = "id_style")
	private Style style;
	
	@Size(min = 1, max = 50, message = "O tamanho da descrição deve estar entre 1 e 50")
	private String description;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public BigDecimal getAlcoholContent() {
		return alcoholContent;
	}
	public void setAlcoholContent(BigDecimal alcoholContent) {
		this.alcoholContent = alcoholContent;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public BigDecimal getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(BigDecimal stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	public Flavor getFlavor() {
		return flavor;
	}
	public void setFlavor(Flavor flavor) {
		this.flavor = flavor;
	}
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Beer other = (Beer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
