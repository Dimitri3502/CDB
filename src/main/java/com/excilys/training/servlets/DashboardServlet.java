package com.excilys.training.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.service.ComputerService;

//@WebServlet(name = "Dashboard", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet {
	public static final String VUE = "/WEB-INF/views/dashboard.jsp";

	private final ComputerService computerService = ComputerService.getInstance();
	private static final int MAX_PAGE = 10;
	private int LIMIT_COMP_PAGE = 10;
	private static int currentPageNumber;
	private int offset;
	private List<Integer> pageIds;
	private Long numberPerPage;
	private int nbPage;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get data
		numberPerPage = computerService.count();

		String pageId = request.getParameter("pageid");
		String pageFast = request.getParameter("pageFast");
		String nbComp = request.getParameter("nbComp");
		
		pagination(pageId, pageFast, nbComp);

		List<ComputerDTO> computers = computerService.getAll(LIMIT_COMP_PAGE, offset);

		// Add to request
		request.setAttribute("computersNumber", numberPerPage);
		request.setAttribute("computers", computers);
		request.setAttribute("pageIds", pageIds);
		request.setAttribute("currentPageNumber", currentPageNumber);
		request.setAttribute("nbPage", nbPage);

		Utilities.forwardScreen(request, response, VUE);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void pagination(String pageId, String pageFast, String nbComp) {
		// number of computers per page
		if (!(nbComp == null)) {

			switch (nbComp) {
			case "10":
				LIMIT_COMP_PAGE = 10;
				break;
			case "50":
				LIMIT_COMP_PAGE = 50;
				break;
			case "100":
				LIMIT_COMP_PAGE = 100;
				break;
			default:

			}
		}
		nbPage = (int) Math.ceil(((double) numberPerPage) / LIMIT_COMP_PAGE) - 1;

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

		offset = currentPageNumber * LIMIT_COMP_PAGE;
		pageIds = new ArrayList<>();
		int len = 0;
		for (int number = Math.max(0, currentPageNumber - MAX_PAGE / 2); (len < MAX_PAGE)
				&& (number <= nbPage); ++number) {
			pageIds.add(number);
			len = pageIds.size();
		}
	}
}