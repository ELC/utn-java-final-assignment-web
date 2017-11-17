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
import logic.ControllerUserRoles;

@WebServlet({ "/Person/Add" })
public class PersonAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PersonAdd() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControllerUserRoles ctrlRoles= new ControllerUserRoles();
		try {
			request.getSession().setAttribute("ListUserRoles", ctrlRoles.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/PersonAdd.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ControllerABMCPerson ctrlPer= new ControllerABMCPerson();
			Person per=new Person();
			String option= request.getParameter("Option");
			
			if (option==null) {
				per.setEnabled(false);
			}
			else {
				per.setEnabled(true);
			}
			
			per.setName(request.getParameter("Name_Person"));
			per.setDni(request.getParameter("Dni"));
			per.setLastName(request.getParameter("Last_name_Person"));
			per.setPassword(request.getParameter("Password"));
			per.setUsername(request.getParameter("User_Person"));
			per.setEmail(request.getParameter("Email"));
			per.setPrivileges(Integer.parseInt(request.getParameter("ur")));
			ctrlPer.RegisterPerson(per, (Person)request.getSession().getAttribute("user"));
			
			
			
			Person user = (Person)request.getSession().getAttribute("user");
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.INFO, "Person " + per.getDni() + " has been added by " + user.getDni());
		
			request.getRequestDispatcher("/Person/Show").forward(request, response);		
				
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Person/Show").forward(request, response);
		}
	}
}

