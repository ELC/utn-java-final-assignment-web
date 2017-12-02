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
import entities.TypeBookable;
import logic.ControllerABMCPerson;
import logic.ControllerABMCTypeBookable;

@WebServlet({ "/TypeBookable/Delete" })
public class TypeBookableDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TypeBookableDelete() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/TypeBookableDelete.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ControllerABMCTypeBookable ctrlTypeBookable= new ControllerABMCTypeBookable();
			String name=request.getParameter("Name");
			TypeBookable t=new TypeBookable();
			t.setName(name);
			ctrlTypeBookable.DeleteTypeBookable(t, (Person)request.getSession().getAttribute("user"));
			
			Person user = (Person)request.getSession().getAttribute("user");
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.INFO, "Type Bookable " + t.getName() + " has been deleted by " + user.getDni());
			
			request.getRequestDispatcher("/TypeBookable/Show").forward(request, response);
			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/TypeBookable/Show").forward(request, response);
		}
	}
}


