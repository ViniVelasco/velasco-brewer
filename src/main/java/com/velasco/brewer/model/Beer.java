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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.velasco.brewer.validation.SKU;

@Entity
@Table(name = "beer")
public class Beer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@SKU
	@NotBlank(message = "SKU é obrigatório")
	private String sku;
	
	@NotBlank(message = "Nome é obrigatório")
	private String name;
	
	@DecimalMin(value = "0.50", message = "O valor mínimo deve ser R$0.50")
	@DecimalMax(value = "9999999.99", message = "O valor da cerveja deve ser menor que R$9.999.999,99")
	@NotNull(message = "Valor é obrigatório")
	private BigDecimal value;
	
	@Column(name = "alcohol_content")
	@DecimalMax(value = "100.0", message="O teor alcoólico deve ser menor que 100%")
	@NotNull(message="O teor alcoólico é obrigatório")
	private BigDecimal alcoholContent;
	
	@DecimalMax(value = "100", message="A comissão deve ser igual ou menor que 100")
	@NotNull(message = "A comissão é obrigatória")
	private BigDecimal commission;
	
	@Max(value = 9999, message = "A quantidade em estoque deve ser menor que 9999")
	@Column(name = "stock")
	@NotNull(message = "A quantidade em estoque é obrigatória")
	private BigDecimal stock;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="A origem é obrigatória")
	private Origin origin;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O sabor é obrigatório")
	private Flavor flavor;
	
	@ManyToOne
	@JoinColumn(name = "id_style")
	@NotNull(message = "O estilo é obrigatório")
	private Style style;
	
	@Size(min = 1, max = 250, message = "O tamanho da descrição deve estar entre 1 e 250")
	@NotBlank(message = "A descrição é obrigatória")
	private String description;
	
	private String photo;
	
	@Column(name = "content_type")
	private String contentType;
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		sku = sku.toUpperCase();
	}
	
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
	
	public BigDecimal getStock() {
		return stock;
	}
	
	public void setStock(BigDecimal stock) {
		this.stock = stock;
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
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
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
