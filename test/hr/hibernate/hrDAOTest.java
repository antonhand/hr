package hr.hibernate;

import java.util.Collection;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import hr.obj.*;

@ContextConfiguration
public class hrDAOTest extends AbstractTransactionalTestNGSpringContextTests {
	
	private hrDAO hr;
	
	@Test
	public void getCompanies() {
		Collection<Company> col = hr.getCompanies();
		Assert.assertEquals(col.size(), super.countRowsInTable("Company"));
		Company ent = null;
		for(Company e : col){
			if(e.getId() == 1){
				ent = e;
			}
		}
		Assert.assertNotNull(ent);
		Assert.assertEquals(ent.getName(), "ПАО Газпром");
	}
	
	@Test
	public void getEducations() {
		Collection<Education> col = hr.getEducations();
		Assert.assertEquals(col.size(), super.countRowsInTable("Education"));
		Education ent = null;
		for(Education e : col){
			if(e.getId() == 2){
				ent = e;
			}
		}
		Assert.assertNotNull(ent);
		Assert.assertEquals(ent.getName(), "Среднее полное");
	}
	
	@Test
	public void getEmployees() {
		Collection<Employee> col = hr.getEmployees();
		Assert.assertEquals(col.size(), 5);
		Employee ent = null;
		for(Employee e : col){
			if(e.getId() == 5){
				ent = e;
			}
		}
		Assert.assertNotNull(ent);
		Assert.assertEquals(ent.getFullname(), "Джугашвили Иосиф Виссарионович");
		Assert.assertEquals(ent.getAddress(), "Москва, Красная пл., дом 1");
		Assert.assertEquals(ent.getEducation().getName(), "Среднее специальное");
		Collection<JobStory> js = ent.getJobStory();
		Assert.assertEquals(js.size(), 2);
		for(JobStory jsi : js){
			if(jsi.getSalary() == 3000){
				Assert.assertEquals(jsi.getCompany().getName(),"Совнарком");
				Assert.assertEquals(jsi.getJob().getName(),"Народный комиссар");
				Assert.assertEquals(jsi.getStartDate().toString(), "1917-11-08");
				Assert.assertEquals(jsi.getEndDate().toString(), "1923-07-07");
			} else if(jsi.getSalary() == 5000){
				Assert.assertEquals(jsi.getCompany().getName(),"ЦК ВКП(б)");
				Assert.assertEquals(jsi.getJob().getName(),"Генеральный секретарь");
				Assert.assertEquals(jsi.getStartDate().toString(), "1922-04-03");
				Assert.assertEquals(jsi.getEndDate().toString(), "1952-10-15");
			} else {
				Assert.fail();
			}
		}
		Assert.assertEquals(ent.getJobHunt().size(), 1);
		JobHunt jh = ent.getJobHunt().iterator().next();
		Assert.assertEquals(jh.getJob().getName(), "Генеральный секретарь");
		Assert.assertNull(jh.getSalaryMin());
		Assert.assertNull(jh.getSalaryMax());
	}
	
	@Test
	public void getJobHunts() {
		Collection<JobHunt> col = hr.getJobHunts();
		Assert.assertEquals(col.size(), 5);
		JobHunt ent = null;
		for(JobHunt e : col){
			if(e.getEmployee().getFullname().equals("Тевтонов Данияр Томасович")){
				ent = e;
			}
		}
		Assert.assertNotNull(ent);
		Assert.assertEquals(ent.getJob().getName(), "Учитель информатики");
		
	}
	
	@Test
	public void getJobs() {
		Collection<Job> col = hr.getJobs();
		Assert.assertEquals(col.size(), 6);
		Job ent = null;
		for(Job e : col){
			if(e.getId() == 5){
				ent = e;
			}
		}
		Assert.assertNotNull(ent);
		Assert.assertEquals(ent.getName(), "Химик-стажёр");
	}
	
	@Test
	public void getVacancies() {
		Collection<Vacancy> col = hr.getVacancies();
		Assert.assertEquals(col.size(), 4);
		Vacancy ent = null;
		for(Vacancy e : col){
			if(e.getId() == 4){
				ent = e;
			}
		}
		Assert.assertNotNull(ent);
		Assert.assertEquals(ent.getCompany().getName(), "ЦК КПСС");
		Assert.assertEquals(ent.getJob().getName(), "Генеральный секретарь");
		Assert.assertEquals(ent.getEducation().getName(), "Среднее полное");
		Assert.assertFalse(ent.getVacancyReqs().isEmpty());
		Collection<VacancyReq> vr = ent.getVacancyReqs();
		Assert.assertEquals(vr.size(), 1);
		Assert.assertEquals(vr.iterator().next().getJob().getName(), "Народный комиссар");
		Assert.assertEquals(vr.iterator().next().getExpMonths(), 12);
	}
	
	@Test(dependsOnMethods={"getEducations"})
	public void create() {
		Education ed = new Education();
		ed.setName("Начальное");
		hr.create(ed);
		Collection<Education> col = hr.getEducations();
		boolean newEd = false;
		for(Education e : col){
			if(e.getName().equals("Начальное")){
				newEd = true;
				break;
			}
		}
		Assert.assertTrue(newEd);
	}
	
	@Test(dependsOnMethods={"getEducations"})
	public void update() {
		Collection<Education> col = hr.getEducations();
		Education ed =  col.iterator().next();
		String oldname = ed.getName();
		ed.setName(oldname + "_обновлённое");
		hr.update(ed);
		Collection<Education> col1 = hr.getEducations();
		ed = null;
		for(Education edu : col1){
			if(edu.getName().equals(oldname + "_обновлённое")){
				ed = edu;
				break;
			}
		}
		Assert.assertNotNull(ed);
		ed.setName(oldname);
		hr.update(ed);
	}
	
	
	@Test(dependsOnMethods={"create", "getEducations"})
	public void delete() {
		Collection<Education> col = hr.getEducations();
		Education ed = null;
		for(Education edu : col){
			if(edu.getName().equals("Начальное")){
				ed = edu;
				break;
			}
		}
		Assert.assertNotNull(ed);
		hr.delete(ed);
		Collection<Education> col1 = hr.getEducations();
		for(Education edu : col1){
			if(edu.getName().equals("Начальное")){
				Assert.fail();
			}
		}
	}
	
	public hrDAOTest()
	{
		hr = new hrDAO();
	}
}
