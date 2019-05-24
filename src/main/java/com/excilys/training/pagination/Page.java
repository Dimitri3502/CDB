package com.excilys.training.pagination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.excilys.training.pagination.ENUMS.OrderByChamp;
import com.excilys.training.pagination.ENUMS.OrderByDirection;

public class Page {
	private int currentPageNumber;
	private int pageNumberRequest;
	private List<Integer> pageIds; 
	private int limit; 
	private int offset; 
	private String search; 
	private OrderByChamp orderBy; 
	private OrderByDirection orderDirection;
	private int nbPage;
	public static final int MAX_PAGE_CHOICE = 10; // size of page choice below the table
	
	
	public Page() {
		pageIds = Arrays.asList(0, 1, 2,3,4,4,5,6,7,8,9);
		orderBy = OrderByChamp.ID;
		orderDirection = OrderByDirection.ASC;
		limit = 10;
		offset = 0;
		search = "";
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
		setPageNumberRequest(0);
		this.search = Objects.toString(search,"");
	}
	public OrderByChamp getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(OrderByChamp orderBy) {
		setPageNumberRequest(0);
		this.orderBy = orderBy;
	}
	public OrderByDirection getOrderDirection() {
		return orderDirection;
	}
	public void setOrderDirection(OrderByDirection orderDirection) {
		setPageNumberRequest(0);
		this.orderDirection = orderDirection;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public int getPageNumberRequest() {
		return pageNumberRequest;
	}

	public void setPageNumberRequest(int pageNumberRequest) {
		this.pageNumberRequest = pageNumberRequest;
	}

	public List<Integer> getPageIds() {
		return pageIds;
	}

	public void setPageIds(List<Integer> pageIds) {
		this.pageIds = pageIds;
	}

	public int getNbPage() {
		return nbPage;
	}

	public void setNbPage(int nbPage) {
		this.nbPage = nbPage;
	}
	
	
	
}
