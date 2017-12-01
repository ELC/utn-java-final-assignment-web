package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Person;
import logic.ControllerABMCPerson;

@WebServlet({ "/Person/Add" })
public class PersonAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PersonAdd() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/PersonAdd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ControllerABMCPerson ctrlPer= new ControllerABMCPerson();

			Person per=new Person();

			per.setName(request.getParameter("Name_Person"));
			per.setDni(request.getParameter("Dni"));
			per.setLastName(request.getParameter("Last_name_Person"));
			per.setPassword(request.getParameter("Password"));
			per.setUsername(request.getParameter("User_Person"));
			per.setEmail(request.getParameter("Email"));
			ctrlPer.RegisterPerson(per, (Person)request.getSession().getAttribute("user"));
		
			request.getRequestDispatcher("/Person/Show").forward(request, response);		
				
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Person/Show").forward(request, response);
		}
	}
}

