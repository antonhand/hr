package hr.web;


import java.util.HashMap;
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
import hr.obj.Employee;
import hr.valid.CompanyValidator;
import hr.valid.EmployeeValidator;

@Controller
@RequestMapping("/addEmployee.htm")
public class AddEmployeeController {

    protected final Log logger = LogFactory.getLog(getClass());
    private static hrDAO DAO;
    
    private AddEmployeeController()
    {
    	DAO = new hrDAO();
    }
    
    @RequestMapping(method = RequestMethod.GET, params = "employeeID")
	public String setupForm(@RequestParam("employeeID") int employeeID, Model model) {
		model.addAttribute("emp", DAO.employeeById(employeeID));
		Map<String,String> edu = new HashMap<String,String>();
		for(Education e : DAO.getEducations()){
			edu.put(new Integer(e.getId()).toString(), e.getName());
		}
		model.addAttribute("educations", edu);
		model.addAttribute("add", false);
		return "addEmployee";
	}
    
    @RequestMapping(method = RequestMethod.GET)
	public  String setupForm(Model model) {
		model.addAttribute("emp", new Employee());
		Map<String,String> edu = new HashMap<String,String>();
		for(Education e : DAO.getEducations()){
			edu.put(new Integer(e.getId()).toString(), e.getName());
		}
		model.addAttribute("educations", edu);
		model.addAttribute("add", true);
		return "addEmployee";
	}

	@RequestMapping(method = RequestMethod.POST, params = "add")
	public String processAdd(@ModelAttribute("emp") Employee emp, BindingResult result, Model model) {
		model.addAttribute("add", true);
		new EmployeeValidator().validate(emp,result);
		if(result.hasErrors()){
			Map<String,String> edu = new HashMap<String,String>();
			for(Education e : DAO.getEducations()){
				edu.put(new Integer(e.getId()).toString(), e.getName());
			}
			model.addAttribute("educations", edu);
			model.addAttribute("emp", emp);
			return "addEmployee";
		}
		DAO.create(emp);
		return "redirect:profile.htm?employeeID=" + emp.getId();
	}
	
	@RequestMapping(method = RequestMethod.POST, params = "save")
	public String processSave(@ModelAttribute("emp") Employee emp, BindingResult result, Model model) {
		new EmployeeValidator().validate(emp,result);
		if(result.hasErrors()){
			Map<String,String> edu = new HashMap<String,String>();
			for(Education e : DAO.getEducations()){
				edu.put(new Integer(e.getId()).toString(), e.getName());
			}
			model.addAttribute("educations", edu);
			model.addAttribute("emp", emp);
			return "addEmployee";
		}
		DAO.update(emp);
		return "redirect:profile.htm?employeeID=" + emp.getId();
	}
}