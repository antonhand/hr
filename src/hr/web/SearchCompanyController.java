package hr.web;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import hr.hibernate.*;
import hr.obj.Education;
import hr.obj.SearchCompany;
import hr.obj.Vacancy;

@Controller
@RequestMapping("/searchCompany.htm")
public class SearchCompanyController {

    protected final Log logger = LogFactory.getLog(getClass());
    private static hrDAO DAO;
    
    private SearchCompanyController()
    {
    	DAO = new hrDAO();
    }
    
    @RequestMapping(method = RequestMethod.GET, params = {"jhEmployeeID","jhJobID"})
   	public  String setupFormVacancy(@RequestParam("jhEmployeeID") int jhEmployeeID,
   										 @RequestParam("jhJobID") int jhJobID,  
   										 						  Model model) {
    	SearchCompany sc = new SearchCompany();
    	sc.setJobHunt(DAO.jobHuntByIds(jhEmployeeID, jhJobID));
   		return processSearch(sc, null, model);
   	}
    
    @RequestMapping(method = RequestMethod.GET)
	public  String setupForm(Model model) {
    	Map<String,String> edu = new HashMap<String,String>();
		for(Education e : DAO.getEducations()){
			edu.put(new Integer(e.getId()).toString(), e.getName());
		}
		model.addAttribute("educations", edu);
		model.addAttribute("companies", DAO.getCompanies());
		model.addAttribute("vacs", DAO.getVacancies());
		SearchCompany sc = new SearchCompany();
		model.addAttribute("sc", sc);
		return "searchCompany";
	}
    
    @RequestMapping(method = RequestMethod.POST, params = "clear")
    public String processClear(@ModelAttribute("vac") Vacancy vac, BindingResult result, Model model) {
    	Map<String,String> edu = new HashMap<String,String>();
		for(Education e : DAO.getEducations()){
			edu.put(new Integer(e.getId()).toString(), e.getName());
		}
		model.addAttribute("educations", edu);
		model.addAttribute("companies", DAO.getCompanies());
		model.addAttribute("vacs", DAO.getVacancies());
		SearchCompany sc = new SearchCompany();
		model.addAttribute("sc", sc);
		return "searchCompany";
	}
	
	@RequestMapping(method = RequestMethod.POST, params = "search")
	public String processSearch(@ModelAttribute("sc") SearchCompany sc, BindingResult result, Model model) {
		Collection<Vacancy> vacs = DAO.getVacancies();
		Collection<Vacancy> findVac = new ArrayList<Vacancy>();
		
		Map<String,String> edu = new HashMap<String,String>();
		for(Education e : DAO.getEducations()){
			edu.put(new Integer(e.getId()).toString(), e.getName());
		}
		model.addAttribute("educations", edu);
		
		for(Vacancy vac : vacs){
			if(sc.getJobHunt().getJob().getName().equals("") || sc.getJobHunt().getJob().getName().equals(vac.getJob().getName())){
				if(sc.getJobHunt().getEmployee().getEducation().getId() >= vac.getEducation().getId()){
					if(sc.getJobHunt().getSalaryMin() == null || vac.getSalary() == null || sc.getJobHunt().getSalaryMin() <= vac.getSalary()){
						if(sc.getCompaniesID() == null || sc.getCompaniesID().contains(vac.getCompany().getId())){
							findVac.add(vac);
						}
					}
				}
			}
		}
		model.addAttribute("companies", DAO.getCompanies());
		model.addAttribute("sc", sc);
		model.addAttribute("vacs", findVac);
		return "searchCompany";
	}
}