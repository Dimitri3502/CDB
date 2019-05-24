package com.excilys.training.controller.web;

import static com.excilys.training.controller.web.CONSTANTES.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.mapper.ComputerMapper;
import com.excilys.training.pagination.Page;
import com.excilys.training.pagination.Pagination;
import com.excilys.training.service.ComputerService;

@Controller
@RequestMapping({"/dashboard", "/"})
@SessionAttributes("page")
public class DashboardController{

	public DashboardController(ComputerService computerService, ComputerMapper computerMapper, Pagination pagination) {
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
	protected ModelAndView processRequest(@ModelAttribute("page") Page page)
			throws ServletException, IOException {
		
		ModelAndView mv = new ModelAndView(VUE);
		

		Long totalNumber = computerService.count(page.getSearch());
		pagination.doPagination(totalNumber, page);


		List<ComputerDTO> computers = computerMapper.allModelToDTO(computerService.getAll(page));
		// Add to request
		mv.addObject(ATT_TOTAL_NUMBER, totalNumber);
		mv.addObject(ATT_COMPUTERS, computers);
		mv.addObject("page", page);
		return mv;
	}


	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView handlePost(@ModelAttribute("page") Page page,
    		@RequestParam(name = SELECTION) List<Long> removeComputersId) throws Exception {
		
		removeComputersId.stream().forEach(computerService::delete);
		return processRequest(page);

	}
	
	@ModelAttribute("page")
	public Page getPage() {
		return new Page();
	}

}