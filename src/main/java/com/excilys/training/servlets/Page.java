package com.excilys.training.servlets;

import com.excilys.training.persistance.OrderByChamp;
import com.excilys.training.persistance.OrderByDirection;

public class Page {
	private int limit; 
	private int offset; 
	private String search; 
	private OrderByChamp orderBy; 
	private OrderByDirection orderDirection;
	
	
	
	public Page(int limit, int offset, String search, OrderByChamp orderBy, OrderByDirection orderDirection) {
		super();
		this.limit = limit;
		this.offset = offset;
		this.search = search != null ? "%" + search + "%" : "%%";;
		this.orderBy = orderBy;
		this.orderDirection = orderDirection;
	}
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public OrderByChamp getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(OrderByChamp orderBy) {
		this.orderBy = orderBy;
	}
	public OrderByDirection getOrderDirection() {
		return orderDirection;
	}
	public void setOrderDirection(OrderByDirection orderDirection) {
		this.orderDirection = orderDirection;
	}
	
	
	
}
