package it.mondovich.actions;

import it.mondovich.data.dao.PersonDAO;
import it.mondovich.data.dao.PersonDAOImpl;
import it.mondovich.data.entities.Person;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.appengine.api.datastore.KeyFactory;
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
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		
		if (request.getParameter("id") != null) {
			person.setKey(KeyFactory.createKey("Person", Long.parseLong(request.getParameter("id"))));
		}
		personDAO.persist(person);
		
		fillListOfPerson();
		
		person = new Person();
		
		return SUCCESS;
	}
	public String editPerson() throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		
		person = personDAO.findById(Long.parseLong(request.getParameter("id")));
		
		fillListOfPerson();
		
		return SUCCESS;
	}
	public String deletePerson() throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);

		Long id = null;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (Exception e) {}
		
		if (id == null) {
			fillListOfPerson(); 
			return ERROR;
		}
		
		personDAO.remove(id);
		
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
