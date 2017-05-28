package hr.valid;


import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import hr.obj.Company;

public class CompanyValidator {

	public void validate(Company company, Errors errors) {
		if (!StringUtils.hasLength(company.getName())) {
			errors.rejectValue("name", "required", "required");
		}
	}

}
