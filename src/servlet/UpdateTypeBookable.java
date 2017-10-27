package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.TypeBookable;

import logic.ControllerABMCTypeBookable;

@WebServlet({ "/Update/TypeBookable" })
public class UpdateTypeBookable extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateTypeBookable() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/UpdateTypeBookable.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name=request.getParameter("Name");
			String days=request.getParameter("DaysLimit");
			String hours=request.getParameter("HoursLimit");
			
			TypeBookable t=new TypeBookable();
			t.setName(name);
			t.setDayslimit(Integer.parseInt(days));
			t.setHourslimit(hours);
			
			
			ControllerABMCTypeBookable ctrlTypeBookable= new ControllerABMCTypeBookable();
			
			ctrlTypeBookable.ModifyTypeBookable(t);
			
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);			
			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}


}