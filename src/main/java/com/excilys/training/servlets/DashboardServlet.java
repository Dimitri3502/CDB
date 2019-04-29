package com.excilys.training.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.service.CompanyService;
import com.excilys.training.service.ComputerService;

//@WebServlet(name = "Dashboard", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet {
//	private final CompanyService companyService = CompanyService.getInstance();
	private final ComputerService computerService = ComputerService.getInstance();

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
		List<ComputerDTO> computers = computerService.getAll(10, 0);
//		List<CompanyDTO> companies = companyService.getAll(100, 0);
		// Add to request
		request.setAttribute("computers", computers);
//		request.setAttribute("companies", companies);

		Utilities.forwardScreen(request, response, "dashboard");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}