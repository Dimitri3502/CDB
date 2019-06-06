package com.excilys.training.console.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import com.excilys.training.binding.dto.CompanyDTO;
import com.excilys.training.binding.dto.ComputerDTO;
import com.excilys.training.binding.exception.CompanyNotDeletedException;
import com.excilys.training.binding.exception.CompanyNotFoundException;
import com.excilys.training.binding.exception.ComputerNotFoundException;
import com.excilys.training.binding.exception.InvalidDiscontinuedDate;
import com.excilys.training.binding.mapper.CompanyMapper;
import com.excilys.training.binding.mapper.ComputerMapper;
import com.excilys.training.binding.validator.ComputerDTOValidator;
import com.excilys.training.binding.validator.Validator;
import com.excilys.training.console.dto.ComputerDTOUi;
import com.excilys.training.console.mapper.ComputerUiDTOMapper;
import com.excilys.training.core.Company;
import com.excilys.training.core.Computer;
import com.excilys.training.service.ICompanyService;
import com.excilys.training.service.IComputerService;

@Component
public class Controller {

	private final ComputerDTOValidator computerDTOValidator;
	private final IComputerService computerService;
	private final ICompanyService companyService;
	private final ComputerMapper computerMapper;
	private final CompanyMapper companyMapper;
	private final ComputerUiDTOMapper computerUiDTOMapper;

	public Controller(ComputerDTOValidator computerDTOValidator, IComputerService computerService,
			ComputerUiDTOMapper computerUiDTOMapper, ICompanyService companyService, ComputerMapper computerMapper, CompanyMapper companyMapper) {
		super();
		this.computerDTOValidator = computerDTOValidator;
		this.computerService = computerService;
		this.companyService = companyService;
		this.computerMapper = computerMapper;
		this.companyMapper = companyMapper;
		this.computerUiDTOMapper = computerUiDTOMapper;
	}

	public void updateComputer(Long id, ComputerDTOUi computerDTOUi) {
		ComputerDTO computerDTO = computerUiDTOMapper.uiToDTO(computerDTOUi);
		computerDTO.setId(id);
		final Validator.Result result = computerDTOValidator.check(computerDTO);

		if (result.isValid()) {
			computerService.update(computerMapper.dtoToModel(computerDTO));
			System.out.println("Ordinateur id : " + id + " modifié");
		} else {
			System.out.println("error occured");
		}

	}

	public boolean ComputerExist(Long id) {

		return computerService.isPresent(id);
	}

	public boolean CompanyExist(Long id) {

		return companyService.isPresent(id);
	}

	public void showComputer(Long id) {

		Computer computer;
		try {
			computer = computerService.findById(id);
			System.out.println(computer);
		} catch (ComputerNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public void createComputer(ComputerDTOUi computerDTOUiCreate) {

		ComputerDTO computerDTOCreate = computerUiDTOMapper.uiToDTO(computerDTOUiCreate);
		final Validator.Result result = computerDTOValidator.check(computerDTOCreate);

		if (result.isValid()) {
			long idCreate = computerService.create(computerMapper.dtoToModel(computerDTOCreate));
			System.out.println("Ordinateur creer avec l'id : " + idCreate);
		} else {
			System.out.println("error occured");
		}
	}

	public void deleteComputer(Long id) {
		Computer computertoDelete;
		try {
			computertoDelete = computerService.findById(id);
			computerService.delete(computertoDelete);
		} catch (ComputerNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public List<ComputerDTO> getAllComputerPagined(int page, int limit) throws InvalidDiscontinuedDate {
		return computerMapper.allModelToDTO(computerService.getAll(page, limit));
	}

	public List<CompanyDTO> getAllCompanyPagined(int page, int limit) throws InvalidDiscontinuedDate {
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
