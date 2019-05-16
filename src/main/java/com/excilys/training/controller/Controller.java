package com.excilys.training.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

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
import com.excilys.training.servlets.Pagination;
import com.excilys.training.validator.ComputerDTOValidator;
import com.excilys.training.validator.Validator;
import com.excilys.training.validator.WebValidator;

@Component
public class Controller {

	private final ComputerDTOValidator computerDTOValidator;
	private final ComputerService computerService;
	private final CompanyService companyService;
	private final ComputerMapper computerMapper;
	private final CompanyMapper companyMapper;
	


	public Controller(ComputerDTOValidator computerDTOValidator, ComputerService computerService,
			CompanyService companyService, ComputerMapper computerMapper, CompanyMapper companyMapper) {
		super();
		this.computerDTOValidator = computerDTOValidator;
		this.computerService = computerService;
		this.companyService = companyService;
		this.computerMapper = computerMapper;
		this.companyMapper = companyMapper;
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

		return computerService.findById(id) != null;
	}

	public boolean CompanyExist(Long id) {

		return companyService.findById(id) != null;
	}

	public void showComputer(Long id) {

		Computer computer = computerService.findById(id);

		if (computer != null) {
			System.out.println(computer);
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
		Computer computertoDelete = computerService.findById(id);
		if (computertoDelete != null) {
			computerService.delete(computertoDelete);
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
			Company company = companyService.findById(id);
			companyService.delete(id);
			System.out.println("Le fabriquant " + company.toString() + " a été supprimé avec succès");
		} catch (CompanyNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CompanyNotDeletedException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

}
