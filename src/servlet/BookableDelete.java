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

import entities.Bookable;
import entities.Person;
import logic.ControllerABMCBookable;


@WebServlet({ "/Bookable/Delete" })
public class BookableDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BookableDelete() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/BookableDelete.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ControllerABMCBookable ctrlBookable= new ControllerABMCBookable();
			Bookable b = new Bookable();
			b.setName(request.getParameter("Name"));
			Person user = (Person)request.getSession().getAttribute("user");
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.INFO, "Bookalbe " + b.getId() + " has been deleted by " + user.getDni());
			ctrlBookable.DeleteBookable(b, (Person)request.getSession().getAttribute("user"));
			request.getRequestDispatcher("/Bookable/Show").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Bookable/Show").forward(request, response);
		}
	}
}
