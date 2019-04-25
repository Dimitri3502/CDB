package com.excilys.training.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.InvalidDateValueException;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.exception.NotFoundException;
import com.excilys.training.exception.ValidatorException;
import com.excilys.training.service.CompanyService;
import com.excilys.training.service.ComputerService;
import com.excilys.training.validator.ComputerDTOValidator;
import com.excilys.training.validator.Validator;

public class Controller {
	private static Controller instance;

	private final ComputerDTOValidator computerDTOValidator = ComputerDTOValidator.getInstance();
	private final CompanyService companyService = CompanyService.getInstance();
	private final ComputerService computerService = ComputerService.getInstance();

	private Controller() {
	}

	public static Controller getInstance() {
		if (Objects.isNull(instance)) {
			instance = new Controller();
		}
		return instance;
	}

//	public void updateComputer(Long id) {
//		try {
//			vue.updateComputer();
//			String idUpdate = vue.readInputs();
//			ComputerDTO computerDTOtoUpdate = computerService.findById(id);
//			System.out.println("Ordiateur a modifier : " + computerDTOtoUpdate.toString());
//			Map<String, String> inputsNewComputer = vue.createComputer();
//			ComputerDTO computerDTOUpdate = this.inputsToComputerDTO(inputsNewComputer);
//			computerDTOUpdate.setId(id);
//			computerService.update(computerDTOUpdate);
//		} catch (InvalidDateValueException | NotFoundException | InvalidDiscontinuedDate e1) {
//			// TODO Auto-generated catch block
//			System.out.println(e1.getMessage());
//		}
//	}

	public void showComputer(Long id) {

		Optional<ComputerDTO> computerDTO = computerService.findById(id);

		if (computerDTO.isPresent()) {
			System.out.println(computerDTO.get());
		} else {
			System.out.println("L'ordinateur id = " + id + " n'existe pas");
		}
	}

	public void createComputer(ComputerDTO computerDTOCreate) {
		try {
			final Validator.Result result = computerDTOValidator.check(computerDTOCreate);

			if (result.isValid()) {
				computerService.create(computerDTOCreate);
				long idCreate = computerService.create(computerDTOCreate);
				System.out.println("Ordinateur creer avec l'id : " + idCreate);
			} else {
				throw new ValidatorException(result);
			}
		} catch (InvalidDateValueException | InvalidDiscontinuedDate e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ValidatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteComputer(Long id) {
		Optional<ComputerDTO> computerDTOtoDelete = computerService.findById(id);
		if (computerDTOtoDelete.isPresent()) {
			computerService.delete(computerDTOtoDelete.get());
		} else {
			System.out.println("L'ordinateur id = " + id + " n'existe pas");
		}
	}

	public List<ComputerDTO> getAllComputerPagined(int limit, int offset) throws InvalidDiscontinuedDate {
		return computerService.getAll(limit, offset);
	}

	public List<CompanyDTO> getAllCompanyPagined(int limit, int offset) throws InvalidDiscontinuedDate {
		return companyService.getAll(limit, offset);
	}

}
