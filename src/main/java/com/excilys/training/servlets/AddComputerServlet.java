package com.excilys.training.servlets;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.dto.ComputerDTOUi;
import com.excilys.training.mapper.UiDTO.ComputerUiDTOMapper;
import com.excilys.training.service.CompanyService;
import com.excilys.training.service.ComputerService;
import com.excilys.training.validator.Validator;
import com.excilys.training.validator.WebValidator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class AddComputerServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/views/addComputer.jsp";
    public static final String CHAMP_COMPUTERNAME = "computerName";
    public static final String CHAMP_INTRODUCED = "introduced";
    public static final String CHAMP_DISCONTINUED = "discontinued";
    public static final String CHAMP_COMPANYID = "companyId";

    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";
    
	private final ComputerService computerService = ComputerService.getInstance();
	private final CompanyService companyService = CompanyService.getInstance();
	private final ComputerUiDTOMapper computerUiDTOMapper = ComputerUiDTOMapper.getInstance();
	private final WebValidator webValidator = WebValidator.getInstance();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        
                
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		List<CompanyDTO> companies = companyService.getAll();

		// Add to request
		request.setAttribute("companies", companies);
    	Utilities.forwardScreen(request, response, VUE);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String resultat;
        /* Récupération des champs du formulaire. */
        String computerName = request.getParameter(CHAMP_COMPUTERNAME);
        String introduced = request.getParameter(CHAMP_INTRODUCED);
        String discontinued = request.getParameter(CHAMP_DISCONTINUED);
        String companyId = request.getParameter(CHAMP_COMPANYID);

        
        ComputerDTOUi computerDTOUi = new ComputerDTOUi();
        computerDTOUi.setName(computerName);
        computerDTOUi.setIntroducedDate(introduced);
        computerDTOUi.setDiscontinuedDate(discontinued);
        computerDTOUi.setCompanyId(("0".equals(companyId)) ? null : companyId);
        

        final Validator.Result result = webValidator.check(computerDTOUi);
        Map<String, String> erreurs = result.getError();
		if (result.isValid()) {
            ComputerDTO computerDTO = computerUiDTOMapper.uiToDTO(computerDTOUi);
            long id = computerService.create(computerDTO);
            resultat = "Succès de l'inscription.";
        } else {
            resultat = "Échec de l'inscription.";
        }
        

        /* Stockage du résultat et des messages d'erreur dans l'objet request */
        request.setAttribute( ATT_ERREURS, erreurs );
        request.setAttribute( ATT_RESULTAT, resultat );
        
		List<CompanyDTO> companies = companyService.getAll();
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
