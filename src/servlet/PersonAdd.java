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
import entities.UserRole;
import logic.ControllerABMCPerson;
import logic.ControllerUserRoles;
import util.exceptions.AccessDeniedException;

@WebServlet({ "/Person/Add" })
public class PersonAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerUserRoles ctrlRoles= new ControllerUserRoles();
	private ControllerABMCPerson ctrlPer= new ControllerABMCPerson();

    public PersonAdd() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			if (user == null || !user.hasPermission(AccessLevel.CREATE_USER)) {
				throw new AccessDeniedException();
			}
			
			request.getSession().setAttribute("ListUserRoles", ctrlRoles.getAll());
			request.getRequestDispatcher("/WEB-INF/PersonAdd.jsp").forward(request, response);
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		}  catch (Exception e) {
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.ERROR, e.getMessage());
			request.getRequestDispatcher("/Person/Show").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person per=new Person();
			
			per.setEnabled(request.getParameter("Option") != null);
			per.setName(request.getParameter("Name_Person"));
			per.setDni(request.getParameter("Dni"));
			per.setLastName(request.getParameter("Last_name_Person"));
			per.setPassword(request.getParameter("Password"));
			per.setUsername(request.getParameter("User_Person"));
			per.setEmail(request.getParameter("Email"));
			UserRole ur = new UserRole();
			ur.setId(Integer.parseInt(request.getParameter("ur")));
			per.setUserRoles(ur);
			ctrlPer.RegisterPerson(per, (Person)request.getSession().getAttribute("user"));
			
			
			Person user = (Person)request.getSession().getAttribute("user");
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.INFO, "Person " + per.getDni() + " has been added by " + user.getDni());
		
			request.getRequestDispatcher("/Person/Show").forward(request, response);		
				
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Person/Show").forward(request, response);
		} 
	}
}

