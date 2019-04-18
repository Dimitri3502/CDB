package com.excilys.training.UI;

import com.excilys.training.controller.Controller;
import com.excilys.training.dao.CompanyDAO;
import com.excilys.training.dao.ComputerDAO;
import com.excilys.training.mapper.CompanyMapper;
import com.excilys.training.mapper.ComputerMapper;
import com.excilys.training.service.CompanyService;
import com.excilys.training.service.ComputerService;

public class Application {

	public static void main(final String[] args) {
		CompanyMapper companyMapper = new CompanyMapper();
		CompanyDAO companyDAO = new CompanyDAO();
		CompanyService companyService = new CompanyService(companyMapper, companyDAO);

		ComputerMapper computerMapper = new ComputerMapper(companyDAO);
		ComputerDAO computerDAO = new ComputerDAO();
		ComputerService computerService = new ComputerService(computerMapper, computerDAO);
		
		Ui vue = new Ui();
		Controller theController = new Controller(companyService, computerService, vue);

		theController.start();

	}
}
