package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Person;
import entities.TypeBookable;
import logic.ControllerABMCPerson;


@WebServlet({ "/Person/Show" })
public class PersonShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PersonShow() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			ControllerABMCPerson ctrlPerson = new ControllerABMCPerson();
			List<Person> pers = ctrlPerson.getAll();
			
			request.getSession().setAttribute("Persons", pers);
			
			request.getRequestDispatcher("/WEB-INF/PersonShow.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/PersonShow.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/PersonShow.jsp").forward(request, response);
	}
}