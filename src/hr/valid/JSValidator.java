package hr.valid;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import hr.obj.JobStory;

public class JSValidator {
	public void validate(JobStory js, Errors errors) {
		if (!StringUtils.hasLength(js.getJob().getName())) {
			errors.rejectValue("job.name", "required", "required");
		}
		if (js.getSalary() == 0) {
			errors.rejectValue("salary", "required", "required");
		}
		if (js.getStartDate() == null) {
			errors.rejectValue("startDate", "required", "required");
		}
		
	}

}
