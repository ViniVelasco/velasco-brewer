package com.velasco.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.velasco.brewer.model.Client;
import com.velasco.brewer.repository.helper.client.ClientsQueries;

@Repository
public interface Clients extends JpaRepository<Client, Long>, ClientsQueries{

	public Optional<Client> findByCpfCnpj(String cpfCnpj);

}
