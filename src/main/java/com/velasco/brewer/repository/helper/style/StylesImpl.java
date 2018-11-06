package com.velasco.brewer.repository.helper.style;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.velasco.brewer.model.Style;
import com.velasco.brewer.repository.filter.StyleFilter;

public class StylesImpl implements StylesQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Style> filter(StyleFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Style.class);
		
		int actualPage = pageable.getPageNumber();
		int totalRegistersPerPage = pageable.getPageSize();
		int firstRegister = actualPage * totalRegistersPerPage;
		
		criteria.setFirstResult(firstRegister);
		criteria.setMaxResults(totalRegistersPerPage);
		
		Sort sort = pageable.getSort();
		
		if (sort != null) {
			Sort.Order order = sort.iterator().next();
			String field = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(field) : Order.desc(field));
		}
		
		addFilter(filter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}

	private void addFilter(StyleFilter filter, Criteria criteria) {
		if(filter != null) {
			if(!StringUtils.isEmpty(filter.getName())) {
				criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
			}
		}
	}
	private Long total(StyleFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Style.class);
		addFilter(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

}
