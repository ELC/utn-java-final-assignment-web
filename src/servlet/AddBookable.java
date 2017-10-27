package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Bookable;
import entities.TypeBookable;
import logic.ControllerABMCBookable;
import logic.ControllerABMCTypeBookable;

@WebServlet({ "/Add/Bookable" })
public class AddBookable extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddBookable() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ControllerABMCTypeBookable ctrlTypeBookable= new ControllerABMCTypeBookable();
			request.setAttribute("ListTypeBookables", ctrlTypeBookable.getAll());
			request.getRequestDispatcher("/WEB-INF/AddBookable.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ControllerABMCBookable ctrlBook= new ControllerABMCBookable();
			Bookable bookable= new Bookable();
			
			bookable.setName(request.getParameter("NameBookable"));
			
			TypeBookable typeBookable = new TypeBookable();
			typeBookable.setId(Integer.parseInt(request.getParameter("selectedType")));
			bookable.setType(typeBookable);
			ctrlBook.RegisterBookable(bookable);

			request.getRequestDispatcher("/Bookable/Show").forward(request, response);			
			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}


}