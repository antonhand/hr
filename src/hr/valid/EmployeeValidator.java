package hr.valid;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import hr.obj.Employee;

public class EmployeeValidator {
	public void validate(Employee emp, Errors errors) {
		if (!StringUtils.hasLength(emp.getName())) {
			errors.rejectValue("name", "required", "required");
		}
		if (!StringUtils.hasLength(emp.getSurname())) {
			errors.rejectValue("surname", "required", "required");
		}
		
	}

}
