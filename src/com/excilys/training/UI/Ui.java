package com.excilys.training.UI;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;


public class Ui {


	public void showActions() {

		System.out.println("Actions : "
				+ "\n" + "0 : Fermer l'application"
				+ "\n" + "1 : ListComputers"
				+ "\n" + "2 : ListCompanies"  
				+ "\n" + "3 : ShowComputer"
				+ "\n" + "4 : CreateComputer"
				+ "\n" + "5 : UpdateComputer"
				+ "\n" + "6 : DeleteComputer"
				+ "\n" + "Entrez votre choix : ");
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

		System.out.println("Actions : ShowComputer"
				+ "\n" + "Entrez l'id de l'ordinateur"
				+ "\n" + "Votre choix : ");
	}
	
	public void deleteComputer() {

		System.out.println("Actions : deleteComputer"
				+ "\n" + "Entrez l'id de l'ordinateur à supprimer"
				+ "\n" + "Votre choix : ");
	}
	
	public ComputerDTO createComputer() {
		ComputerDTO computerDTO = new ComputerDTO();
		
		//get the name
		System.out.println("Actions : CreateComputer"
				+ "\n" + "Entrez le nom de l'ordinateur : ");
		String theName = readInputs();
		computerDTO.setName(theName);
		
		//get the introducedDate
		System.out.println("Entrez la date d'introduction de l'ordinateur sous le format yyyy-mm-dd: ");
		String theIntroducedDate = readInputs();
		computerDTO.setIntroducedDate(theIntroducedDate);

		//get the discontinuedDate
		System.out.println("Entrez la date d'expiration de l'ordinateur sous le format yyyy-mm-dd: ");
		String theDiscontinuedDate = readInputs();
		computerDTO.setDiscontinuedDate(theDiscontinuedDate);
		
		//get the idCompany
		System.out.println("Entrez l'id du constructeur de l'ordinateur : ");
		String theIdCompany = readInputs();
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(theIdCompany);
		computerDTO.setCompanyDTO(companyDTO);
		
		return computerDTO;
	}

	public void updateComputer() {

		System.out.println("Actions : updateComputer"
				+ "\n" + "Entrez l'id de l'ordinateur à modifier"
				+ "\n" + "Votre choix : ");
	}
	
}
