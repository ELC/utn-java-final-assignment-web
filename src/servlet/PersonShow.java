package servlet;

import java.io.IOException;
import java.util.List;

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


@WebServlet({ "/Person/Show" })
public class PersonShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerABMCPerson ctrlPerson = new ControllerABMCPerson();

    public PersonShow() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			if (user == null || !user.hasPermission(AccessLevel.READ_USER)) {
				throw new AccessDeniedException();
			}
			
			doPost(request, response);
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
		try{
			Person user = (Person)request.getSession().getAttribute("user");
			
			List<Person> pers = ctrlPerson.getAll(user);
			
			request.getSession().setAttribute("Persons", pers);
			
			request.getRequestDispatcher("/WEB-INF/PersonShow.jsp").forward(request, response);
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/PersonShow.jsp").forward(request, response);
		}
	}
}