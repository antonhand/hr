package hr.obj;

import java.io.Serializable;

public class JobHunt implements Serializable {
	
	private static final long serialVersionUID = -8394193675395650920L;
	
	private Employee employee;
	private Job job;
	private Integer salaryMin;
	private Integer salaryMax;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
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
		JobHunt other = (JobHunt) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		return true;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Job getJob() {
		return job;
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
	
	public Integer getSalaryMin() {
		return salaryMin;
	}
	
	public Integer getSalaryMax() {
		return salaryMax;
	}
	
	public void setSalary(Integer salaryMin, Integer salaryMax) {
		this.salaryMin = salaryMin;
		this.salaryMax = salaryMax;
	}

	public void setSalaryMin(Integer salaryMin) {
		this.salaryMin = salaryMin;
	}

	public void setSalaryMax(Integer salaryMax) {
		this.salaryMax = salaryMax;
	}

}
