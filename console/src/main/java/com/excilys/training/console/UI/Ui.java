package com.excilys.training.console.UI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.excilys.training.binding.dto.CompanyDTO;
import com.excilys.training.binding.dto.ComputerDTO;
import com.excilys.training.binding.exception.InvalidDiscontinuedDate;
import com.excilys.training.binding.validator.Validator;
import com.excilys.training.console.controller.Controller;
import com.excilys.training.console.dto.ComputerDTOUi;
import com.excilys.training.console.validator.ComputerUiValidator;

@Component
public class Ui {

	private final Controller controller;
	private final ComputerUiValidator createComputerUIValidator;
	

	public Ui(Controller controller, ComputerUiValidator createComputerUIValidator) {
		super();
		this.controller = controller;
		this.createComputerUIValidator = createComputerUIValidator;
	}

	public void start() {

		while (true) {
			showActions();
			int choix = readInputInt();
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
		// delete Computer
		case 7:
			deleteCompany();
			break;
		default:

		}

	}

	public void showActions() {

		System.out.println(" " + "\n" + "Actions : " + "\n" + "0 : Fermer l'application" + "\n" + "1 : ListComputers"
				+ "\n" + "2 : ListCompanies" + "\n" + "3 : ShowComputer" + "\n" + "4 : CreateComputer" + "\n"
				+ "5 : UpdateComputer" + "\n" + "6 : DeleteComputer" + "\n" + "7 : DeleteCompany" + "\n"
				+ "Entrez votre choix : ");
	}

	public String readInputs() {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println("Vous avez saisi : " + str);

		return str;
	}

	public Long readInputLong() {
		Scanner sc = new Scanner(System.in);
		Long input = sc.nextLong();
		System.out.println("Vous avez saisi : " + input);

		return input;
	}

	public int readInputInt() {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		System.out.println("Vous avez saisi : " + input);

		return input;
	}

	public void showComputer() {
		System.out.println("Actions : ShowComputer" + "\n" + "Entrez l'id de l'ordinateur" + "\n" + "Votre choix : ");
		Long id = readInputLong();
		controller.showComputer(id);
	}

	public void deleteComputer() {

		System.out.println("Actions : deleteComputer" + "\n" + "Entrez l'id de l'ordinateur à supprimer" + "\n"
				+ "Votre choix : ");
		Long id = readInputLong();
		controller.deleteComputer(id);
	}

	public void deleteCompany() {

		System.out.println("Actions : deleteCompany" + "\n" + "Entrez l'id du fabriquant à supprimer" + "\n"
				+ "Votre choix : ");
		Long id = readInputLong();
		controller.deleteCompany(id);
	}	
	
	public void createComputer() {
		System.out.println("Actions : CreateComputer");
		ComputerDTOUi computerDTOUiCreate = getInputsComputer();

		final Validator.Result result = createComputerUIValidator.check(computerDTOUiCreate);
		if (result.isValid()) {
			controller.createComputer(computerDTOUiCreate);
		} else {
			result.values().forEach(System.out::println);
		}
	}

	public void updateComputer() {

		System.out.println(
				"Actions : updateComputer" + "\n" + "Entrez l'id de l'ordinateur à modifier" + "\n" + "Votre choix : ");
		long id = readInputLong();
		System.out.println("Ordiateur a modifier : ");
		controller.showComputer(id);
		if (controller.ComputerExist(id)) {
			ComputerDTOUi computerDTOUi = getInputsComputer();

			final Validator.Result result = createComputerUIValidator.check(computerDTOUi);
			if (result.isValid()) {
				controller.updateComputer(id, computerDTOUi);
			} else {
				result.values().forEach(System.out::println);
			}
		}

	}

	public void menuNextPage() {
		System.out.println(" " + "\n" + "Pagination" + "\n" + "0 : retour menu" + "\n" + "1 : page suivante" + "\n"
				+ "2 : page precedente");
	}

	public void listPagination(String str) {
		boolean returnMenu = false;
		int page = 1;
		int size = 10;
		while (!returnMenu) {
			if (str == "computer") {
				List<ComputerDTO> theComputerDTOList;
				try {
					theComputerDTOList = controller.getAllComputerPagined(page, size);
					theComputerDTOList.forEach(System.out::println);
				} catch (InvalidDiscontinuedDate e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if (str == "company") {
				List<CompanyDTO> theCompanyDTOList;
				try {
					theCompanyDTOList = controller.getAllCompanyPagined(page, size);
					theCompanyDTOList.forEach(System.out::println);
				} catch (InvalidDiscontinuedDate e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			menuNextPage();
			String choix2 = readInputs();
			switch (choix2) {
			// retour
			case "0":
				returnMenu = true;
				break;
			// page suivante
			case "1":
				page += 1;
				break;
			// page precedente
			case "2":
				page -= 1;
				break;
			}
		}
	}

	public ComputerDTOUi getInputsComputer() {
		Map<String, String> inputsCreateComputer = new HashMap<String, String>();

		// get the name
		System.out.println("Entrez le nom de l'ordinateur : ");
		String theName = readInputs();
		inputsCreateComputer.put("name", theName);

		// get the introducedDate
		System.out.println("Entrez la date d'introduction de l'ordinateur sous le format yyyy-mm-dd: ");
		String theIntroducedDate = readInputs();
		inputsCreateComputer.put("introducedDate", theIntroducedDate);

		// get the discontinuedDate
		System.out.println("Entrez la date d'expiration de l'ordinateur sous le format yyyy-mm-dd: ");
		String theDiscontinuedDate = readInputs();
		inputsCreateComputer.put("discontinuedDate", theDiscontinuedDate);

		// get the idCompany
		System.out.println("Entrez l'id du constructeur de l'ordinateur : ");
		String theIdCompany = readInputs();
		inputsCreateComputer.put("idCompany", theIdCompany);

		final ComputerDTOUi computerDTOUiCreate = new ComputerDTOUi();
		computerDTOUiCreate.setName(inputsCreateComputer.get("name"));
		computerDTOUiCreate.setIntroducedDate(inputsCreateComputer.get("introducedDate"));
		computerDTOUiCreate.setDiscontinuedDate(inputsCreateComputer.get("discontinuedDate"));
		computerDTOUiCreate.setCompanyId(inputsCreateComputer.get("idCompany"));
		return computerDTOUiCreate;
	}

}
