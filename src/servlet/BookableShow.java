package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Bookable;
import logic.ControllerABMCBookable;

@WebServlet({ "/Bookable/Show" })
public class BookableShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BookableShow() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			ControllerABMCBookable ctrlBookable = new ControllerABMCBookable();
			
			request.getSession().setAttribute("Bookables", ctrlBookable.GetAll());
			
			request.getRequestDispatcher("/WEB-INF/BookableShow.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/BookableShow.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/BookableShow.jsp").forward(request, response);
	}
}