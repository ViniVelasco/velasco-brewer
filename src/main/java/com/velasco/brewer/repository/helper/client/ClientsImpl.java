package com.velasco.brewer.repository.helper.client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.velasco.brewer.model.Client;
import com.velasco.brewer.repository.filter.ClientFilter;
import com.velasco.brewer.repository.pagination.PaginationUtil;

public class ClientsImpl implements ClientsQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginationUtil paginationUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Client> filter(ClientFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Client.class);
		
		paginationUtil.prepare(criteria, pageable);
		
		addFilter(filter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}
	
	private void addFilter(ClientFilter filter, Criteria criteria) {
		if(filter != null) {
			if(!StringUtils.isEmpty(filter.getCpfCnpj())) {
				criteria.add(Restrictions.eq("cpfCnpj", filter.getCpfCnpj()));
			}
			
			if(!StringUtils.isEmpty(filter.getName())) {
				criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
			}
		}
	}

	private Long total(ClientFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Client.class);
		addFilter(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

}
