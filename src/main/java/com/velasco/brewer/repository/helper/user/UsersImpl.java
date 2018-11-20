package com.velasco.brewer.repository.helper.user;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.velasco.brewer.model.User;

public class UsersImpl implements UsersQueries {

	@Autowired
	private EntityManager manager;
	
	@Override
	public Optional<User> byEmailAndActive(String email) {
		return manager
				.createQuery("from User where lower(email) = lower(:email) and active = true", User.class)
				.setParameter("email", email).getResultList().stream().findFirst();
	}

}
