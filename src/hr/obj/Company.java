package hr.obj;

import java.util.Set;

public class Company extends NamedEntity {
	
	private Set<Vacancy> vacancies;

	public Set<Vacancy> getVacancies() {
		return vacancies;
	}

	public void setVacancies(Set<Vacancy> vacancies) {
		this.vacancies = vacancies;
	}
}