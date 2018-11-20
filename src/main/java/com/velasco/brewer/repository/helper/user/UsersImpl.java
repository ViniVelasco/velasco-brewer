package com.velasco.brewer.repository.helper.user;

import java.util.List;
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

	@Override
	public List<String> permissions(User user) {
		return manager.createQuery(
				"select distinct p.name from User u inner join u.grupos g inner join g.permissions p where u = :user", String.class)
				.setParameter("user", user)
				.getResultList();
	}

}
