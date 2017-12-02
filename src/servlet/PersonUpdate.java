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

import entities.Person;
import logic.ControllerABMCPerson;

@WebServlet({ "/Person/Update" })
public class PersonUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PersonUpdate() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/PersonUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ControllerABMCPerson ctrlPer= new ControllerABMCPerson();
			
			String user=request.getParameter("User_Person");
			String pass=request.getParameter("Password");
			String dni=request.getParameter("Dni");
			String name=request.getParameter("Name_Person");
			String lastName=request.getParameter("Last_name_Person");
			String email=request.getParameter("Email");
			
			
			Person per=new Person();
			per.setName(name);
			per.setDni(dni);
			per.setLastName(lastName);
			per.setPassword(pass);
			per.setUsername(user);
			per.setEmail(email);
			ctrlPer.ModifyPerson(per, (Person)request.getSession().getAttribute("user"));
			
			Person user2 = (Person)request.getSession().getAttribute("user");
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.INFO, "Person " + per.getDni() + " has been updated by " + user2.getDni());
	
			request.getRequestDispatcher("/index.jsp").forward(request, response);			
			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Person/Show").forward(request, response);
		}
	}
}