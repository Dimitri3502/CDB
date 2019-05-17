package com.excilys.training.servlets;
import static com.excilys.training.servlets.CONSTANTES.ATT_ERREURS;
import static com.excilys.training.servlets.CONSTANTES.ATT_RESULTAT;
import static com.excilys.training.servlets.CONSTANTES.CHAMP_COMPANYID;
import static com.excilys.training.servlets.CONSTANTES.CHAMP_COMPUTERNAME;
import static com.excilys.training.servlets.CONSTANTES.CHAMP_DISCONTINUED;
import static com.excilys.training.servlets.CONSTANTES.CHAMP_INTRODUCED;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


@Controller
@RequestMapping("/addComputer")
public class AddComputerServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -828447276545120635L;
	public static final String VUE = "addComputer";
    
	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyMapper companyMapper;
	

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
    	      BindingResult result, Model m) throws Exception {
		
		ModelAndView mv = new ModelAndView(VUE);
		
		String resultat;
		if (result.hasErrors()) {
			resultat = "Échec de l'inscription.";
            
        } else {
//        	long id = computerService.create(computerMapper.dtoToModel(computerDTO));
            resultat = "Succès de l'inscription.";
        }
        

        /* Stockage du résultat et des messages d'erreur dans l'objet request */
        mv.addObject( ATT_RESULTAT, resultat );
        mv.addObject("computerDTOForm", computerDTOForm);
		List<CompanyDTO> companies = companyMapper.allModelToDTO(companyService.getAll());
		mv.addObject("companies", companies);

		return mv;
        
        
    }
}
