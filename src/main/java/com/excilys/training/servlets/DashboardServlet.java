package com.excilys.training.servlets;

import static com.excilys.training.servlets.CONSTANTES.ATT_COMPUTERS;
import static com.excilys.training.servlets.CONSTANTES.ATT_NUMBER_PER_PAGE;
import static com.excilys.training.servlets.CONSTANTES.ATT_ORDER_BY;
import static com.excilys.training.servlets.CONSTANTES.ATT_ORDER_DIRECTION;
import static com.excilys.training.servlets.CONSTANTES.ATT_PAGE_ID;
import static com.excilys.training.servlets.CONSTANTES.ATT_SEARCH;
import static com.excilys.training.servlets.CONSTANTES.SELECTION;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.mapper.ComputerMapper;
import com.excilys.training.service.ComputerService;

@Controller
@RequestMapping({"/dashboard", "/"})
public class DashboardServlet{

	public DashboardServlet(ComputerService computerService, ComputerMapper computerMapper, Pagination pagination) {
		super();
		this.computerService = computerService;
		this.computerMapper = computerMapper;
		this.pagination = pagination;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7519860715562557299L;

	private final Logger logger = LogManager.getLogger(getClass());

	private static final String VUE = "dashboard";

	private ComputerService computerService;

	private ComputerMapper computerMapper;

	private Pagination pagination;
	
	

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView processRequest(@RequestParam(required = false, name = ATT_SEARCH) String search,
			@RequestParam(required = false, name = ATT_ORDER_BY) String orderBy,
			@RequestParam(required = false, name = ATT_ORDER_DIRECTION) String orderDirection,
			@RequestParam(defaultValue = "10", name = ATT_NUMBER_PER_PAGE) Integer numberPerPage,
			@RequestParam(defaultValue = "0", name = ATT_PAGE_ID) Integer pageId)
			throws ServletException, IOException {
		
		ModelAndView mv = new ModelAndView(VUE);
		

		Long totalNumber = computerService.count(search);
		Page page = pagination.doPagination(search, orderBy, orderDirection, numberPerPage, pageId, totalNumber,mv);


		List<ComputerDTO> computers = computerMapper.allModelToDTO(computerService.getAll(page));
		// Add to request
		mv.addObject(ATT_COMPUTERS, computers);
		
		return mv;
	}


	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView handlePost(@RequestParam(required = false, name = ATT_SEARCH) String search,
			@RequestParam(required = false, name = ATT_SEARCH) String orderBy,
			@RequestParam(required = false, name = ATT_ORDER_DIRECTION) String orderDirection,
			@RequestParam(required = false, name = ATT_NUMBER_PER_PAGE) Integer numberPerPage,
			@RequestParam(required = false, name = ATT_PAGE_ID) Integer pageId,
    		@RequestParam(name = SELECTION) List<Long> removeComputersId) throws Exception {
		
		removeComputersId.stream().forEach(computerService::delete);
		return processRequest(search, orderBy, orderDirection, numberPerPage, pageId);

	}

}