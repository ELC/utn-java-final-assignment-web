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

import logic.ControllerABMCBookable;
import logic.ControllerABMCReservation;
import logic.ControllerABMCTypeBookable;
import entities.*;

@WebServlet({ "/Booking/CRUD" })
public class BookingCrud extends HttpServlet {
	private static final long serialVersionUID = 3L;
	private ControllerABMCReservation ctrlBooking= new ControllerABMCReservation();
	private ControllerABMCBookable ctrlBook = new ControllerABMCBookable();

    public BookingCrud() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
	        Reservation booking = new Reservation();
	        String tbs = request.getParameter("tb");
	        
	        if (tbs != null){
		        
		        String date = request.getParameter("date");
		        String time = request.getParameter("time");
		        
		        // validar que ninguno de los dos sea nulo
		        
		        LocalDateTime dt = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
		        Timestamp timestamp = Timestamp.valueOf(dt);
		        booking.setDate(timestamp);
		        booking.setPerson((Person)request.getSession().getAttribute("user"));
		        request.getSession().setAttribute("Booking", booking);
				
				TypeBookable typeBookable = new TypeBookable();
				typeBookable.setId(Integer.parseInt(tbs));
				
	        	request.getSession().setAttribute("ListBookables", ctrlBook.getAllAvailable(typeBookable, timestamp));
	        	
	        	request.getRequestDispatcher("/WEB-INF/BookingCrud2.jsp").forward(request, response);
	        	return;
	        }
	        
	        ControllerABMCTypeBookable ctrlTypeBookable= new ControllerABMCTypeBookable();
			request.setAttribute("ListTypeBookables", ctrlTypeBookable.getAll());        	
	        request.getRequestDispatcher("/WEB-INF/BookingCrud.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Booking/Show").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Reservation booking = (Reservation) request.getSession().getAttribute("Booking");
			Bookable b = new Bookable();
			b.setId(Integer.parseInt(request.getParameter("selectedType")));
			booking.setBookable(b);
			booking.setDetail("");
			ctrlBooking.RegisterReservation(booking, (Person)request.getSession().getAttribute("user"));
			request.getRequestDispatcher("/Booking/Show").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/Booking/Show").forward(request, response);
		}
	}
}

