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
		String pageFast = request.getParameter("pageFast");
		String nbComp = request.getParameter("nbComp");

		if (!(nbComp == null)) {

			switch (nbComp) {
			case "10":
				numberPerPage = 10;
				break;
			case "50":
				numberPerPage = 50;
				break;
			case "100":
				numberPerPage = 100;
				break;
			default:
				numberPerPage = 10;

			}

		} else {
			numberPerPage = 10;
		}
		nbPage = (int) Math.ceil(((double) totalNumber) / numberPerPage) - 1;

		if (!(pageId == null)) {
			currentPageNumber = Integer.parseInt(pageId);
		}
		// jump 10 pages
		if (!(pageFast == null)) {

			switch (pageFast) {
			case "next":
				if (currentPageNumber + MAX_PAGE < nbPage)
					currentPageNumber += MAX_PAGE;
				else
					currentPageNumber = nbPage;
				break;
			case "previous":
				if (currentPageNumber - MAX_PAGE < 0)
					currentPageNumber = 0;
				else
					currentPageNumber -= MAX_PAGE;
				break;
			default:

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
