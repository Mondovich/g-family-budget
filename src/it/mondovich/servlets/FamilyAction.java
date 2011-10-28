package it.mondovich.servlets;

import it.mondovich.EMFactory;
import it.mondovich.data.entities.Person;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.appengine.repackaged.com.google.common.base.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class FamilyAction extends ActionSupport {

	private String crea;
	private String firstname;
	private String lastname;
	private List<Person> listOfPersons;

	@Override
	public String execute() throws Exception {
		EntityManager em = EMFactory.get().createEntityManager();

		if (!StringUtil.isEmptyOrWhitespace(crea)) {
			Person person = new Person();
			person.setFirstName(firstname);
			person.setLastName(lastname);

			em.persist(person);
		}

		listOfPersons = em.createQuery("select p from Person p").getResultList();

//		em.close();

		return SUCCESS;
	}

	public List<Person> getListOfPersons() {
		return listOfPersons;
	}

	public void setListOfPersons(List<Person> listOfPersons) {
		this.listOfPersons = listOfPersons;
	}

	public String getCrea() {
		return crea;
	}

	public void setCrea(String crea) {
		this.crea = crea;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
