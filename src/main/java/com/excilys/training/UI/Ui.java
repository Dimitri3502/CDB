package com.excilys.training.UI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.excilys.training.controller.Controller;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.dto.ComputerDTOUi;
import com.excilys.training.mapper.UiDTO.ComputerUiDTOMapper;
import com.excilys.training.validator.ComputerUiValidator;
import com.excilys.training.validator.Validator;

public class Ui {

	private final Controller controller = Controller.getInstance();
	private final ComputerUiValidator createComputerUIValidator = ComputerUiValidator.getInstance();

	public void showActions() {

		System.out.println(" " + "\n" + "Actions : " + "\n" + "0 : Fermer l'application" + "\n" + "1 : ListComputers"
				+ "\n" + "2 : ListCompanies" + "\n" + "3 : ShowComputer" + "\n" + "4 : CreateComputer" + "\n"
				+ "5 : UpdateComputer" + "\n" + "6 : DeleteComputer" + "\n" + "Entrez votre choix : ");
	}

	public String readInputs() {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println("Vous avez saisi : " + str);

		return str;
	}

	public int readInputInt() {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		System.out.println("Vous avez saisi : " + input);

		return input;
	}

	public void showComputer() {

		System.out.println("Actions : ShowComputer" + "\n" + "Entrez l'id de l'ordinateur" + "\n" + "Votre choix : ");
	}

	public void deleteComputer() {

		System.out.println("Actions : deleteComputer" + "\n" + "Entrez l'id de l'ordinateur à supprimer" + "\n"
				+ "Votre choix : ");
	}

	public void createComputer() {
		Map<String, String> inputsCreateComputer = new HashMap<String, String>();

		// get the name
		System.out.println("Actions : CreateComputer" + "\n" + "Entrez le nom de l'ordinateur : ");
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
		
        final Validator.Result result = createComputerUIValidator.check(computerDTOUiCreate);
        if (result.isValid()) {
        	ComputerDTO computerDTOCreate = ComputerUiDTOMapper.getInstance().uiToDTO(computerDTOUiCreate);
            controller.createComputer(computerDTOCreate);
        } else {
            result.values().forEach(System.out::println);
        }
	}

	public void updateComputer() {

		System.out.println(
				"Actions : updateComputer" + "\n" + "Entrez l'id de l'ordinateur à modifier" + "\n" + "Votre choix : ");
	}

	public void menuNextPage() {
		System.out.println(" " + "\n" + "Pagination" + "\n" + "0 : retour menu" + "\n" + "1 : page suivante" + "\n"
				+ "2 : page precedente");
	}

}
