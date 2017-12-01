package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Person;
import logic.ControllerABMCPerson;

@WebServlet({ "/Person/Select" })
public class PersonSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PersonSelect() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/PersonSelect.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ControllerABMCPerson ctrlPer= new ControllerABMCPerson();
	
			String dni=request.getParameter("Dni");
			
			request.getSession().setAttribute("person",ctrlPer.getByDni(dni));
				
			request.getRequestDispatcher("/WEB-INF/PersonInfo.jsp").forward(request, response);			
			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/PersonInfo.jsp").forward(request, response);
		}

	}
}
