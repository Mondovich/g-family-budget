package it.mondovich.actions;

import it.mondovich.EMFactory;
import it.mondovich.data.entities.Person;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FamilyAction extends ActionSupport {

	private String firstname;
	private String lastname;
	private List<Person> listOfPerson;

	@Override
	public String execute() throws Exception {
		EntityManager em = EMFactory.get().createEntityManager();

		em.close();
		
		fillListOfPerson();

		return SUCCESS;
	}
	
	public String newPerson() throws Exception {
		EntityManager em = EMFactory.get().createEntityManager();

		Person person = new Person();
		person.setFirstName(firstname);
		person.setLastName(lastname);

		em.persist(person);
		
		em.close();
		
		fillListOfPerson();
		
		return SUCCESS;
	}
	public String deletePerson() throws Exception {
		EntityManager em = EMFactory.get().createEntityManager();
		
		String[] id = (String[]) ActionContext.getContext().getParameters().get("id");

		if (id == null) {
			em.close();
			fillListOfPerson(); 
			return ERROR;
		}
		
		Person person = em.find(Person.class, KeyFactory.createKey("Person", Long.parseLong(id[0])));

		if (person != null) em.remove(person);
		
		em.close();
		
		fillListOfPerson();
		
		return SUCCESS;
	}
	
	private void fillListOfPerson(){
		EntityManager em = EMFactory.get().createEntityManager();
		listOfPerson = new ArrayList<Person>();
		for (Person person : (List<Person>)em.createQuery("select p from Person p").getResultList()) {
			listOfPerson.add(person);
		}
		em.close();
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
