package com.velasco.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.velasco.brewer.model.Client;
import com.velasco.brewer.repository.Clients;
import com.velasco.brewer.service.exception.CpfCnpjAlreadyRegisteredException;

@Service
public class ClientRegisterService {
	
	@Autowired
	private Clients clients;
	
	@Transactional
	public void save(Client client) {
		Optional<Client> existentClient = clients.findByCpfCnpj(client.getCpfCnpjWithotFormat());
		if(existentClient.isPresent()) {
			throw new CpfCnpjAlreadyRegisteredException("CPF/CNPJ já cadastrado");
		}
		
		clients.save(client);
	}

}
