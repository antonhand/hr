package hr.obj;

import java.io.Serializable;

public class VacancyReq implements Serializable {
	
	private static final long serialVersionUID = -6182598717208982526L;
	
	private Vacancy vacancy;
	private Job job;
	private Company company;
	private Integer expMonths;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((vacancy == null) ? 0 : vacancy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VacancyReq other = (VacancyReq) obj;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (vacancy == null) {
			if (other.vacancy != null)
				return false;
		} else if (!vacancy.equals(other.vacancy))
			return false;
		return true;
	}

	public Vacancy getVacancy() {
		return vacancy;
	}

	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}

	public Job getJob() {
		return job;
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
	
	public Integer getExpMonths() {
		return expMonths;
	}
	
	public void setExpMonths(Integer expMonths) {
		this.expMonths = expMonths;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
