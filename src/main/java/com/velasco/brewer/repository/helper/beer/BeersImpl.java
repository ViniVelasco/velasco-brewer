package com.velasco.brewer.repository.helper.beer;

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


import com.velasco.brewer.model.Beer;
import com.velasco.brewer.repository.filter.BeerFilter;
import com.velasco.brewer.repository.pagination.PaginationUtil;

public class BeersImpl implements BeersQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginationUtil paginationUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Beer> filter(BeerFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Beer.class);
		
		paginationUtil.prepare(criteria, pageable);
		
		addFilter(filter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}

	private void addFilter(BeerFilter filter, Criteria criteria) {
		if(filter != null) {
			if(!StringUtils.isEmpty(filter.getSku())) {
				criteria.add(Restrictions.eq("sku", filter.getSku()));
			}
			
			if(!StringUtils.isEmpty(filter.getName())) {
				criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
			}
			
			if(isStylePresent(filter)) {
				criteria.add(Restrictions.eq("style", filter.getStyle()));
			}
			
			if(filter.getFlavor() != null) {
				criteria.add(Restrictions.eq("flavor", filter.getFlavor()));
			}
			
			if(filter.getOrigin() != null) {
				criteria.add(Restrictions.eq("origin", filter.getOrigin()));
			}
			
			if(filter.getValueIn() != null) {
				criteria.add(Restrictions.ge("value", filter.getValueIn())); //greater and equal
			}
			
			if(filter.getValueTo() != null) {
				criteria.add(Restrictions.le("value", filter.getValueTo())); //less and equal
			}
		}
	}

	private Long total(BeerFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Beer.class);
		addFilter(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private boolean isStylePresent(BeerFilter filter) {
		return filter.getStyle() != null && filter.getStyle().getId() != null;
	}

}
