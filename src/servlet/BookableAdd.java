package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.AccessLevel;
import entities.Bookable;
import entities.Person;
import entities.TypeBookable;
import logic.ControllerABMCBookable;
import logic.ControllerABMCTypeBookable;
import util.exceptions.AccessDeniedException;
import util.exceptions.AppDataException;

@WebServlet({ "/Bookable/Add" })
public class BookableAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerABMCTypeBookable ctrlTypeBookable= new ControllerABMCTypeBookable();
	private ControllerABMCBookable ctrlBook= new ControllerABMCBookable();
	
    public BookableAdd() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			if (user == null || !user.hasPermission(AccessLevel.CREATE_BOOKABLE)) {
				throw new AccessDeniedException();
			}
			
			request.setAttribute("ListTypeBookables", ctrlTypeBookable.getAll(user));
			request.getRequestDispatcher("/WEB-INF/BookableAdd.jsp").forward(request, response);
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Bookable bookable= new Bookable();
			bookable.setName(request.getParameter("NameBookable"));
			
			TypeBookable typeBookable = new TypeBookable();
			typeBookable.setId(Integer.parseInt(request.getParameter("selectedType")));
			bookable.setType(typeBookable);
			
			ctrlBook.RegisterBookable(bookable, (Person)request.getSession().getAttribute("user"));
			
			request.getSession().setAttribute("messageSuccess", "Bookable successfully created");
			request.getRequestDispatcher("/Bookable/Show").forward(request, response);			
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (AppDataException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Bookable/Show").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Bookable/Show").forward(request, response);
		}
	}


}