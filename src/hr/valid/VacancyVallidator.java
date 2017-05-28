package hr.valid;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import hr.obj.Vacancy;
import hr.obj.VacancyReq;

public class VacancyVallidator {
	public void validate(Vacancy vac, Errors errors) {
		if (!StringUtils.hasLength(vac.getJob().getName())) {
			errors.rejectValue("job.name", "required", "required");
		}
		if (vac.getSalary() == null) {
			errors.rejectValue("salary", "required", "required");
		}
		List<VacancyReq> vr = vac.getVacancyReqs();
		for(int i = 0; i < vr.size(); i++){
			if(vr.get(i).getJob().getName().equals("") && vr.get(i).getExpMonths() == null){
				continue;
			}
			if (!StringUtils.hasLength(vr.get(i).getJob().getName())) {
				errors.rejectValue("vacancyReqs[" + i + "].job.name", "required", "required");
			}
			if (vr.get(i).getExpMonths() == null || vr.get(i).getExpMonths() <= 0) {
				errors.rejectValue("vacancyReqs[" + i + "].expMonths", "required", "required");
			}
		}
	}

}
