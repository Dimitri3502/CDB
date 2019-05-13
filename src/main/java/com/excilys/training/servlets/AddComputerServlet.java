package com.excilys.training.servlets;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
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


@WebServlet(name = "AddComputer", urlPatterns = { "/addComputer" })
public class AddComputerServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -828447276545120635L;
	public static final String VUE = "/WEB-INF/views/addComputer.jsp";
    public static final String CHAMP_COMPUTERNAME = "computerName";
    public static final String CHAMP_INTRODUCED = "introduced";
    public static final String CHAMP_DISCONTINUED = "discontinued";
    public static final String CHAMP_COMPANYID = "companyId";

    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";
    
	private ComputerService computerService;
	private CompanyService companyService;
	private CompanyMapper companyMapper;
	private WebValidator webValidator;
	private ComputerMapper computerMapper;

	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		this.computerService = wac.getBean(ComputerService.class);
		this.companyService = wac.getBean(CompanyService.class);
		this.computerMapper = wac.getBean(ComputerMapper.class);
		this.webValidator = wac.getBean(WebValidator.class);
		this.computerMapper = wac.getBean(ComputerMapper.class);
								

	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		List<CompanyDTO> companies = companyMapper.allModelToDTO(companyService.getAll());

		// Add to request
		request.setAttribute("companies", companies);
    	Utilities.forwardScreen(request, response, VUE);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String resultat;
        /* Récupération des champs du formulaire. */
        String computerName = request.getParameter(CHAMP_COMPUTERNAME);
        String introduced = request.getParameter(CHAMP_INTRODUCED);
        String discontinued = request.getParameter(CHAMP_DISCONTINUED);
        String companyId = request.getParameter(CHAMP_COMPANYID);

        
        ComputerDTO computerDTO = new ComputerDTO();
        computerDTO.setName(computerName);
        computerDTO.setIntroducedDate(introduced);
        computerDTO.setDiscontinuedDate(discontinued);
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId("0".equals(companyId) ? null : companyId);
		computerDTO.setCompanyDTO(companyDTO);
        

        final Validator.Result result = webValidator.check(computerDTO);
        Map<String, String> erreurs = result.getError();
		if (result.isValid()) {
            long id = computerService.create(computerMapper.dtoToModel(computerDTO));
            resultat = "Succès de l'inscription.";
        } else {
            resultat = "Échec de l'inscription.";
        }
        

        /* Stockage du résultat et des messages d'erreur dans l'objet request */
        request.setAttribute( ATT_ERREURS, erreurs );
        request.setAttribute( ATT_RESULTAT, resultat );
        request.setAttribute("computer", computerDTO);
		List<CompanyDTO> companies = companyMapper.allModelToDTO(companyService.getAll());
		request.setAttribute("companies", companies);

        Utilities.forwardScreen(request, response, VUE);
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
