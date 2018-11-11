package com.velasco.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.velasco.brewer.model.Client;
import com.velasco.brewer.repository.Clients;



@Service
public class ClientRegisterService {
	
	@Autowired
	private Clients clients;
	
	@Transactional
	public void save(Client client) {
		clients.save(client);
	}

}
