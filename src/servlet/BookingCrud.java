package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import logic.ControllerABMCBookable;
import logic.ControllerABMCReservation;
import logic.ControllerABMCTypeBookable;
import util.Emailer;
import util.Token;
import entities.*;

@WebServlet({ "/Booking/CRUD" })
public class BookingCrud extends HttpServlet {
	private static final long serialVersionUID = 3L;
	private ControllerABMCReservation ctrlBooking= new ControllerABMCReservation();
	private ControllerABMCBookable ctrlBook = new ControllerABMCBookable();
	private ControllerABMCTypeBookable ctrlTypeBookable= new ControllerABMCTypeBookable();

    public BookingCrud() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String token = (String)request.getParameter("token");
			if (token != null) {
				String storedToken = (String) request.getSession().getAttribute("token");
				if (token.equals(storedToken)) {
					request.getSession().removeAttribute("token");
					request.getSession().setAttribute("ValidToken", "1");
					doPost(request, response);
					return;
				} else {
					throw new Exception("Invalid Token");
				}
			}			
	        
			Person user = (Person)request.getSession().getAttribute("user");
			
	        String date = request.getParameter("date");
	        String time = request.getParameter("time");
	        String tbs = request.getParameter("tb");
	        
	        if ((date == null || time == null) && tbs == null){        	
		        request.getRequestDispatcher("/WEB-INF/BookingCrud.jsp").forward(request, response);
	        	return;
	        }
	        
	        if (tbs == null) {
	        	Reservation booking = new Reservation();
	        	booking.setPerson(user);
	        	LocalDateTime dt = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
		        Timestamp timestamp = Timestamp.valueOf(dt);
		        booking.setDate(timestamp);
		        request.getSession().setAttribute("booking", booking);
	        	request.setAttribute("ListTypeBookables", ctrlTypeBookable.getAllByDate(timestamp, user));        	
		        request.getRequestDispatcher("/WEB-INF/BookingCrud2.jsp").forward(request, response);
	        	return;
	        }
	        
	        Reservation booking = (Reservation)request.getSession().getAttribute("booking");
	        
			TypeBookable typeBookable = ctrlTypeBookable.getById(Integer.parseInt(tbs), user);
			
			List<Bookable> lb = ctrlBook.getAllAvailable(typeBookable, booking.getDate());
			
        	request.getSession().setAttribute("ListBookables", lb);
        	request.getRequestDispatcher("/WEB-INF/BookingCrud3.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Booking/Show").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Person user = (Person)request.getSession().getAttribute("user");
			
			String validToken = (String) request.getSession().getAttribute("ValidToken");
			
			if (validToken == null) {
				Reservation booking = (Reservation) request.getSession().getAttribute("booking");
				Bookable b = new Bookable();
				b.setId(Integer.parseInt(request.getParameter("selectedType")));
				booking.setBookable(b);
				booking.setDetail(request.getParameter("detail"));
				request.getSession().setAttribute("booking", booking);
				
				String token = Token.create();
				request.getSession().setAttribute("token", token);
				
				Emailer.getInstance().send(user.getEmail(), "Confirm your booking", request.getRequestURL() + "?token=" + token);
				request.getRequestDispatcher("/checkEmail.jsp").forward(request, response);
				return;
			}
			
			request.getSession().removeAttribute("ValidToken");
			
			Reservation booking = (Reservation)request.getSession().getAttribute("booking");
			
			ctrlBooking.RegisterReservation(booking, user);
			
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.INFO, "Booking ID: " + booking.getId() + " has been made by " + user.getDni());
			
			request.getSession().setAttribute("messageSuccess", "Booking successfully created");
			request.getRequestDispatcher("/Booking/Show").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Booking/Show").forward(request, response);
		}
	}
}

