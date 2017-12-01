package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Person;
import entities.TypeBookable;

import logic.ControllerABMCTypeBookable;

@WebServlet({ "/TypeBookable/Update" })
public class TypeBookableUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TypeBookableUpdate() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/TypeBookableUpdate.jsp").forward(request, response);
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
			
			ctrlTypeBookable.ModifyTypeBookable(t, (Person)request.getSession().getAttribute("user"));
			
			request.getRequestDispatcher("/TypeBookable/Show").forward(request, response);
			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/TypeBookable/Show").forward(request, response);
		}
	}


}