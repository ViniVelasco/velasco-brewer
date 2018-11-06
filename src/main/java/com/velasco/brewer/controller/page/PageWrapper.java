package com.velasco.brewer.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {
	
	private Page<T> page;
	private UriComponentsBuilder uriBuilder;

	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		String httpUrl = httpServletRequest.getRequestURL().append(
				httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "")
				.toString().replaceAll("\\+", "%20");
		this.uriBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);
	}
	
	public List<T> getContent() {
		return page.getContent();
	}
	
	public boolean isEmpty() {
		return page.getContent().isEmpty();
	}
	
	public int getActualPage() {
		return page.getNumber();
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public int getTotalPages() {
		return page.getTotalPages();
	}
	
	public String urlForPage(int page) {
		return uriBuilder.replaceQueryParam("page", page).build(true).encode().toUriString();
	}
	
	public String urlOrdered(String property) {
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder.fromUriString(uriBuilder.build(true).encode().toUriString());
		
		String sortValue = String.format("%s,%s", property, invertDirection(property));
		
		return uriBuilderOrder.replaceQueryParam("sort", sortValue).build(true).encode().toUriString();
	}
	
	public String invertDirection(String property) {
		String direction = "asc";
		
		Order order = page.getSort() != null ? page.getSort().getOrderFor(property) : null;
		if(order != null) {
			direction = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}
		
		return direction;
	}
	
	public boolean descendent(String property) {
		return invertDirection(property).equals("asc");
	}
	
	public boolean ordered(String property) {
		Order order = page.getSort() != null ? page.getSort().getOrderFor(property) : null;
		
		if(order == null) {
			return false;
		}
		
		return page.getSort().getOrderFor(property) != null ? true : false;
	}
}
