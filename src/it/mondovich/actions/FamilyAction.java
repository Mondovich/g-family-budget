package it.mondovich.actions;

import it.mondovich.data.dao.PersonDAO;
import it.mondovich.data.dao.PersonDAOImpl;
import it.mondovich.data.entities.Person;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FamilyAction extends ActionSupport implements ModelDriven<Person> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Person person = new Person();
	private List<Person> listOfPerson;
	private PersonDAO personDAO = new PersonDAOImpl();
	
	@Override
	public Person getModel() {
		return person;
	}

	@Override
	public String execute() throws Exception {
		fillListOfPerson();

		return SUCCESS;
	}
	
	public String newPerson() throws Exception {
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
		
		personDAO.remove(Long.parseLong(id[0]));
		
		fillListOfPerson();
		
		return SUCCESS;
	}
	
	private void fillListOfPerson(){
		listOfPerson = personDAO.findAll();
	}

	public List<Person> getListOfPerson() {
		return listOfPerson;
	}

	public void setListOfPerson(List<Person> listOfPerson) {
		this.listOfPerson = listOfPerson;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
