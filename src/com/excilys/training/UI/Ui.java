package com.excilys.training.UI;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Ui {


	public void showActions() {

		System.out.println("Actions : "
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
	
	public Map<String, String> createComputer() {
		Map<String, String> inputsCreateComputer = new HashMap<String, String>();
		
		//get the name
		System.out.println("Actions : CreateComputer"
				+ "\n" + "Entrez le nom de l'ordinateur : ");
		String theName = readInputs();
		inputsCreateComputer.put("name", theName);
		
		//get the introducedDate
		System.out.println("Entrez la date d'introduction de l'ordinateur sous le format dd/mm/yy: ");
		String theIntroducedDate = readInputs();
		inputsCreateComputer.put("introducedDate", theIntroducedDate);
		
		
		//get the idCompany
		System.out.println("Entrez l'id du constructeur de l'ordinateur : ");
		String theIdCompany = readInputs();
		inputsCreateComputer.put("idCompany", theIdCompany);
		
		return inputsCreateComputer;
	}
}
