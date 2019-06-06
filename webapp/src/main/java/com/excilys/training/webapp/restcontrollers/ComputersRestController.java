package com.excilys.training.webapp.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.training.binding.dto.ComputerDTO;
import com.excilys.training.binding.exception.ComputerNotFoundException;
import com.excilys.training.binding.mapper.ComputerMapper;
import com.excilys.training.service.IComputerService;
import com.excilys.training.webapp.dto.ComputerDTOForm;
import com.excilys.training.webapp.mapper.ComputerFormMapper;

@RestController
@RequestMapping("/computers")
public class ComputersRestController {

	@Autowired
	private IComputerService computerService;

	@Autowired
	private ComputerMapper computerMapper;
	
	@Autowired
	private ComputerFormMapper computerFormMapper;
	
	@GetMapping
	public List<ComputerDTO> getcomputers() {
		return computerMapper.allModelToDTO(computerService.getAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ComputerDTO> getComputer(@PathVariable("id") Long id) {
		System.out.println(" looking for computer");
		ComputerDTO computerDTO;
		try {
			computerDTO = computerMapper.modelToDto(computerService.findById(id));
		} catch (ComputerNotFoundException e) {
			System.out.println("computer not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<ComputerDTO>(computerDTO, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ComputerDTOForm> createComputer(@RequestBody ComputerDTOForm computerDTO) {

		computerService.create(computerFormMapper.dtoFormToModel(computerDTO));

		return new ResponseEntity<ComputerDTOForm>(computerDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deleteComputer(@PathVariable Long id) {
		computerService.delete(id);

		return new ResponseEntity<Long>(id, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ComputerDTOForm> updateComputer(@PathVariable Long id, @RequestBody ComputerDTOForm computerDTO) {
		computerDTO.setId(id);
		computerService.update(computerFormMapper.dtoFormToModel(computerDTO));

		return new ResponseEntity<ComputerDTOForm>(computerDTO, HttpStatus.OK);
	}

}
