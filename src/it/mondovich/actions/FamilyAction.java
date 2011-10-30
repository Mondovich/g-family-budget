package it.mondovich.actions;

import it.mondovich.EMFactory;
import it.mondovich.data.entities.Person;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FamilyAction extends ActionSupport {

	private String firstname;
	private String lastname;
	private List<Person> listOfPerson;

	@Override
	public String execute() throws Exception {
		EntityManager em = EMFactory.get().createEntityManager();

		fillListOfPerson(em);

		em.close();

		return SUCCESS;
	}
	
	public String newPerson() throws Exception {
		EntityManager em = EMFactory.get().createEntityManager();

		Person person = new Person();
		person.setFirstName(firstname);
		person.setLastName(lastname);

		em.persist(person);
		
		fillListOfPerson(em);
		
		em.close();
		
		return SUCCESS;
	}
	public String deletePerson() throws Exception {
		EntityManager em = EMFactory.get().createEntityManager();
		
		String[] key = (String[]) ActionContext.getContext().getParameters().get("id");

		if (key == null) {
			fillListOfPerson(em);
			em.close();
			return ERROR;
		}
		
		Person person = em.find(Person.class, key[0]);

		if (person != null) em.remove(person);
		
		fillListOfPerson(em);
		
		em.close();
		
		return SUCCESS;
	}
	
	private void fillListOfPerson(EntityManager em){
		listOfPerson = new ArrayList<Person>();
		for (Person person : (List<Person>)em.createQuery("select p from Person p").getResultList()) {
			listOfPerson.add(person);
		}
	}

	public List<Person> getListOfPerson() {
		return listOfPerson;
	}

	public void setListOfPerson(List<Person> listOfPerson) {
		this.listOfPerson = listOfPerson;
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
