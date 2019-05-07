package com.excilys.training.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.service.CompanyService;
import com.excilys.training.service.ComputerService;
import com.excilys.training.validator.WebValidator;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet(name = "DeleteComputer", urlPatterns = { "/deleteComputer" })
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final ComputerService computerService = ComputerService.getInstance();
	private final CompanyService companyService = CompanyService.getInstance();
	private final WebValidator webValidator = WebValidator.getInstance();      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idsStr = request.getParameter("selection");
		String str[] = idsStr.split(",");
		List<String> idsArray = new ArrayList<String>();
		idsArray = Arrays.asList(str);
		for(String idStr: idsArray){
			Long value = Long.parseLong(idStr);
	        Optional<ComputerDTO> computerDTO = computerService.findById(value);
	        computerService.delete(computerDTO.get());
		}
		response.sendRedirect("dashboard");
        

        
	}

}
