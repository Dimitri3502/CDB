package com.excilys.training.servlets;

import static com.excilys.training.servlets.CONSTANTES.*;
import static com.excilys.training.validator.ValidatorUtils.isBlank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.mapper.ComputerMapper;
import com.excilys.training.model.Computer;
import com.excilys.training.persistance.ENUMS.OrderByChamp;
import com.excilys.training.persistance.ENUMS.OrderByDirection;
import com.excilys.training.service.ComputerService;

@WebServlet(name = "Dashboard", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet {

	private final Logger logger = LogManager.getLogger(getClass());
	
	private static final String VUE = "/WEB-INF/views/dashboard.jsp";

	private ComputerService computerService;
	private ComputerMapper computerMapper;
	private Pagination pagination;

	private String search;
	private String orderBy;
	private String orderDirection;

	@Override
	public void init() throws ServletException {
		super.init();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		this.computerService = wac.getBean(ComputerService.class);
		this.pagination = wac.getBean(Pagination.class);
		this.computerMapper = wac.getBean(ComputerMapper.class);

	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get data
		search = request.getParameter(ATT_SEARCH);
		orderBy = request.getParameter(ATT_ORDER_BY);
		orderDirection = request.getParameter(ATT_ORDER_DIRECTION);

		Long totalNumber = computerService.count(search);
		pagination.doPagination(request, totalNumber);

		Page page = new Page(pagination.getNumberPerPage(), pagination.getOffset(), search, mapOrderByChamp(orderBy),
				mapOrderByDirection(orderDirection));

		List<ComputerDTO> computers = computerMapper.allModelToDTO(computerService.getAll(page));
		// Add to request
		request.setAttribute(ATT_COMPUTERS, computers);
		request.setAttribute(ATT_ORDER_BY, orderBy);
		request.setAttribute(ATT_SEARCH, search);
		request.setAttribute(ATT_ORDER_DIRECTION, orderDirection);
		Utilities.forwardScreen(request, response, VUE);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Long> removeComputersId = getRemoveComputersId(request);
		removeComputersId.stream().forEach(computerService::delete);

		processRequest(request, response);

	}

	private List<Long> getRemoveComputersId(HttpServletRequest request) {
		try {
			final String idsStr = request.getParameter(SELECTION);
			if (!isBlank(idsStr)) {
				String[] selection = idsStr.split(",");
				return Arrays.stream(selection).map(Long::valueOf).collect(Collectors.toList());
			}
		} catch (NumberFormatException e) {
			logger.warn("Les identifiants des ordinateurs à supprimer sont incorects", e);
		}
		return Collections.emptyList();
	}

	private OrderByChamp mapOrderByChamp(String s) {
		if (s == null) {
			orderBy = CHAMP_ID;
			return OrderByChamp.ID;
		} else {
			switch (s) {
			default:
			case CHAMP_ID:
				orderBy = CHAMP_ID;
				return OrderByChamp.ID;
			case CHAMP_COMPUTERNAME:
				orderBy = CHAMP_COMPUTERNAME;
				return OrderByChamp.NAME;
			case CHAMP_INTRODUCED:
				orderBy = CHAMP_INTRODUCED;
				return OrderByChamp.INTRODUCED;
			case CHAMP_DISCONTINUED:
				orderBy = CHAMP_DISCONTINUED;
				return OrderByChamp.DISCONTINUED;
			case CHAMP_COMPANY_NAME:
				orderBy = CHAMP_COMPANY_NAME;
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