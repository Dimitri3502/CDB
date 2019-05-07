package com.excilys.training.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.persistance.OrderByChamp;
import com.excilys.training.persistance.OrderByEnum;
import com.excilys.training.service.ComputerService;

@WebServlet(name = "Dashboard", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet {
	public static final String VUE = "/WEB-INF/views/dashboard.jsp";

	private final ComputerService computerService = ComputerService.getInstance();
	private final Pagination pagination = Pagination.getInstance();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get data
		String name = request.getParameter("search");
		String orderBy = request.getParameter("order_by");
		String orderDirection = request.getParameter("order_direction");

		Long totalNumber = computerService.count();
		pagination.doPagination(request, totalNumber);
		List<ComputerDTO> computers = computerService.getAll(pagination.getNumberPerPage(), pagination.getOffset(),
				name, mapOrderByChamp(orderBy), mapOrderByEnum(orderDirection));

		// Add to request
		request.setAttribute("computers", computers);
		request.setAttribute("orderBy", orderBy );
		request.setAttribute("orderDirection", orderDirection);
		Utilities.forwardScreen(request, response, VUE);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private OrderByChamp mapOrderByChamp(String s) {
		if (s == null) {
			return OrderByChamp.ID;
		} else {
			switch (s) {
			default:
			case "id":
				return OrderByChamp.ID;
			case "name":
				return OrderByChamp.NAME;
			case "introduced":
				return OrderByChamp.INTRODUCED;
			case "discontinued":
				return OrderByChamp.DISCONTINUED;
			case "company":
				return OrderByChamp.COMPANY;
			}
		}
	}

	private OrderByEnum mapOrderByEnum(String s) {
		if (s == null) {
			return OrderByEnum.ASC;
		} else {
			switch (s) {
			default:
			case "asc":
				return OrderByEnum.ASC;
			case "desc":
				return OrderByEnum.DESC;
			}
		}
	}

}