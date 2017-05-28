package hr.obj;

import java.util.Set;

public class Employee extends BaseEntity {
	
	private String surname;
	private String name;
	private String midname;
	private String address;
	private Education education;
	private Set<JobHunt> jobHunt;
	private Set<JobStory> jobStory;
	
	public void setJobHunt(Set<JobHunt> jobHunt) {
		this.jobHunt = jobHunt;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMidname() {
		return midname;
	}
	
	public void setMidname(String midname) {
		this.midname = midname;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Education getEducation() {
		return education;
	}
	
	public void setEducation(Education education) {
		this.education = education;
	}
	
	public String getFullname() {
		String fullname = surname + ' ' + name;
		
		if(midname != ""){
			fullname += ' ' + midname;
		}
		
		return fullname;
	}
	
	public void setFullname(String surname, String name, String midname) {
		this.surname = surname;
		this.name = name;
		this.midname = midname;
	}
	
	public void setFullname(String surname, String name) {
		setFullname(surname, name, "");
	}
	
	public void addJobHunt(JobHunt jobHunt) {
		this.jobHunt.add(jobHunt);
		jobHunt.setEmployee(this);
	}
	
	public Set<JobHunt> getJobHunt() {
		return jobHunt;
	}
	
	public Set<JobStory> getJobStory() {
		return jobStory;
	}
	
	public void setJobStory(Set<JobStory> jobStory) {
		this.jobStory = jobStory;
	}
	
	public String toString() {
		return getFullname();
	}
}
