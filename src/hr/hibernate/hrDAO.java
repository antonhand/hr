package hr.hibernate;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import hr.obj.*;

@SuppressWarnings("unchecked")
public class hrDAO {
	
	public Collection<Employee> getEmployees()
	{
		Session s = new	 Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction(); 
		Collection<Employee> col = s.createQuery("from Employee").list();
		s.close();
		return col;
	}
	
	public Collection<Company> getCompanies()
	{
		Session s = new	 Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction(); 
		Collection<Company> col = s.createQuery("from Company").list();
		s.close();
		return col;
	}
	
	public Collection<Education> getEducations()
	{
		Session s = new	 Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction(); 
		Collection<Education> col = s.createQuery("from Education").list();
		s.close();
		return col;
	}
	
	public Collection<Job> getJobs()
	{
		Session s = new	 Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction(); 
		Collection<Job> col = s.createQuery("from Job").list();
		s.close();
		return col;
	}
	
	public Collection<JobHunt> getJobHunts()
	{
		Session s = new	 Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction(); 
		Collection<JobHunt> col = s.createQuery("from JobHunt").list();
		s.close();
		return col;
	}
	
	public Collection<Vacancy> getVacancies()
	{
		Session s = new	 Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction(); 
		Collection<Vacancy> col = s.createQuery("from Vacancy").list();
		s.close();
		return col;
	}
	
	public Employee employeeById(int id){
		Session s = new	 Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction(); 
		Employee col = (Employee) s.createQuery("from Employee where ID=" + id).uniqueResult();
		s.close();
		return col;
	}
	
	public Company companyById(int id){
		Session s = new	 Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction(); 
		Company col = (Company) s.createQuery("from Company where ID=" + id).uniqueResult();
		s.close();
		return col;
	}
	
	public Vacancy vacancyById(int id){
		Session s = new	 Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction(); 
		Vacancy col = (Vacancy) s.createQuery("from Vacancy where ID=" + id).uniqueResult();
		s.close();
		return col;
	}
	
	public JobHunt jobHuntByIds(int employeeID, int jobID){
		Session s = new	 Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction(); 
		JobHunt col = (JobHunt) s.createQuery("from JobHunt where EmployeeID=" + employeeID + " AND JobID=" + jobID).uniqueResult();
		s.close();
		return col;
	}
	
	public <Entity> void update(Entity entity)
	{
		Session s = new	 Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.update(entity);
		s.getTransaction().commit();
	}
	
	public <Entity> void create(Entity entity)
	{
		Session s = new	 Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(entity);
		s.getTransaction().commit();
	}
	
	public <Entity> void delete(Entity entity)
	{
		Session s = new	 Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.delete(entity);
		s.getTransaction().commit();
	}
}
