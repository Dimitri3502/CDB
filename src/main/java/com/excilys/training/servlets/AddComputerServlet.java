package com.excilys.training.servlets;
import static com.excilys.training.servlets.CONSTANTES.ATT_MESSAGE;
import static com.excilys.training.servlets.CONSTANTES.ATT_RESULTAT;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTOForm;
import com.excilys.training.mapper.CompanyMapper;
import com.excilys.training.mapper.ComputerMapper;
import com.excilys.training.service.CompanyService;
import com.excilys.training.service.ComputerService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


@Controller
@RequestMapping("/addComputer")
public class AddComputerServlet{
    public AddComputerServlet(ComputerService computerService, CompanyService companyService,
			CompanyMapper companyMapper, ComputerMapper computerMapper) {
		super();
		this.computerService = computerService;
		this.companyService = companyService;
		this.companyMapper = companyMapper;
		this.computerMapper = computerMapper;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -828447276545120635L;
	public static final String VUE = "addComputer";
	
	private ComputerService computerService;

	private CompanyService companyService;
	
	private CompanyMapper companyMapper;
	
	private ComputerMapper computerMapper;
	

	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mv = new ModelAndView(VUE);
		
		List<CompanyDTO> companies = companyMapper.allModelToDTO(companyService.getAll());

		// Add to request
		mv.addObject("computerDTOForm", new ComputerDTOForm());
		mv.addObject("companies", companies);
		return mv;
    }


	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView handlePost(@Valid @ModelAttribute("computerDTOForm") ComputerDTOForm computerDTOForm,
    	      BindingResult result) throws Exception {
		
		ModelAndView mv = new ModelAndView(VUE);
		
		String message;
		Boolean resultat;
		if (result.hasErrors()) {
			message = "Échec de l'inscription.";
			resultat = false;
            
        } else {
        	long id = computerService.create(computerMapper.dtoFormToModel(computerDTOForm));
        	message = "Succès de l'inscription.";
        	resultat = true;
        }
        

        /* Stockage du résultat et des messages d'erreur dans l'objet request */
        mv.addObject( ATT_RESULTAT, resultat );
        mv.addObject( ATT_MESSAGE, message );
        mv.addObject("computerDTOForm", computerDTOForm);
		List<CompanyDTO> companies = companyMapper.allModelToDTO(companyService.getAll());
		mv.addObject("companies", companies);

		return mv;
        
        
    }
}
