package com.excilys.training.UI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.excilys.training.controller.Controller;
import com.excilys.training.service.CompanyService;
import com.excilys.training.service.ComputerService;

public class Application {

	public static void main(final String[] args) {
		CompanyService companyService = new CompanyService();
		ComputerService computerService = new ComputerService();
		Ui vue = new Ui();
		Controller theController = new Controller(companyService, computerService, vue);

		theController.start();

	}
}
