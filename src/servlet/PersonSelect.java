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

@WebServlet({ "/Person/Select" })
public class PersonSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerABMCPerson ctrlPer= new ControllerABMCPerson();

    public PersonSelect() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			if (user == null || !user.hasPermission(AccessLevel.READ_USER)) {
				throw new AccessDeniedException();
			}
			
			request.getRequestDispatcher("/WEB-INF/PersonSelect.jsp").forward(request, response);
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
			Person user = (Person)request.getSession().getAttribute("user");
			Person p = new Person();
			p.setDni(request.getParameter("Dni"));
			p = ctrlPer.getByDni(p, user);
			if (p == null){
				throw new Exception("User doesn't exist");			
			}
			request.getSession().setAttribute("person", p);		
			request.getRequestDispatcher("/WEB-INF/PersonInfo.jsp").forward(request, response);			
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/PersonInfo.jsp").forward(request, response);
		}

	}
}
