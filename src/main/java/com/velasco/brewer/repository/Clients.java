package com.velasco.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.velasco.brewer.model.Client;

public interface Clients extends JpaRepository<Client, Long>{

	public Optional<Client> findByCpfCnpj(String cpfCnpj);

}
