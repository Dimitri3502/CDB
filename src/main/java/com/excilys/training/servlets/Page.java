package com.excilys.training.servlets;

import com.excilys.training.persistance.ENUMS.OrderByChamp;
import com.excilys.training.persistance.ENUMS.OrderByDirection;

public class Page {
	private int limit; 
	private int offset; 
	private String search; 
	private OrderByChamp orderBy; 
	private OrderByDirection orderDirection;
	
	
	
	public Page(int limit, int offset, String search, OrderByChamp orderBy, OrderByDirection orderDirection) {
		super();
		this.setLimit(limit);
		this.setOffset(offset);
		this.search = search != null ? "%" + search + "%" : "%%";;
		this.orderBy = orderBy;
		this.orderDirection = orderDirection;
	}
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit>0 ? limit : 0;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset>0 ? offset : 0;
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
