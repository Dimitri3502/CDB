package com.excilys.training.servlets;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 

@WebServlet(name = "EditComputer", urlPatterns = { "/editComputer" })
public class EditComputerServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/views/editComputer.jsp";
    public static final String CHAMP_ID = "id";
    public static final String CHAMP_COMPUTERNAME = "computerName";
    public static final String CHAMP_INTRODUCED = "introduced";
    public static final String CHAMP_DISCONTINUED = "discontinued";
    public static final String CHAMP_COMPANYID = "companyId";

    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";
    
	private final ComputerService computerService = null;
	private final CompanyService companyService = null;
	private final ComputerMapper computerMapper =  null;
	private final Pagination pagination = null;
	private final CompanyMapper companyMapper = null;
	private final WebValidator webValidator = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String idStr = request.getParameter("id");

    	long id = Long.parseLong(idStr);
    	ComputerDTO computer = computerMapper.modelToDto(computerService.findById(id).get());
		List<CompanyDTO> companies = companyMapper.allModelToDTO(companyService.getAll());

		// Add to request
		request.setAttribute("computer", computer);
		request.setAttribute("companies", companies);
    	Utilities.forwardScreen(request, response, VUE);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String resultat;
        /* Récupération des champs du formulaire. */
        String id = request.getParameter(CHAMP_ID);
        String computerName = request.getParameter(CHAMP_COMPUTERNAME);
        String introduced = request.getParameter(CHAMP_INTRODUCED);
        String discontinued = request.getParameter(CHAMP_DISCONTINUED);
        String companyId = request.getParameter(CHAMP_COMPANYID);

        
        ComputerDTO computerDTO = new ComputerDTO();
        computerDTO.setId(id);
        computerDTO.setName(computerName);
        computerDTO.setIntroducedDate(introduced);
        computerDTO.setDiscontinuedDate(discontinued);
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId("0".equals(companyId) ? null : companyId);
		computerDTO.setCompanyDTO(companyDTO);
        

        final Validator.Result result = webValidator.check(computerDTO);
        Map<String, String> erreurs = result.getError();
		if (result.isValid()) {
            computerService.update(computerMapper.dtoToModel(computerDTO));
            resultat = "Modification effectuée avec succès.";
        } else {
            resultat = "Échec de la modification.";
        }
        

        /* Stockage du résultat et des messages d'erreur dans l'objet request */
        request.setAttribute( ATT_ERREURS, erreurs );
        request.setAttribute( ATT_RESULTAT, resultat );
        request.setAttribute("computer", computerDTO);
		List<CompanyDTO> companies =  companyMapper.allModelToDTO(companyService.getAll());
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
