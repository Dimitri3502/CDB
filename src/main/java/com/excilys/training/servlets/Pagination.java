package com.excilys.training.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class Pagination {
	
	private static final String TOTAL_NUMBER = "totalNumber";
	private static final String PAGE_IDS = "pageIds";
	private static final String CURRENT_PAGE_NUMBER = "currentPageNumber";
	private static final String NB_PAGE = "nbPage";
	private static final String NUMBER_PER_PAGE = "numberPerPage";
	private static final int MAX_PAGE = 10; // size of page choice below the table
	private int numberPerPage = 10; // default value
	private int currentPageNumber;
	private int offset;
	private List<Integer> pageIds;
	private int nbPage;

	
	public void doPagination(HttpServletRequest request, long totalNumber) {
		
		String pageId = request.getParameter("pageid");
		
		if (!(request.getParameter(NUMBER_PER_PAGE) == null)) {
			numberPerPage = Integer.parseInt(request.getParameter(NUMBER_PER_PAGE));

		}
		nbPage = (int) Math.ceil(((double) totalNumber) / numberPerPage) - 1;

		if (!(pageId == null)) {
			currentPageNumber = Integer.parseInt(pageId);
		}
		// jump 10 pages
		if (!(pageId == null)) {
			int pageIdInt = Integer.parseInt(pageId);

			if (pageIdInt > nbPage) {
				currentPageNumber = nbPage;
			}

			else if (pageIdInt < 0) {
				currentPageNumber = 0;
			} else {
				currentPageNumber = pageIdInt;

			}
		}

		setOffset(currentPageNumber * numberPerPage);
		pageIds = new ArrayList<>();
		int len = 0;
		for (int number = Math.max(0, currentPageNumber - MAX_PAGE / 2); (len < MAX_PAGE)
				&& (number <= nbPage); ++number) {
			pageIds.add(number);
			len = pageIds.size();
		}
		request.setAttribute(TOTAL_NUMBER, totalNumber);
		request.setAttribute(PAGE_IDS, pageIds);
		request.setAttribute(CURRENT_PAGE_NUMBER, currentPageNumber);
		request.setAttribute(NB_PAGE, nbPage);
		request.setAttribute(NUMBER_PER_PAGE, numberPerPage);
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getNumberPerPage() {
		return numberPerPage;
	}

	public void setNumberPerPage(int numberPerPage) {
		this.numberPerPage = numberPerPage;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
}
