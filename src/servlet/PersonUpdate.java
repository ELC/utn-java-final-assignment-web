package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entities.AccessLevel;
import entities.Person;
import logic.ControllerABMCPerson;
import util.exceptions.AccessDeniedException;

@WebServlet({ "/Person/Update" })
public class PersonUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerABMCPerson ctrlPer= new ControllerABMCPerson();

    public PersonUpdate() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			if (user == null || !user.hasPermission(AccessLevel.MODIFY_USER)) {
				throw new AccessDeniedException();
			}
			
			request.getRequestDispatcher("/WEB-INF/PersonUpdate.jsp").forward(request, response);
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (Exception e) {
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.ERROR, e.getMessage());
			request.getRequestDispatcher("/Person/Show").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			Person per=new Person();
			per.setName(request.getParameter("Name_Person"));
			per.setDni(request.getParameter("Dni"));
			per.setLastName(request.getParameter("Last_name_Person"));
			per.setPassword(request.getParameter("Password"));
			per.setUsername(request.getParameter("User_Person"));
			per.setEmail(request.getParameter("Email"));
			ctrlPer.ModifyPerson(per, user);
			
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.INFO, "Person " + per.getDni() + " has been updated by " + user.getDni());
	
			request.getRequestDispatcher("/index.jsp").forward(request, response);			
			
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Person/Show").forward(request, response);
		}
	}
}