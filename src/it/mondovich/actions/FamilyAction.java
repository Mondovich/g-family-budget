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
import com.opensymphony.xwork2.Preparable;

public class FamilyAction extends ActionSupport implements ModelDriven<Person>, Preparable {
	
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
	private Account account = accountDAO.findByGmail(user.getEmail());
	
	@Override
	public Person getModel() {
		return person;
	}

	@Override
	public String execute() throws Exception {
		prepare();
		
		log.debug("Account ID " + account.getGmail());
		
		return SUCCESS;
	}
	
	public String newPerson() throws Exception {
		person.setAccount(KeyFactory.createKey("Account", account.getGmail()));
		if (ContextUtils.getParameter("id") != null) {
			Key keyPerson = KeyFactory.createKey("Person", Long.parseLong(ContextUtils.getParameter("id")));
			person.setKey(keyPerson);
			personDAO.save(person);
		} else {
			person = personDAO.save(person);
			account.getPersons().add(person.getKey());
			accountDAO.save(account);
		}
		
		prepare();
		
		person = new Person();
		
		return SUCCESS;
	}
	public String editPerson() throws Exception {
		Long id = ContextUtils.getLongParameter("id");
		if (id != null) {
			person = personDAO.findByKey(KeyFactory.createKey("Person", id));
		}
		
		prepare();
		
		return SUCCESS;
	}
	public String deletePerson() throws Exception {
		Long id = null;
		try {
			id = ContextUtils.getLongParameter("id");
		} catch (Exception e) {}
		
		if (id == null) {
			prepare(); 
			return ERROR;
		}
		
		Key keyPerson = KeyFactory.createKey("Person", id);
		personDAO.delete(keyPerson);
		
		prepare();
		
		return SUCCESS;
	}
	
	@Override
	public void prepare() throws Exception {
		if (account == null) {
			accountDAO.save(new Account(user.getEmail()));
			account = accountDAO.findByGmail(user.getEmail());
		}
		listOfPerson = personDAO.findAllByAccount(account);
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
