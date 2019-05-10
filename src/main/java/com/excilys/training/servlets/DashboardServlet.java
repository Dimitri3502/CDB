package com.excilys.training.servlets;

import static com.excilys.training.validator.ValidatorUtils.isBlank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.persistance.OrderByChamp;
import com.excilys.training.persistance.OrderByDirection;
import com.excilys.training.service.ComputerService;

@WebServlet(name = "Dashboard", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet {
	public static final String VUE = "/WEB-INF/views/dashboard.jsp";

	private final ComputerService computerService = ComputerService.getInstance();
	private final Pagination pagination = Pagination.getInstance();

	private String search;
	private String orderBy;
	private String orderDirection;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get data
		search = request.getParameter("search");
		orderBy = request.getParameter("order_by");
		orderDirection = request.getParameter("order_direction");

		Long totalNumber = computerService.count(search);
		pagination.doPagination(request, totalNumber);
		List<ComputerDTO> computers = computerService.getAll(pagination.getNumberPerPage(), pagination.getOffset(),
				search, mapOrderByChamp(orderBy), mapOrderByDirection(orderDirection));

		// Add to request
		request.setAttribute("computers", computers);
		request.setAttribute("orderBy", orderBy );
		request.setAttribute("search", search );
		request.setAttribute("orderDirection", orderDirection);
		Utilities.forwardScreen(request, response, VUE);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idsStr = request.getParameter("selection");
		if (!isBlank(idsStr)) {
			String str[] = idsStr.split(",");
			List<String> idsArray = new ArrayList<String>();
			idsArray = Arrays.asList(str);
			for(String idStr: idsArray){
				Long value = Long.parseLong(idStr);
		        Optional<ComputerDTO> computerDTO = computerService.findById(value);
		        computerService.delete(computerDTO.get());
			}
		}
		processRequest(request, response);
        

        
	}

	private OrderByChamp mapOrderByChamp(String s) {
		if (s == null) {
			orderBy = "id";
			return OrderByChamp.ID;
		} else {
			switch (s) {
			default:
			case "id":
				orderBy = "id";
				return OrderByChamp.ID;
			case "name":
				orderBy = "name";
				return OrderByChamp.NAME;
			case "introduced":
				orderBy = "introduced";
				return OrderByChamp.INTRODUCED;
			case "discontinued":
				orderBy = "discontinued";
				return OrderByChamp.DISCONTINUED;
			case "company_name":
				orderBy = "company_name";
				return OrderByChamp.COMPANY;
			}
		}
	}

	private OrderByDirection mapOrderByDirection(String s) {
		if (s == null) {
			return OrderByDirection.ASC;
		} else {
			switch (s) {
			default:
			case "asc":
				return OrderByDirection.ASC;
			case "desc":
				return OrderByDirection.DESC;
			}
		}
	}

}