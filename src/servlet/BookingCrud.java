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
	private ControllerABMCReservation ctrlRes= new ControllerABMCReservation();
	private ControllerABMCBookable ctrlBook = new ControllerABMCBookable();
	private Person activeUser;

    public BookingCrud() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "/Booking/Show";
		try{
			String action = request.getParameter("action");	        
	        Reservation booking = new Reservation();
	        String tbs = request.getParameter("tb");
	        
	        if (tbs != null){
		        
		        String date = request.getParameter("date");
		        String time = request.getParameter("time");
		        
		        // validar que ninguno de los dos sea nulo
		        
		        LocalDateTime dt = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
		        Timestamp timestamp = Timestamp.valueOf(dt);
		        booking.setDate(timestamp);
		        Person p = new Person();
		        p.setId(1);
		        booking.setPerson(p);
		        request.getSession().setAttribute("Booking", booking);
	        	Integer tb = Integer.parseInt(tbs);
	        	TypeBookable typeBookable = new TypeBookable();
				typeBookable.setId(tb);
				
				ControllerABMCBookable ctrlBook= new ControllerABMCBookable();
				List<Bookable> l = ctrlBook.getAllAvailable(typeBookable, timestamp);
	        	request.getSession().setAttribute("ListBookables", l);
	        	
	        	forward = "/WEB-INF/BookingCrud2.jsp";
	        	request.getRequestDispatcher(forward).forward(request, response);
	        	return;
	        }
	        
	        if (action == null){
	        	ControllerABMCTypeBookable ctrlTypeBookable= new ControllerABMCTypeBookable();
				request.setAttribute("ListTypeBookables", ctrlTypeBookable.getAll());
	        	forward = "/WEB-INF/BookingCrud.jsp";
	        } else if (action.equalsIgnoreCase("delete")){
	        	booking.setId(Integer.parseInt(request.getParameter("bookingId")));
	        	ctrlRes.DeleteReservation(booking);   
	        } else if (action.equalsIgnoreCase("create")){
	        	activeUser = (Person)request.getSession().getAttribute("user");
	        	Bookable bookable = ctrlBook.getById(Integer.parseInt(request.getParameter("bookableId")));
	        	booking.setBookable(bookable);
	        	booking.setPerson(activeUser);
	        	booking.setDate(Timestamp.valueOf(request.getParameter("bookingDate")));
	        	booking.setDetail(request.getParameter("bookingDetail"));
	            ctrlRes.RegisterReservation(booking);
	        }
	        
	        request.getRequestDispatcher(forward).forward(request, response);
	        
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher(forward).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "/Booking/BookingCrud";
		try{
			ControllerABMCReservation ctrlBooking = new ControllerABMCReservation();
			Reservation booking = (Reservation) request.getSession().getAttribute("Booking");
			Bookable b = new Bookable();
			b.setId(Integer.parseInt(request.getParameter("selectedType")));
			booking.setBookable(b);
			booking.setDetail("");
			ctrlBooking.RegisterReservation(booking);
			request.getRequestDispatcher("/WEB-INF/BookingCrud.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher(forward).forward(request, response);
		}
	}
}

