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
	private static int LIMIT_COMP_PAGE = 10;
	private static int currentPageNumber;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get data
		Long computersNumber = computerService.count();

		String pageId = request.getParameter("pageid");
		String pageFast = request.getParameter("pageFast");
		int nbPage = (int) Math.ceil(((double) computersNumber) / LIMIT_COMP_PAGE);

		int offset = 0;

		
		if (!(pageId == null)) {
			currentPageNumber = Integer.parseInt(pageId);
		}
		if (!(pageFast == null)) {

			switch (pageFast) {
			case "next":
				if (currentPageNumber + MAX_PAGE < nbPage)
					currentPageNumber += MAX_PAGE;
				else 
					currentPageNumber = nbPage;
				break;
			case "previous":
				if (currentPageNumber-MAX_PAGE<0)
					currentPageNumber = 0;
				else
					currentPageNumber -= MAX_PAGE;
				break;
			default:

			}
		}
		offset = currentPageNumber * LIMIT_COMP_PAGE;
		List<Integer> pageIds = new ArrayList<>();
		int len = 0;
		for (int number = Math.max(0, currentPageNumber - MAX_PAGE/2); (len<MAX_PAGE) && (number <= nbPage); ++number) {
			pageIds.add(number);
			len = pageIds.size();
		}

		List<ComputerDTO> computers = computerService.getAll(LIMIT_COMP_PAGE, offset);

		// Add to request
		request.setAttribute("computersNumber", computersNumber);
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
}