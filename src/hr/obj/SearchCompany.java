package hr.obj;

import java.util.List;

public class SearchCompany {
	
	private JobHunt jobHunt;
	private List<Integer> companiesID;
	
	public JobHunt getJobHunt() {
		return jobHunt;
	}
	
	public void setJobHunt(JobHunt jobHunt) {
		this.jobHunt = jobHunt;
	}

	public List<Integer> getCompaniesID() {
		return companiesID;
	}

	public void setCompaniesID(List<Integer> companiesID) {
		this.companiesID = companiesID;
	}

}
