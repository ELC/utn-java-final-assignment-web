package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Person;
import logic.ControllerABMCPerson;

@WebServlet({ "/Person/Delete" })
public class PersonDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PersonDelete() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/PersonDelete.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ControllerABMCPerson ctrlPer= new ControllerABMCPerson();
				
			String dni=request.getParameter("Dni");
			ctrlPer.DeletePerson(ctrlPer.getByDni(dni), (Person)request.getSession().getAttribute("user"));
			
			request.getRequestDispatcher("/Person/Show").forward(request, response);			
			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Person/Show").forward(request, response);
		}
	}
}