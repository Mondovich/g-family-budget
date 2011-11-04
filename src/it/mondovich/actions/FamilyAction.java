package it.mondovich.actions;

import it.mondovich.data.dao.PersonDAO;
import it.mondovich.data.dao.PersonDAOImpl;
import it.mondovich.data.entities.Person;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.appengine.api.datastore.KeyFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FamilyAction extends ActionSupport {

	private String firstname;
	private String lastname;
	private List<Person> listOfPerson;
	private PersonDAO personDAO = new PersonDAOImpl();

	@Override
	public String execute() throws Exception {
		fillListOfPerson();

		return SUCCESS;
	}
	
	public String newPerson() throws Exception {
		Person person = new Person();
		person.setFirstName(firstname);
		person.setLastName(lastname);

		personDAO.persist(person);
		
		fillListOfPerson();
		
		return SUCCESS;
	}
	public String deletePerson() throws Exception {
		String[] id = (String[]) ActionContext.getContext().getParameters().get("id");

		if (id == null || !StringUtils.isNumeric(id[0])) {
			fillListOfPerson(); 
			return ERROR;
		}
		
		Person person = personDAO.findById(KeyFactory.createKey("Person", Long.parseLong(id[0])));

		if (person != null) personDAO.remove(person);
		
		fillListOfPerson();
		
		return SUCCESS;
	}
	
	private void fillListOfPerson(){
		listOfPerson = new ArrayList<Person>();
		for (Person person : personDAO.findAll()) {
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
