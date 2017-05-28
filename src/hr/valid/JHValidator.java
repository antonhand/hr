package hr.valid;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import hr.obj.JobHunt;

public class JHValidator {
	public void validate(JobHunt jh, Errors errors) {
		if (!StringUtils.hasLength(jh.getJob().getName())) {
			errors.rejectValue("job.name", "required", "required");
		}
	}

}
