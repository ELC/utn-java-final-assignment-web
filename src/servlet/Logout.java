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

@WebServlet({ "/LogOut", "/logout" })
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Logout() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getSession().invalidate();
			Person user = (Person)request.getSession().getAttribute("user");
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.INFO, "User " + user.getDni() + " has logged out");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
