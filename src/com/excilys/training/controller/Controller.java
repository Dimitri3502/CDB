package com.excilys.training.controller;

import java.util.List;
import java.util.Map;

import com.excilys.training.UI.Ui;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.model.Computer;
import com.excilys.training.service.CompanyService;
import com.excilys.training.service.ComputerService;

public class Controller {
	private CompanyService companyService;
	private ComputerService computerService;
	private Ui vue;

	public Controller(CompanyService companyService, ComputerService computerService, Ui vue) {
		super();
		this.companyService = companyService;
		this.computerService = computerService;
		this.vue = vue;
	}

	public void start() {

		while (true) {
			vue.showActions();
			int choix = vue.readInputInt();
			runChoice(choix);
		}
	}

	public void runChoice(int choix) {

		switch (choix) {

		// ListComputers
		case 1:
			String theComputerList = computerService.listComputers();
			System.out.println(theComputerList);
			break;
			
		case 2:
			companyService.listCompanies();
			break;
			
		case 3:
			vue.showComputer();
			String str = vue.readInputs();
			int id = Integer.parseInt(str);
			computerService.showComputer(id);
			break;
			
		case 4:
			Map<String, String> inputsCreateComputer = vue.createComputer();
			//creer dto
			ComputerDTO computerDTO = new ComputerDTO();
			computerService.createComputer(computerDTO);
			break;
			
		case 5:
			computerService.updateComputer();
			break;
			
		case 6:
			computerService.deleteComputer();
			break;

		}
	}

}
