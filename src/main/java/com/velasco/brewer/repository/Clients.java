package com.velasco.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.velasco.brewer.model.Client;

public interface Clients extends JpaRepository<Client, Long>{

}
