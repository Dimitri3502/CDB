package com.excilys.training.controller;

import java.util.List;
import java.util.Map;

import com.excilys.training.UI.Ui;
import com.excilys.training.dto.CompanyDTO;
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
			if (choix==0) {
				System.out.println("Attention à la marche en descendant du train");
				break;
			}
			runChoice(choix);
		}
	}

	public void runChoice(int choix) {

		switch (choix) {
			
		
		// List Computers
		case 1:
			List<ComputerDTO> theComputerDaoList = computerService.getAll();
			theComputerDaoList.forEach(System.out::println);
			break;
			
		// List Companies
		case 2:
			List<CompanyDTO> theCompanyList = companyService.getAll();
			theCompanyList.forEach(System.out::println);
			break;
		
		// show Computer
		case 3:
			vue.showComputer();
			String id = vue.readInputs();
			ComputerDTO computerDTO = computerService.findById(id);
			System.out.println(computerDTO);
			break;
		
		// 	create Computer
		case 4:
			ComputerDTO inputsCreateComputer = vue.createComputer();
			//creer dto
			long idCreate = computerService.create(inputsCreateComputer);
			System.out.println("Ordinateur creer avec l'id : " + idCreate);
			break;
		
		// update Computer
		case 5:
			vue.updateComputer();
			String idUpdate = vue.readInputs();
			ComputerDTO computerDTOtoUpdate = computerService.findById(idUpdate);
			System.out.println("Ordiateur a modifier : " + computerDTOtoUpdate.toString());
			ComputerDTO inputsNewComputer = vue.createComputer();
			inputsNewComputer.setId(idUpdate);
			computerService.update(inputsNewComputer);
			break;
		
		// delete Computer
		case 6: 
			vue.deleteComputer();
			String idDelete = vue.readInputs();
			ComputerDTO computerDTOtoDelete = computerService.findById(idDelete);
			computerService.delete(computerDTOtoDelete);
			break;
		
		}
	}

}
