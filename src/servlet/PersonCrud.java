package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Person;
import logic.Application;
import logic.ControllerABMCPerson;

@WebServlet({ "/Person/CRUD" })
public class PersonCrud extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PersonCrud() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/PersonCrud.jsp").forward(request, response);
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
			String id=request.getParameter("iDPerson");
			String action=request.getParameter("Action");
			
			// 1 para agregar 2 para borrar 3 para consultar 4 para modificar
			
			if (action.equals("1")) {
				Person per=new Person();
				//per.setId(Integer.parseInt(id));
				per.setName(name);
				per.setDni(dni);
				per.setLastName(lastName);
				per.setPassword(pass);
				per.setUsername(user);
				per.setEmail(email);
				ctrlPer.RegisterPerson(per);
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	
			request.getRequestDispatcher("/index.jsp").forward(request, response);			
			
		} catch (Exception e) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		//request.getRequestDispatcher("/WEB-INF/PersonCrud.jsp").forward(request, response);
		//request.getRequestDispatcher(destination).forward(request, response);
	}
}

