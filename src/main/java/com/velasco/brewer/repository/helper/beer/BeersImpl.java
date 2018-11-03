package com.velasco.brewer.repository.helper.beer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import com.velasco.brewer.model.Beer;
import com.velasco.brewer.repository.filter.BeerFilter;

public class BeersImpl implements BeersQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Beer> filter(BeerFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Beer.class);
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
		
		return criteria.list();
	}

	private boolean isStylePresent(BeerFilter filter) {
		return filter.getStyle() != null && filter.getStyle().getId() != null;
	}

}
