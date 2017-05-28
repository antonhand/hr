package hr.web;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hr.hibernate.hrDAO;
import hr.obj.Company;
import hr.obj.Employee;
import hr.obj.Job;
import hr.obj.JobHunt;
import hr.obj.JobStory;
import hr.valid.JHValidator;
import hr.valid.JSValidator;

@Controller
@RequestMapping("/profile.htm")
public class ProfileController {

    protected final Log logger = LogFactory.getLog(getClass());
    private static hrDAO DAO;
    
    private ProfileController()
    {
    	DAO = new hrDAO();
    }
    
    @RequestMapping(method = RequestMethod.GET)
	public  String setupForm(@RequestParam("employeeID") int employeeID, Model model) {
		model.addAttribute("emp", DAO.employeeById(employeeID));
		model.addAttribute("addJH", false);
		model.addAttribute("addJS", false);
		model.addAttribute("employee", new Employee());
		return "profile";
	}
    
    @RequestMapping(method = RequestMethod.POST, params = "addJH")
	public String processAdd(@ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
    	model.addAttribute("addJH", true);
    	model.addAttribute("jh", new JobHunt());
    	model.addAttribute("emp", DAO.employeeById(employee.getId()));
    	model.addAttribute("employee", new Employee());
		return "profile";
	}
    
    @RequestMapping(method = RequestMethod.POST, params = "cancelJH")
	public String processCancel(@ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
    	model.addAttribute("addJH", false);
    	model.addAttribute("addJS", false);
    	model.addAttribute("emp", DAO.employeeById(employee.getId()));
    	model.addAttribute("employee", new Employee());
		return "profile";
	}
    
    @RequestMapping(method = RequestMethod.POST, params = "saveJH")
	public String processSave(@ModelAttribute("jh") JobHunt jh, BindingResult result, Model model) {
    	model.addAttribute("employee", new Employee());
    	new JHValidator().validate(jh,result);
		if(result.hasErrors()){
			model.addAttribute("addJH", true);
	    	model.addAttribute("addJS", false);
	    	model.addAttribute("emp", DAO.employeeById(jh.getEmployee().getId()));
			return "profile"; 
		}
    	Collection<Job> jobs = DAO.getJobs();
    	Job job = null;
    	for(Job j : jobs){
    		if(j.getName().toLowerCase().equals(jh.getJob().getName().toLowerCase())){
    			job = j;
    		}
    	}
    	
    	if(job == null){
    		DAO.create(jh.getJob());
    	} else {
    		jh.setJob(job);
    	}
    	
    	DAO.create(jh);
    	
    	model.addAttribute("addJH", false);
    	model.addAttribute("addJS", false);
    	model.addAttribute("emp", DAO.employeeById(jh.getEmployee().getId()));
    	model.addAttribute("employee", new Employee());
		return "profile";
	}
	
    @RequestMapping(method = RequestMethod.POST, params = "addJS")
	public String processAddJS(@ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
    	model.addAttribute("addJS", true);
    	Map<String,String> com = new HashMap<String,String>();
		for(Company c : DAO.getCompanies()){ 
			com.put(new Integer(c.getId()).toString(), c.getName());
		}
		model.addAttribute("companies", com);
    	model.addAttribute("js", new JobStory());
    	model.addAttribute("emp", DAO.employeeById(employee.getId()));
    	model.addAttribute("employee", new Employee());
		return "profile";
	}
    
    @RequestMapping(method = RequestMethod.POST, params = "cancelJS")
	public String processCancelJS(@ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
    	model.addAttribute("addJS", false);
    	model.addAttribute("addJH", false);
    	model.addAttribute("emp", DAO.employeeById(employee.getId()));
    	model.addAttribute("employee", new Employee());
		return "profile";
	}
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
    
    @RequestMapping(method = RequestMethod.POST, params = "saveJS")
	public String processSaveJS(@ModelAttribute("js") JobStory js, BindingResult result, Model model) {
    	model.addAttribute("employee", new Employee());
    	new JSValidator().validate(js,result);
		if(result.hasErrors()){
			model.addAttribute("addJH", false);
	    	model.addAttribute("addJS", true);
	    	model.addAttribute("emp", DAO.employeeById(js.getEmployee().getId()));
	    	Map<String,String> com = new HashMap<String,String>();
			for(Company c : DAO.getCompanies()){ 
				com.put(new Integer(c.getId()).toString(), c.getName());
			}
			model.addAttribute("companies", com);
			return "profile"; 
		}
    	Collection<Job> jobs = DAO.getJobs();
    	Job job = null;
    	for(Job j : jobs){
    		if(j.getName().toLowerCase().equals(js.getJob().getName().toLowerCase())){
    			job = j;
    		}
    	}
    	
    	if(job == null){
    		DAO.create(js.getJob());
    	} else {
    		js.setJob(job);
    	}
    	
    	DAO.create(js);
    	
    	model.addAttribute("addJH", false);
    	model.addAttribute("addJS", false);
    	model.addAttribute("emp", DAO.employeeById(js.getEmployee().getId()));
		return "profile";
	}

}
