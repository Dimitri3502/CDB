package com.excilys.training.UI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.excilys.training.controller.Controller;

public class Ui {

	public static void main(final String[] args) {

		System.out.println("Actions : "
				+ "\n" + "1 : ListComputers"
				+ "\n" + "2 : ListCompanies"  
				+ "\n" + "3 : ShowComputer"
				+ "\n" + "4 : CreateComputer"
				+ "\n" + "5 : UpdateComputer"
				+ "\n" + "6 : DeleteComputer"
				+ "\n" + "Entrez votre choix : ");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println("Vous avez saisi : " + str);
		int choix = Integer.parseInt(str);
		
		 Controller c = new Controller();
		 switch(choix) {
		 
		 //ListComputers
		 case 1 :
			 System.out.println(c.listComputers());
		 
		 }
		
	}
}
