package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Person;
import entities.Reservation;
import logic.ControllerABMCReservation;

@WebServlet({ "/Booking/Show" })
public class BookingShow extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private ControllerABMCReservation ctrlRes= new ControllerABMCReservation();

    public BookingShow() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			List<Reservation> bookings = ctrlRes.getAllByUser((Person)request.getSession().getAttribute("user"));
			
			request.getSession().setAttribute("Bookings", bookings);
			
			request.getRequestDispatcher("/WEB-INF/BookingShow.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/BookingShow.jsp").forward(request, response);
		}
	}
}