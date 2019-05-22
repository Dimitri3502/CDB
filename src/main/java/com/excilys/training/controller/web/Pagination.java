package com.excilys.training.controller.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Pagination {

	private static final int MAX_PAGE = 10; // size of page choice below the table
	private int currentPageNumber;
	private List<Integer> pageIds;
	private int nbPage;

	public void doPagination(Long totalNumber, Page page) {
		int limit = page.getLimit();
		Integer pageNumberRequest = page.getPageNumberRequest();

		nbPage = (int) Math.ceil(((double) totalNumber) / limit);

		if (!(pageNumberRequest == null)) {

			if (pageNumberRequest > nbPage) {
				currentPageNumber = nbPage;
			}

			else if (pageNumberRequest < 0) {
				currentPageNumber = 0;
			} else {
				currentPageNumber = pageNumberRequest;

			}
		}

		pageIds = new ArrayList<>();
		int len = 0;
		for (int number = Math.max(0, currentPageNumber - MAX_PAGE / 2); (len < MAX_PAGE)
				&& (number <= nbPage); ++number) {
			pageIds.add(number);
			len = pageIds.size();
		}
		page.setNbPage(nbPage);
		page.setCurrentPageNumber(currentPageNumber);
		page.setOffset(currentPageNumber * limit);
		page.setPageIds(pageIds);
	}



}
