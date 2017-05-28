package hr.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

import hr.hibernate.*;
import hr.obj.Employee;

@Controller
@RequestMapping("/employees.htm")
public class EmployeeController {

    protected final Log logger = LogFactory.getLog(getClass());
    private static hrDAO DAO;
    
    private EmployeeController()
    {
    	DAO = new hrDAO();
    }
    
    @RequestMapping(method = RequestMethod.GET)
	public  String setupForm(Model model) {
		model.addAttribute("emp", new Employee());
		model.addAttribute("employee", DAO.getEmployees());
		return "employees";
	}

	@RequestMapping(value = "/employees.htm", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("emp") Employee emp, BindingResult result, Model model) {
		Collection<Employee> emps = DAO.getEmployees();
		Collection<Employee> findEmps = new ArrayList<Employee>();
		if(!emp.getSurname().equals("")){
			for(Employee e : emps){
				StringTokenizer st = new StringTokenizer(emp.getSurname().toLowerCase());
				while(st.hasMoreTokens()){
					if(e.getFullname().toLowerCase().contains(st.nextToken())){
						findEmps.add(e);
					}
				}
			}
			model.addAttribute("employee", findEmps);
		} else {
			model.addAttribute("employee", emps);
		}
		return "employees";
	}
}