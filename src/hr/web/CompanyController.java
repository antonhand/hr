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
import hr.obj.Company;

@Controller
@RequestMapping("/companies.htm")
public class CompanyController {

    protected final Log logger = LogFactory.getLog(getClass());
    private static hrDAO DAO;
    
    private CompanyController()
    {
    	DAO = new hrDAO();
    }
    
    @RequestMapping(method = RequestMethod.GET)
	public  String setupForm(Model model) {
		model.addAttribute("comp", new Company());
		model.addAttribute("company", DAO.getCompanies());
		return "companies";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("comp") Company comp, BindingResult result, Model model) {
		Collection<Company> comps = DAO.getCompanies();
		Collection<Company> findComps = new ArrayList<Company>();
		if(!comp.getName().equals("")){
			for(Company c : comps){
				StringTokenizer st = new StringTokenizer(comp.getName().toLowerCase());
				while(st.hasMoreTokens()){
					if(c.getName().toLowerCase().contains(st.nextToken())){
						findComps.add(c);
					}
				}
			}
			model.addAttribute("company", findComps);
		} else {
			model.addAttribute("company", comps);
		}
		return "companies";
	}
}