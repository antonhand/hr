package hr.web;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Calendar;
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
import hr.obj.JobHunt;
import hr.obj.JobStory;
import hr.obj.Vacancy;
import hr.obj.VacancyReq;

@Controller
@RequestMapping("/searchEmployee.htm")
public class SearchEmployeeController {

    protected final Log logger = LogFactory.getLog(getClass());
    private static hrDAO DAO;
    
    private SearchEmployeeController()
    {
    	DAO = new hrDAO();
    }
    
    @RequestMapping(method = RequestMethod.GET, params = "vacancyID")
	public String setupFormVacancy(@RequestParam("vacancyID") int vacancyID, Model model) {
    	Vacancy vc = DAO.vacancyById(vacancyID);
    	if(vc.getVacancyReqs().size() == 0){
    		vc.getVacancyReqs().add((new VacancyReq()));
    	}
		return processSearch(vc, null, model);
	}
    
    @RequestMapping(method = RequestMethod.GET)
	public  String setupForm(Model model) {
    	Map<String,String> edu = new HashMap<String,String>();
		for(Education e : DAO.getEducations()){
			edu.put(new Integer(e.getId()).toString(), e.getName());
		}
		model.addAttribute("educations", edu);
		Vacancy vac =  new Vacancy();
		vac.setVacancyReqs(new ArrayList<VacancyReq>());
		vac.getVacancyReqs().add(new VacancyReq());
		model.addAttribute("vac", vac);
		model.addAttribute("jhs", DAO.getJobHunts());
		return "searchEmployee";
	}
    
    @RequestMapping(method = RequestMethod.POST, params = "clear")
    public String processClear(@ModelAttribute("vac") Vacancy vac, BindingResult result, Model model) {
    	Map<String,String> edu = new HashMap<String,String>();
		for(Education e : DAO.getEducations()){
			edu.put(new Integer(e.getId()).toString(), e.getName());
		}
		model.addAttribute("educations", edu);
		vac =  new Vacancy();
		vac.setVacancyReqs(new ArrayList<VacancyReq>());
		vac.getVacancyReqs().add(new VacancyReq());
		model.addAttribute("vac", vac);
		model.addAttribute("jhs", DAO.getJobHunts());
		return "searchEmployee";
	}

	@RequestMapping(method = RequestMethod.POST, params = "add")
	public String processSubmit(@ModelAttribute("vac") Vacancy vac, BindingResult result, Model model) {
		 
		vac.getVacancyReqs().add(new VacancyReq());
		Map<String,String> edu = new HashMap<String,String>();
		for(Education e : DAO.getEducations()){
			edu.put(new Integer(e.getId()).toString(), e.getName());
		}
		model.addAttribute("educations", edu);
		model.addAttribute("vac", vac);
		model.addAttribute("jhs", DAO.getJobHunts());
		return "searchEmployee";
	}
	
	@RequestMapping(method = RequestMethod.POST, params = "search")
	public String processSearch(@ModelAttribute("vac") Vacancy vac, BindingResult result, Model model) {
		Collection<JobHunt> jhs = DAO.getJobHunts();
		Collection<JobHunt> findJH = new ArrayList<JobHunt>();
		Map<String,String> edu = new HashMap<String,String>();
		for(Education e : DAO.getEducations()){
			edu.put(new Integer(e.getId()).toString(), e.getName());
		}
		model.addAttribute("educations", edu);
		
		for(JobHunt jh : jhs){
			if(vac.getJob().getName().equals("") || vac.getJob().getName().equals(jh.getJob().getName())){
				if(vac.getEducation().getId() <= jh.getEmployee().getEducation().getId()){
					if(vac.getSalary() == null || jh.getSalaryMin() == null || jh.getSalaryMin() <= vac.getSalary()){
						boolean good = true;
						for(VacancyReq vr : vac.getVacancyReqs()){
							if(vr.getJob() == null || vr.getJob().getName().equals("")){
								continue;
							}
							int expMonth = 0;
							for(JobStory js : jh.getEmployee().getJobStory()){
								if(js.getJob().getName().equals(vr.getJob().getName())
										&& (vr.getCompany() == null || vr.getCompany().getName().equals("") 
												|| vr.getCompany().getName().equals(js.getCompany().getName()))){
									Calendar clend = Calendar.getInstance();
									if(js.getEndDate() != null){
										clend.setTime(js.getEndDate());
									}
									Calendar clstart = Calendar.getInstance();
									clstart.setTime(js.getStartDate());
									expMonth += (clend.get(Calendar.YEAR) - clstart.get(Calendar.YEAR)) * 12 + clend.get(Calendar.MONTH) - clstart.get(Calendar.MONTH);
								}
							}
							if(vr.getExpMonths() > expMonth){
								good = false;
								break;
							}
						}
						if(good){
							findJH.add(jh);
						}
					}
				}
			}
		}
		model.addAttribute("vac", vac);
		model.addAttribute("jhs", findJH);
		return "searchEmployee";
	}
}