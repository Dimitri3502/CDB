package com.excilys.training.servlets;

import static com.excilys.training.servlets.CONSTANTES.ATT_COMPUTERS;
import static com.excilys.training.servlets.CONSTANTES.ATT_ORDER_BY;
import static com.excilys.training.servlets.CONSTANTES.ATT_ORDER_DIRECTION;
import static com.excilys.training.servlets.CONSTANTES.ATT_SEARCH;
import static com.excilys.training.servlets.CONSTANTES.CHAMP_COMPANY_NAME;
import static com.excilys.training.servlets.CONSTANTES.CHAMP_COMPUTERNAME;
import static com.excilys.training.servlets.CONSTANTES.CHAMP_DISCONTINUED;
import static com.excilys.training.servlets.CONSTANTES.CHAMP_ID;
import static com.excilys.training.servlets.CONSTANTES.CHAMP_INTRODUCED;
import static com.excilys.training.servlets.CONSTANTES.SELECTION;
import static com.excilys.training.validator.ValidatorUtils.isBlank;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.mapper.ComputerMapper;
import com.excilys.training.persistance.ENUMS.OrderByChamp;
import com.excilys.training.persistance.ENUMS.OrderByDirection;
import com.excilys.training.service.ComputerService;

@Controller
@RequestMapping({"/dashboard", "/"})
public class DashboardServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7519860715562557299L;

	private final Logger logger = LogManager.getLogger(getClass());

	private static final String VUE = "dashboard";

	@Autowired
	private ComputerService computerService;

	@Autowired
	private ComputerMapper computerMapper;

	@Autowired
	private Pagination pagination;

	private String search;
	private String orderBy;
	private String orderDirection;


	protected ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ModelAndView mv = new ModelAndView(VUE);
		
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
		mv.addObject(ATT_COMPUTERS, computers);
		mv.addObject(ATT_ORDER_BY, orderBy);
		mv.addObject(ATT_SEARCH, search);
		mv.addObject(ATT_ORDER_DIRECTION, orderDirection);
		
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return processRequest(request, response);
	}


	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView handlePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Long> removeComputersId = getRemoveComputersId(request);
		removeComputersId.stream().forEach(computerService::delete);

		return processRequest(request, response);

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