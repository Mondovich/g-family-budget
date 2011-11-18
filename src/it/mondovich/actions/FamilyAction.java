package it.mondovich.actions;

import it.mondovich.data.dao.AccountDAO;
import it.mondovich.data.dao.AccountDAOImpl;
import it.mondovich.data.dao.PersonDAO;
import it.mondovich.data.dao.PersonDAOImpl;
import it.mondovich.data.entities.Account;
import it.mondovich.data.entities.Person;
import it.mondovich.util.ContextUtils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.opensymphony.xwork2.ActionContext;
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
			accountDAO.persist(new Account(user.getEmail()));
			account = accountDAO.findByGmail(user.getEmail());
		}
		
		log.debug("Accound ID " + account.getId());
		
		return SUCCESS;
	}
	
	public String newPerson() throws Exception {
		if (ContextUtils.getParameter("id") != null) {
			Key parentKey = KeyFactory.createKey("Account", accountDAO.findByGmail(UserServiceFactory.getUserService().getCurrentUser().getEmail()).getId());
			person.setKey(KeyFactory.createKey(parentKey, "Person", Long.parseLong(ContextUtils.getParameter("id"))));
			personDAO.persist(person);
		} else {
			accountDAO.addPerson(accountDAO.findByGmail(user.getEmail()), person);
		}
		
		fillListOfPerson();
		
		person = new Person();
		
		return SUCCESS;
	}
	public String editPerson() throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		
		Key parentKey = KeyFactory.createKey("Account", accountDAO.findByGmail(UserServiceFactory.getUserService().getCurrentUser().getEmail()).getId());
		person = personDAO.findByKey(KeyFactory.createKey(parentKey, "Person", Long.parseLong(request.getParameter("id"))));
		
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
		
		personDAO.remove(KeyFactory.createKey("Person", id));
		
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
