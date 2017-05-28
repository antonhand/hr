package hr.obj;

import java.util.List;

public class Vacancy extends BaseEntity {
	
	private Company company;
	private Job job;
	private Integer salary;
	private Education education;
	private List<VacancyReq> vacancyReqs;
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Job getJob() {
		return job;
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
	
	public Integer getSalary() {
		return salary;
	}
	
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	public Education getEducation() {
		return education;
	}
	
	public void setEducation(Education education) {
		this.education = education;
	}
	
	public List<VacancyReq> getVacancyReqs() {
		return vacancyReqs;
	}
	
	public void setVacancyReqs(List<VacancyReq> vacancyReqs) {
		this.vacancyReqs = vacancyReqs;
	}
	
	public void addVacancyReq(VacancyReq vacancyReq) {
		this.vacancyReqs.add(vacancyReq);
	}
	
}
