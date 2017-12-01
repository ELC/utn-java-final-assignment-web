package servlet;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entities.Person;
import entities.Reservation;
import logic.ControllerABMCReservation;
import util.Emailer;
import util.Token;

@WebServlet({ "/Booking/Delete" })
public class BookingDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerABMCReservation ctrlRes = new ControllerABMCReservation();

    public BookingDelete() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			String token = (String)request.getParameter("token");
			if (token != null) {
				String storedToken = (String) request.getSession().getAttribute("token");
				if (storedToken.equals(token)) {
					request.getSession().setAttribute("ValidToken", "1");
					doPost(request, response);
					return;
				} else {
					throw new Exception("Invalid Token");
				}
			}
			request.setAttribute("ListBookings", ctrlRes.getAllByUser(user));
			request.getRequestDispatcher("/WEB-INF/BookingDelete.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Booking/Show").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			String validToken = (String) request.getSession().getAttribute("ValidToken");
			
			if (validToken == null) {
				
				String token = Token.create();
				
				request.getSession().setAttribute("token", token);
				
				int id = Integer.parseInt(request.getParameter("id"));
				Reservation re = new Reservation();
				re.setId(id);
				
				request.getSession().setAttribute("re", re);
				
				Emailer.getInstance().send(user.getEmail(), "Delete your booking", request.getRequestURL() + "?token=" + token);
				request.getRequestDispatcher("/checkEmail.jsp").forward(request, response);
				return;
			}
			
			Reservation re = (Reservation)request.getSession().getAttribute("re");
			
			ctrlRes.DeleteReservation(re, user);
			
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.INFO, "Booking " + re.getId() + " has been deleted by " + user.getDni());
			
			request.getSession().setAttribute("messageSuccess", "Booking successfully deleted");
			
			request.getRequestDispatcher("/Booking/Show").forward(request, response);			
			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Booking/Show").forward(request, response);
		}
	}
}