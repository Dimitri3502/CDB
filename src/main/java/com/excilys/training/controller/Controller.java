package com.excilys.training.controller;

import java.util.List;
import java.util.Map;

import com.excilys.training.UI.Ui;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.ComputerNotFoundException;
import com.excilys.training.exception.InvalidDateValueException;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.exception.NotFoundException;
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
			if (choix == 0) {
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
			listPagination("computer");
			break;

		// List Companies
		case 2:
			listPagination("company");
			break;

		// show Computer
		case 3:
			showComputer();
			break;

		// create Computer
		case 4:
			createComputer();
			break;

		// update Computer
		case 5:
			updateComputer();
			break;

		// delete Computer
		case 6:
			deleteComputer();
			break;
		default:

		}

	}

	private ComputerDTO inputsToComputerDTO(Map<String, String> inputsCreateComputer) {
		// creer dto
		ComputerDTO computerDTOCreate = new ComputerDTO();
		computerDTOCreate.setName(inputsCreateComputer.get("name"));
		computerDTOCreate.setIntroducedDate(inputsCreateComputer.get("introducedDate"));
		computerDTOCreate.setDiscontinuedDate(inputsCreateComputer.get("discontinuedDate"));
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(inputsCreateComputer.get("idCompany"));
		computerDTOCreate.setCompanyDTO(companyDTO);
		return computerDTOCreate;

	}

	private void updateComputer() {
		try {
			vue.updateComputer();
			String idUpdate = vue.readInputs();
			ComputerDTO computerDTOtoUpdate = computerService.findById(idUpdate);
			System.out.println("Ordiateur a modifier : " + computerDTOtoUpdate.toString());
			Map<String, String> inputsNewComputer = vue.createComputer();
			ComputerDTO computerDTOUpdate = this.inputsToComputerDTO(inputsNewComputer);
			computerDTOUpdate.setId(idUpdate);
			computerService.update(computerDTOUpdate);
		} catch (InvalidDateValueException | NotFoundException | InvalidDiscontinuedDate e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
	}

	private void showComputer() {

		try {
			vue.showComputer();
			String id = vue.readInputs();
			ComputerDTO computerDTO;
			computerDTO = computerService.findById(id);
			System.out.println(computerDTO);
		} catch (NotFoundException | InvalidDiscontinuedDate e) {
			System.out.println(e.getMessage());
		}
	}

	private void createComputer() {
		Map<String, String> inputsCreateComputer = vue.createComputer();
		ComputerDTO computerDTOCreate = this.inputsToComputerDTO(inputsCreateComputer);

		try {
			long idCreate = computerService.create(computerDTOCreate);
			System.out.println("Ordinateur creer avec l'id : " + idCreate);
		} catch (InvalidDateValueException | InvalidDiscontinuedDate e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	private void deleteComputer() {
		try {
			vue.deleteComputer();
			String idDelete = vue.readInputs();
			ComputerDTO computerDTOtoDelete = computerService.findById(idDelete);
			computerService.delete(computerDTOtoDelete);
			System.out.println("T'es un gogole toi");
		} catch (InvalidDateValueException | NotFoundException | InvalidDiscontinuedDate e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	private void listPagination(String str) {
		try {
			boolean returnMenu = false;
			int n = 1;
			while (!returnMenu) {
				if (str=="computer") {
					List<ComputerDTO> theComputerDaoList;
					theComputerDaoList = computerService.getAll(10, n);
					theComputerDaoList.forEach(System.out::println);					
				}
				if (str=="company") {
					List<CompanyDTO> theCompanyList;
					theCompanyList = companyService.getAll(10, n);
					theCompanyList.forEach(System.out::println);			
				}
				vue.menuNextPage();
				String choix2 = vue.readInputs();
				switch (choix2) {
				// retour
				case "0":
					returnMenu = true;
					break;
				// page suivante
				case "1":
					n += 10;
					break;
				// page precedente
				case "2":
					n -= 10;
					break;
				}
			}

		} catch (InvalidDiscontinuedDate e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	private void listCompanies() {
		List<CompanyDTO> theCompanyList;
		theCompanyList = companyService.getAll(10, 10);
		theCompanyList.forEach(System.out::println);
	}
}
