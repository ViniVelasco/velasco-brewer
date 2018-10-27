package com.velasco.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.velasco.brewer.model.Beer;

@Repository
public interface Beers extends JpaRepository<Beer, Long> {
	
	public Optional<Beer> findBySkuIgnoreCase(String sku);

}