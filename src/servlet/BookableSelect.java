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
import logic.ControllerABMCPerson;
import util.exceptions.AccessDeniedException;
import util.exceptions.ElementNotFoundException;

@WebServlet({ "/Bookable/Select" })
public class BookableSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerABMCBookable ctrlBook= new ControllerABMCBookable();

    public BookableSelect() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			if (user == null || !user.hasPermission(AccessLevel.READ_BOOKABLE)) {
				throw new AccessDeniedException();
			}
			
			request.getRequestDispatcher("/WEB-INF/BookableSelect.jsp").forward(request, response);
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		}  catch (Exception e) {
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.ERROR, e.getMessage());
			request.getRequestDispatcher("/Bookable/Show").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			Bookable b = new Bookable();
			b.setId(Integer.parseInt(request.getParameter("Id")));
			b = ctrlBook.getById(b, user);
			if (b == null){
				throw new ElementNotFoundException("Bookable doesn't exist",Level.ERROR);			
			}
			request.getSession().setAttribute("bookable", b);		
			request.getRequestDispatcher("/WEB-INF/BookableInfo.jsp").forward(request, response);			
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (ElementNotFoundException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/PersonInfo.jsp").forward(request, response);
		}
		  catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/PersonInfo.jsp").forward(request, response);
		}

	}
}
