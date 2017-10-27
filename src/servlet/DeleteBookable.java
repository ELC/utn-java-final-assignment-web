package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Bookable;
import logic.ControllerABMCBookable;


@WebServlet({ "/Delete/Bookable" })
public class DeleteBookable extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteBookable() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/DeleteBookable.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			ControllerABMCBookable ctrlBookable= new ControllerABMCBookable();
			String name=request.getParameter("Name");
			Bookable b=new Bookable();
			b.setName(name);
			ctrlBookable.DeleteBookable(b);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}
