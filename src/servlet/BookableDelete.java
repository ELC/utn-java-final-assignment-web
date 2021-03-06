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

import entities.AccessLevel;
import entities.Bookable;
import entities.Person;
import logic.ControllerABMCBookable;
import util.exceptions.AccessDeniedException;
import util.exceptions.AppDataException;


@WebServlet({ "/Bookable/Delete" })
public class BookableDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerABMCBookable ctrlBookable= new ControllerABMCBookable();

    public BookableDelete() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			if (user == null || !user.hasPermission(AccessLevel.DELETE_BOOKABLE)) {
				throw new AccessDeniedException();
			}
			
			request.getRequestDispatcher("/WEB-INF/BookableDelete.jsp").forward(request, response);
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (Exception e) {
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.ERROR, e.getMessage());
			request.getRequestDispatcher("/Person/Show").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Bookable b = new Bookable();
			b.setId(Integer.parseInt(request.getParameter("Id")));
			Person user = (Person)request.getSession().getAttribute("user");
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.INFO, "Bookable " + b.getId() + " has been deleted by " + user.getDni());
			ctrlBookable.DeleteBookable(b, (Person)request.getSession().getAttribute("user"));
			request.getSession().setAttribute("messageSuccess", "Bookable successfully deleted");
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
