package com.velasco.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.velasco.brewer.model.Style;
import com.velasco.brewer.repository.helper.style.StylesQueries;

@Repository
public interface Styles extends JpaRepository<Style, Long>, StylesQueries {

	public Optional<Style> findByNameIgnoreCase(String nome);
}
