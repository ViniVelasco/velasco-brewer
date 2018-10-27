package com.velasco.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.velasco.brewer.model.Style;

@Repository
public interface Styles extends JpaRepository<Style, Long> {

}