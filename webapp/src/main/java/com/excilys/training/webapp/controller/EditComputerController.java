package com.excilys.training.webapp.controller;
import static com.excilys.training.webapp.controller.CONSTANTES.ATT_ERRORS_MSG;
import static com.excilys.training.webapp.controller.CONSTANTES.ATT_MESSAGE;
import static com.excilys.training.webapp.controller.CONSTANTES.ATT_RESULTAT;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.training.binding.dto.CompanyDTO;
import com.excilys.training.binding.mapper.CompanyMapper;
import com.excilys.training.binding.mapper.ComputerMapper;
import com.excilys.training.service.ICompanyService;
import com.excilys.training.service.IComputerService;
import com.excilys.training.webapp.dto.ComputerDTOForm;
import com.excilys.training.webapp.mapper.ComputerFormMapper;
 

@Controller
@RequestMapping("/editComputer")
public class EditComputerController{
	
	
    public EditComputerController(IComputerService computerService, ICompanyService companyService,
			CompanyMapper companyMapper, ComputerFormMapper computerFormMapper) {
		super();
		this.computerService = computerService;
		this.companyService = companyService;
		this.companyMapper = companyMapper;
		this.computerFormMapper = computerFormMapper;
	}


	public static final String VUE = "editComputer";

	private IComputerService computerService;

	private ICompanyService companyService;

	private CompanyMapper companyMapper;

	private ComputerFormMapper computerFormMapper;

	@ModelAttribute("companies")
	public List<CompanyDTO>  getCompanies() {
		return companyMapper.allModelToDTO(companyService.getAll());
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleGet(@RequestParam("id") long id) throws Exception {
		
		ModelAndView mv = new ModelAndView(VUE);

		ComputerDTOForm computerDTOForm = computerFormMapper.modelToDtoForm(computerService.findById(id));

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
        	computerService.update(computerFormMapper.dtoFormToModel(computerDTOForm));
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
