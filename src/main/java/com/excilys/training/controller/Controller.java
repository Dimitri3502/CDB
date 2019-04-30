package com.excilys.training.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.dto.ComputerDTOUi;
import com.excilys.training.exception.InvalidDateValueException;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.exception.NotFoundException;
import com.excilys.training.exception.ValidatorException;
import com.excilys.training.mapper.UiDTO.ComputerUiDTOMapper;
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

	public void updateComputer(Long id, ComputerDTOUi computerDTOUi) {
		ComputerDTO computerDTO = ComputerUiDTOMapper.getInstance().uiToDTO(computerDTOUi);
		computerDTO.setId(Long.toString(id));
		final Validator.Result result = computerDTOValidator.check(computerDTO);

		if (result.isValid()) {
			computerService.update(computerDTO);
			System.out.println("Ordinateur id : " + id + " modifié");
		} else {
			try {
				throw new ValidatorException(result);
			} catch (ValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public boolean ComputerExist(Long id) {

		return computerService.findById(id).isPresent();
	}
	public boolean CompanyExist(Long id) {

		return companyService.findById(id).isPresent();
	}
	public void showComputer(Long id) {

		Optional<ComputerDTO> computerDTO = computerService.findById(id);

		if (computerDTO.isPresent()) {
			System.out.println(computerDTO.get());
		} else {
			System.out.println("L'ordinateur id = " + id + " n'existe pas");
		}
	}

	public void createComputer(ComputerDTOUi computerDTOUiCreate) {

			ComputerDTO computerDTOCreate = ComputerUiDTOMapper.getInstance().uiToDTO(computerDTOUiCreate);
			final Validator.Result result = computerDTOValidator.check(computerDTOCreate);

			if (result.isValid()) {
				computerService.create(computerDTOCreate);
				long idCreate = computerService.create(computerDTOCreate);
				System.out.println("Ordinateur creer avec l'id : " + idCreate);
			} else {
				try {
					throw new ValidatorException(result);
				} catch (ValidatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		return companyService.getAll();
	}

}
