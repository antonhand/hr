package hr.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hr.hibernate.hrDAO;
import hr.obj.Company;
import hr.obj.Education;
import hr.obj.Employee;
import hr.obj.Job;
import hr.obj.JobHunt;
import hr.obj.JobStory;
import hr.obj.Vacancy;
import hr.obj.VacancyReq;
import hr.valid.JSValidator;
import hr.valid.VacancyVallidator;

@Controller
@RequestMapping("/com.profile.htm")
public class ComProfileController {

    protected final Log logger = LogFactory.getLog(getClass());
    private static hrDAO DAO;
    
    private ComProfileController()
    {
    	DAO = new hrDAO();
    }
    
    @RequestMapping(method = RequestMethod.GET)
	public  String setupForm(@RequestParam("companyID") int companyID, Model model) {
		model.addAttribute("comp", DAO.companyById(companyID));
		model.addAttribute("company", new Company());
		model.addAttribute("add", false);
		return "com.profile";
	}
    
    @RequestMapping(method = RequestMethod.POST, params = "add")
   	public String processAdd(@ModelAttribute("company") Company company, BindingResult result, Model model) {
       	model.addAttribute("add", true);
       	Map<String,String> edu = new HashMap<String,String>();
   		for(Education e : DAO.getEducations()){
   			edu.put(new Integer(e.getId()).toString(), e.getName());
   		}
   		model.addAttribute("educations", edu);
   		
       	Vacancy vac =  new Vacancy();
		vac.setVacancyReqs(new ArrayList<VacancyReq>());
		vac.getVacancyReqs().add(new VacancyReq());
		model.addAttribute("vac", vac);
       	model.addAttribute("comp", DAO.companyById(company.getId()));
       	model.addAttribute("company", new Company());
   		return "com.profile";
   	}
       
       @RequestMapping(method = RequestMethod.POST, params = "cancel")
   	public String processCancel(@ModelAttribute("company") Company company, BindingResult result, Model model) {
       	model.addAttribute("add", false);
       	model.addAttribute("comp", DAO.companyById(company.getId()));
       	model.addAttribute("company", new Company());
   		return "com.profile";
   	}
       
    @RequestMapping(method = RequestMethod.POST, params = "addVR")
   	public String processSubmit(@ModelAttribute("vac") Vacancy vac, BindingResult result, Model model) {
   		 
   		vac.getVacancyReqs().add(new VacancyReq());
   		Map<String,String> edu = new HashMap<String,String>();
   		for(Education e : DAO.getEducations()){
   			edu.put(new Integer(e.getId()).toString(), e.getName());
   		}
   		model.addAttribute("comp", DAO.companyById(vac.getCompany().getId()));
   		model.addAttribute("add", true);
   		model.addAttribute("educations", edu);
       	model.addAttribute("company", new Company());
   		model.addAttribute("vac", vac);
   		return "com.profile";
   	}
    
    @RequestMapping(method = RequestMethod.POST, params = "save")
	public String processSaveJS(@ModelAttribute("vac") Vacancy vac, BindingResult result, Model model) {
    	new VacancyVallidator().validate(vac,result);
		if(result.hasErrors()){
			model.addAttribute("add", true);
	    	model.addAttribute("comp", DAO.companyById(vac.getCompany().getId()));
	    	model.addAttribute("company", new Company());
	    	Map<String,String> edu = new HashMap<String,String>();
	   		for(Education e : DAO.getEducations()){
	   			edu.put(new Integer(e.getId()).toString(), e.getName());
	   		}
	   		model.addAttribute("educations", edu);
			return "com.profile"; 
		}
    	Collection<Job> jobs = DAO.getJobs();
    	Job job = null;
    	for(Job j : jobs){
    		if(j.getName().toLowerCase().equals(vac.getJob().getName().toLowerCase())){
    			job = j;
    		}
    	}
    	
    	if(job == null){
    		DAO.create(vac.getJob());
    	} else {
    		vac.setJob(job);
    	}
    	
    	List<VacancyReq> tmp = vac.getVacancyReqs();
    	
    	vac.setVacancyReqs(new ArrayList<VacancyReq>());
    	
    	DAO.create(vac);
    	
    	for(VacancyReq vr : tmp){
    		if(vr.getJob().getName().equals("") && vr.getExpMonths() == null){
    			continue;
    		}
    		jobs = DAO.getJobs();
    		job = null;
        	for(Job j : jobs){
        		if(j.getName().toLowerCase().equals(vr.getJob().getName().toLowerCase())){
        			job = j;
        		}
        	}
        	
        	if(job == null){
        		DAO.create(vr.getJob());
        	} else {
        		vr.setJob(job);
        	}
        	vr.setVacancy(vac);
        	DAO.create(vr);
    	}
    	
    	
    	model.addAttribute("add", false);
    	model.addAttribute("comp", DAO.companyById(vac.getCompany().getId()));
    	model.addAttribute("company", new Company());
		return "com.profile";
	}

}
