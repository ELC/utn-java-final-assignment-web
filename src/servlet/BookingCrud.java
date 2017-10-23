package servlet;

import java.io.IOException;
import java.sql.Timestamp;

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
	        
	        if (action == null){
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
		request.getRequestDispatcher("WEB-INF/BookingCrud.jsp").forward(request, response);
	}
}

