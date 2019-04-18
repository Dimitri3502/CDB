package com.excilys.training.UI;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Ui {


	public void showActions() {

		System.out.println(" "
				+ "\n" + "Actions : "
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
	

	public Map<String, String> createComputer() {
		Map<String, String> inputsCreateComputer = new HashMap<String, String>();
		
		//get the name
		System.out.println("Actions : CreateComputer"
				+ "\n" + "Entrez le nom de l'ordinateur : ");
		String theName = readInputs();
		inputsCreateComputer.put("name", theName);
		
		//get the introducedDate
		System.out.println("Entrez la date d'introduction de l'ordinateur sous le format yyyy-mm-dd: ");
		String theIntroducedDate = readInputs();
		inputsCreateComputer.put("introducedDate", theIntroducedDate);

		//get the discontinuedDate
		System.out.println("Entrez la date d'expiration de l'ordinateur sous le format yyyy-mm-dd: ");
		String theDiscontinuedDate = readInputs();
		inputsCreateComputer.put("discontinuedDate", theDiscontinuedDate);
		
		//get the idCompany
		System.out.println("Entrez l'id du constructeur de l'ordinateur : ");
		String theIdCompany = readInputs();
		inputsCreateComputer.put("idCompany", theIdCompany);
		
		return inputsCreateComputer;
	}
	public void updateComputer() {

		System.out.println("Actions : updateComputer"
				+ "\n" + "Entrez l'id de l'ordinateur à modifier"
				+ "\n" + "Votre choix : ");
	}
	
}
