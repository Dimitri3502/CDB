package com.excilys.training.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Pagination {
	private static final int MAX_PAGE = 10;
	private int numberPerPage;
	private int currentPageNumber;
	private int offset;
	private List<Integer> pageIds;
	private int nbPage;

	public void doPagination(HttpServletRequest request, long totalNumber) {
		// number of computers per page
		String pageId = request.getParameter("pageid");
		numberPerPage = 10;
		if (!(request.getParameter("numberPerPage") == null)) {
			numberPerPage = Integer.parseInt(request.getParameter("numberPerPage"));

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
		request.setAttribute("totalNumber", totalNumber);
		request.setAttribute("pageIds", pageIds);
		request.setAttribute("currentPageNumber", currentPageNumber);
		request.setAttribute("nbPage", nbPage);
		request.setAttribute("numberPerPage", numberPerPage);
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
}
