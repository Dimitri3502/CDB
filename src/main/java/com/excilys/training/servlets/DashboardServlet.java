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
	private List<Integer> pageIds;
	private Long numberPerPage;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get data
		Long totalNumber = computerService.count();

		Pagination pagination = new Pagination();
		pagination.doPagination(request, totalNumber);

		List<ComputerDTO> computers = computerService.getAll(pagination.getNumberPerPage(), pagination.getOffset());

		// Add to request
		request.setAttribute("computers", computers);

		Utilities.forwardScreen(request, response, VUE);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	
	
}