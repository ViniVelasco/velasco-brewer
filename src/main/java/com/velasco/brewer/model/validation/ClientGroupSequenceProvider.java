package com.velasco.brewer.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.velasco.brewer.model.Client;

public class ClientGroupSequenceProvider implements DefaultGroupSequenceProvider<Client> {

	@Override
	public List<Class<?>> getValidationGroups(Client client) {
		List<Class<?>> groups = new ArrayList<>();
		groups.add(Client.class);
		
		if(isPeopleSelected(client)) {
			groups.add(client.getPeopleType().getGroup());
		}
		
		return groups;
	}

	private boolean isPeopleSelected(Client client) {
		return client != null && client.getPeopleType() != null;
	}
}
