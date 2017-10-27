package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Person;
import entities.TypeBookable;
import logic.ControllerABMCPerson;
import logic.ControllerABMCTypeBookable;

@WebServlet({ "/Delete/TypeBookable" })
public class DeleteTypeBookable extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteTypeBookable() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/DeleteTypeBookable.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			ControllerABMCTypeBookable ctrlTypeBookable= new ControllerABMCTypeBookable();
			String name=request.getParameter("Name");
			TypeBookable t=new TypeBookable();
			t.setName(name);
			ctrlTypeBookable.DeleteTypeBookable(t);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}


