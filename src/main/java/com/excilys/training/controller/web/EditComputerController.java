package com.excilys.training.controller.web;
import static com.excilys.training.controller.web.CONSTANTES.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.dto.ComputerDTOForm;
import com.excilys.training.mapper.CompanyMapper;
import com.excilys.training.mapper.ComputerMapper;
import com.excilys.training.service.CompanyService;
import com.excilys.training.service.ComputerService;
import com.excilys.training.validator.Validator;
import com.excilys.training.validator.WebValidator;
 

@Controller
@RequestMapping("/editComputer")
public class EditComputerController{
	
	
    public EditComputerController(ComputerService computerService, CompanyService companyService,
			CompanyMapper companyMapper, ComputerMapper computerMapper) {
		super();
		this.computerService = computerService;
		this.companyService = companyService;
		this.companyMapper = companyMapper;
		this.computerMapper = computerMapper;
	}


	public static final String VUE = "editComputer";

	private ComputerService computerService;

	private CompanyService companyService;

	private CompanyMapper companyMapper;

	private ComputerMapper computerMapper;

	@ModelAttribute("companies")
	public List<CompanyDTO>  getCompanies() {
		return companyMapper.allModelToDTO(companyService.getAll());
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleGet(@RequestParam("id") long id) throws Exception {
		
		ModelAndView mv = new ModelAndView(VUE);

		ComputerDTOForm computerDTOForm = computerMapper.modelToDtoForm(computerService.findById(id));

		// Add to request
		mv.addObject("computerDTOForm", computerDTOForm);
		return mv;
    }


	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView handlePost(@Valid @ModelAttribute("computerDTOForm") ComputerDTOForm computerDTOForm, BindingResult result) throws Exception {
		
		ModelAndView mv = new ModelAndView(VUE);
		
		String message;
		Boolean resultat;
		List<String> errors = new ArrayList<>();
		if (result.hasErrors()) {
			message = "string.failEdit";
			resultat = false;
			for (ObjectError error : result.getGlobalErrors()) {
				errors.add(error.getDefaultMessage());
			}
            
        } else {
        	computerService.update(computerMapper.dtoFormToModel(computerDTOForm));
        	message = "string.successEdit";
        	resultat = true;
        	
        }

        /* Stockage du résultat et des messages d'erreur dans l'objet request */
		mv.addObject("computerDTOForm", computerDTOForm);
		mv.addObject( ATT_ERRORS_MSG, errors );
        mv.addObject( ATT_RESULTAT, resultat );
        mv.addObject( ATT_MESSAGE, message );

		return mv;
        
        
    }

}
