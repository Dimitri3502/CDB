package com.excilys.training.binding.validator;

import static com.excilys.training.binding.validator.ValidatorUtils.isBlank;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.excilys.training.binding.dto.CompanyDTO;
import com.excilys.training.binding.dto.ComputerDTO;
import com.excilys.training.binding.exception.CompanyNotFoundException;
import com.excilys.training.binding.mapper.FindCompanyById;



@Component()
public class ComputerDTOValidator extends Validator<ComputerDTO> {


	private final FindCompanyById companyService;

	public ComputerDTOValidator(FindCompanyById companyService) {
		super();
		this.companyService = companyService;
	}



	@Override
	protected Map<String, String> validation(ComputerDTO toValidate) {

		HashMap<String, String> errors = new HashMap<>();
		if (isBlank(toValidate.getName())) {
			errors.put("name", "Le nom ne peux pas être nul ou vide.");
		}

		if (Objects.nonNull(toValidate.getIntroducedDate()) && (Objects.nonNull(toValidate.getDiscontinuedDate()))
				&& LocalDate.parse(toValidate.getIntroducedDate())
						.isAfter(LocalDate.parse(toValidate.getDiscontinuedDate()))) {
			errors.put("discontinued", "La date d'expiration ne peux pas être avant la date d'introduction.");
		}

		final CompanyDTO companyDTO = toValidate.getCompanyDTO();

		try {
			if (Objects.nonNull(companyDTO.getId())) {
				companyService.findById(Long.parseLong(companyDTO.getId()));
				
			}
		} catch (NumberFormatException e) {
			errors.put("companyId", "L'id fabriquant est mal formé");
		} catch (CompanyNotFoundException e) {
			// TODO Auto-generated catch block
			errors.put("companyId", "Le fabriquant avec l'id " + companyDTO.getId() + " n'existe pas");
		}

		return errors;
	}

}
