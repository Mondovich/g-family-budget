package it.mondovich.servlets;

import it.mondovich.EMFactory;
import it.mondovich.data.entities.Person;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.com.google.common.base.StringUtil;

public class FamilyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		
//		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		EntityManager em = EMFactory.get().createEntityManager();
		
		String crea = req.getParameter("crea");
		if (!StringUtil.isEmptyOrWhitespace(crea)) {
			
			
			Person person = new Person();
			person.setFirstName(req.getParameter("firstname"));
			person.setLastName(req.getParameter("lastname"));
			
			em.persist(person);
//			datastore.put(person);
		}
		
		List<Person> list = em.createQuery("select p from Person p").getResultList();
		req.getSession().setAttribute("persons", list);
		em.close();
		
		resp.sendRedirect("family.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

