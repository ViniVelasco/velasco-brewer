package com.velasco.brewer.repository.helper.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.velasco.brewer.model.Client;
import com.velasco.brewer.repository.filter.ClientFilter;

public interface ClientsQueries {

	public Page<Client> filter(ClientFilter filter, Pageable pageable);
}
