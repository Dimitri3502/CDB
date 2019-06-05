package com.excilys.training.webapp.controller;
import static com.excilys.training.webapp.controller.CONSTANTES.ATT_ERRORS_MSG;
import static com.excilys.training.webapp.controller.CONSTANTES.ATT_MESSAGE;
import static com.excilys.training.webapp.controller.CONSTANTES.ATT_RESULTAT;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.training.binding.dto.CompanyDTO;
import com.excilys.training.binding.mapper.CompanyMapper;
import com.excilys.training.service.ICompanyService;
import com.excilys.training.service.IComputerService;
import com.excilys.training.webapp.dto.ComputerDTOForm;
import com.excilys.training.webapp.mapper.ComputerFormMapper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


@RestController
@RequestMapping("/addComputer")
public class AddComputerController{
    public AddComputerController(IComputerService computerService, ICompanyService companyService,
			CompanyMapper companyMapper, ComputerFormMapper computerFormMapper) {
		super();
		this.computerService = computerService;
		this.companyService = companyService;
		this.companyMapper = companyMapper;
		this.computerFormMapper = computerFormMapper;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -828447276545120635L;
	public static final String VUE = "addComputer";
	
	private IComputerService computerService;

	private ICompanyService companyService;
	
	private CompanyMapper companyMapper;
	
	private ComputerFormMapper computerFormMapper;
	
	@ModelAttribute("companies")
	public List<CompanyDTO>  getCompanies() {
		return companyMapper.allModelToDTO(companyService.getAll());
	}

	@ModelAttribute("computerDTOForm")
	public ComputerDTOForm  getComputerDTOForm() {
		return new ComputerDTOForm();
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleGet() throws Exception {
		return new ModelAndView(VUE);
    }


	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView handlePost(@Valid @ModelAttribute("computerDTOForm") ComputerDTOForm computerDTOForm,
    	      BindingResult result) throws Exception {
		
		ModelAndView mv = new ModelAndView(VUE);
		
		String message;
		Boolean resultat;
		List<String> errors = new ArrayList<>();
		if (result.hasErrors()) {
			message = "string.fail";
			resultat = false;
			for (ObjectError error : result.getGlobalErrors()) {
				errors.add(error.getDefaultMessage());
			}
            
        } else {
        	long id = computerService.create(computerFormMapper.dtoFormToModel(computerDTOForm));
        	message = "string.success";
        	resultat = true;
        	mv.addObject("computerDTOForm", new ComputerDTOForm());
        }
        

        /* Stockage du résultat et des messages d'erreur dans l'objet request */
		mv.addObject( ATT_ERRORS_MSG, errors );
        mv.addObject( ATT_RESULTAT, resultat );
        mv.addObject( ATT_MESSAGE, message );
       

		return mv;
        
        
    }
}
