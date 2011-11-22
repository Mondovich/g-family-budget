package it.mondovich.actions;

import it.mondovich.data.dao.AccountDAO;
import it.mondovich.data.dao.AccountDAOImpl;
import it.mondovich.data.dao.PersonDAO;
import it.mondovich.data.dao.PersonDAOImpl;
import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.Person;
import it.mondovich.util.ContextUtils;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FamilyAction extends ActionSupport implements ModelDriven<Person> {
	
	private static Log log = LogFactory.getLog(FamilyAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Person person = new Person();
	private List<Person> listOfPerson;
	private PersonDAO personDAO = new PersonDAOImpl();
	private AccountDAO accountDAO = new AccountDAOImpl();
	private User user = UserServiceFactory.getUserService().getCurrentUser();
	
	@Override
	public Person getModel() {
		return person;
	}

	@Override
	public String execute() throws Exception {
		fillListOfPerson();
		
		Account account = accountDAO.findByGmail(user.getEmail());
		if (account == null) {
			accountDAO.save(new Account(user.getEmail()));
			account = accountDAO.findByGmail(user.getEmail());
		}
		
		log.debug("Accound ID " + account.getGmail());
		
		return SUCCESS;
	}
	
	public String newPerson() throws Exception {
		Account account = accountDAO.findByGmail(user.getEmail());
		if (ContextUtils.getParameter("id") != null) {
			Key keyPerson = KeyFactory.createKey("Person", Long.parseLong(ContextUtils.getParameter("id")));
			person.setKey(keyPerson);
			personDAO.save(person);
		} else {
			person.setAccount(KeyFactory.createKey("Account", account.getGmail()));
			person = personDAO.save(person);
			account.getPersons().add(person.getKey());
			accountDAO.save(account);
		}
		
		fillListOfPerson();
		
		person = new Person();
		
		return SUCCESS;
	}
	public String editPerson() throws Exception {
		person = personDAO.findByKey(KeyFactory.createKey("Person", ContextUtils.getLongParameter("id")));
		
		fillListOfPerson();
		
		return SUCCESS;
	}
	public String deletePerson() throws Exception {
		Long id = null;
		try {
			id = ContextUtils.getLongParameter("id");
		} catch (Exception e) {}
		
		if (id == null) {
			fillListOfPerson(); 
			return ERROR;
		}
		
		Key keyPerson = KeyFactory.createKey("Person", id);
		personDAO.delete(keyPerson);
		
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
