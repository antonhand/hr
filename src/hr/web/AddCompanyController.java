package hr.web;

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
import hr.obj.Company;
import hr.valid.CompanyValidator;

@Controller
@RequestMapping("/addCompany.htm")
public class AddCompanyController {

    protected final Log logger = LogFactory.getLog(getClass());
    private static hrDAO DAO;
    
    private AddCompanyController()
    {
    	DAO = new hrDAO();
    }
    
    @RequestMapping(method = RequestMethod.GET, params = "companyID")
   	public String setupForm(@RequestParam("companyID") int companyID, Model model) {
   		
   		model.addAttribute("add", false);
   		model.addAttribute("comp", DAO.companyById(companyID));
		return "addCompany";
   	}
    
    @RequestMapping(method = RequestMethod.GET)
	public  String setupForm(Model model) {
    	model.addAttribute("add", true);
		model.addAttribute("comp", new Company());
		return "addCompany";
	}

	@RequestMapping(method = RequestMethod.POST, params = "add")
	public String processAdd(@ModelAttribute("comp") Company comp, BindingResult result, Model model) {
		new CompanyValidator().validate(comp,result);
		if(result.hasErrors()){
			model.addAttribute("add", true);
			return "addCompany"; 
		}
		DAO.create(comp);
		return "redirect:com.profile.htm?companyID=" + comp.getId();
	}
	
	@RequestMapping(method = RequestMethod.POST, params = "save")
	public String processSave(@ModelAttribute("comp") Company comp, BindingResult result, Model model) {
		new CompanyValidator().validate(comp,result);
		if(result.hasErrors()){
			return "addCompany";
		}
		DAO.update(comp);
		return "redirect:com.profile.htm?companyID=" + comp.getId();
	}
}