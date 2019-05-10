package com.excilys.training.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.dto.ComputerDTOUi;
import com.excilys.training.exception.CompanyNotDeletedException;
import com.excilys.training.exception.CompanyNotFoundException;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.exception.ValidatorException;
import com.excilys.training.mapper.CompanyMapper;
import com.excilys.training.mapper.ComputerMapper;
import com.excilys.training.mapper.UiDTO.ComputerUiDTOMapper;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.service.CompanyService;
import com.excilys.training.service.ComputerService;
import com.excilys.training.validator.ComputerDTOValidator;
import com.excilys.training.validator.Validator;

public class Controller {
	private static Controller instance;

	private final ComputerDTOValidator computerDTOValidator = ComputerDTOValidator.getInstance();
	private final CompanyService companyService = CompanyService.getInstance();
	private final ComputerService computerService = ComputerService.getInstance();
	private final ComputerMapper computerMapper = ComputerMapper.getInstance();
	private final CompanyMapper companyMapper = CompanyMapper.getInstance();
	
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
			computerService.update(computerMapper.dtoToModel(computerDTO));
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

		Optional<Computer> computer = computerService.findById(id);

		if (computer.isPresent()) {
			System.out.println(computer.get());
		} else {
			System.out.println("L'ordinateur id = " + id + " n'existe pas");
		}
	}

	public void createComputer(ComputerDTOUi computerDTOUiCreate) {

		ComputerDTO computerDTOCreate = ComputerUiDTOMapper.getInstance().uiToDTO(computerDTOUiCreate);
		final Validator.Result result = computerDTOValidator.check(computerDTOCreate);

		if (result.isValid()) {
			long idCreate = computerService.create(computerMapper.dtoToModel(computerDTOCreate));
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
		Optional<Computer> computertoDelete = computerService.findById(id);
		if (computertoDelete.isPresent()) {
			computerService.delete(computertoDelete.get());
		} else {
			System.out.println("L'ordinateur id = " + id + " n'existe pas");
		}
	}

	public List<ComputerDTO> getAllComputerPagined(int limit, int offset) throws InvalidDiscontinuedDate {
		return computerMapper.allModelToDTO(computerService.getAll(limit, offset));
	}

	public List<CompanyDTO> getAllCompanyPagined(int limit, int offset) throws InvalidDiscontinuedDate {
		return companyMapper.allModelToDTO(companyService.getAll());
	}

	public void deleteCompany(Long id) {
		try {
			Optional<Company> company = companyService.findById(id);
			companyService.delete(id);
			System.out.println("Le fabriquant " + company.get().toString() + " a été supprimé avec succès");
		} catch (CompanyNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CompanyNotDeletedException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

}
