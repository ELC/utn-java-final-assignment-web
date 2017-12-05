package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entities.AccessLevel;
import entities.Person;
import entities.TypeBookable;

import logic.ControllerABMCTypeBookable;
import util.exceptions.AccessDeniedException;
import util.exceptions.AppDataException;

@WebServlet({ "/TypeBookable/Add" })
public class TypeBookableAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerABMCTypeBookable ctrlTypeBookable= new ControllerABMCTypeBookable();

    public TypeBookableAdd() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			if (user == null || !user.hasPermission(AccessLevel.CREATE_TYPEBOOKABLE)) {
				throw new AccessDeniedException();
			}
			
			request.getRequestDispatcher("/WEB-INF/TypeBookableAdd.jsp").forward(request, response);
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (Exception e) {
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.ERROR, e.getMessage());
			request.getRequestDispatcher("/Person/Show").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Person user = (Person)request.getSession().getAttribute("user");
			
			String name=request.getParameter("Name");
			String days=request.getParameter("DaysLimit");
			
			int restriction;
			if (request.getParameter("Option") != null) {
				restriction=1;
			} else {
				restriction =0;
			}
			
			float hours = Float.parseFloat(request.getParameter("HoursLimit"));
			int minutes = (int)(hours * 60);
			int h = minutes / 60;
			int m = minutes % 60;
			String hoursLimit = h+":"+m;
			int maxBookings= Integer.parseInt(request.getParameter("maxBookings"));
			
			TypeBookable t=new TypeBookable();
			t.setName(name);
			t.setDayslimit(Integer.parseInt(days));
			t.setHourslimit(hoursLimit);
			t.setRestriction(restriction);
			t.setMaxBookings(maxBookings);
			
			ctrlTypeBookable.RegisterTypeBookable(t, user);
			
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.INFO, "Type Bookable " + t.getName() + " has been added by " + user.getDni());
			
			request.getSession().setAttribute("messageSuccess", "TypeBookable successfully created");
			request.getRequestDispatcher("/TypeBookable/Show").forward(request, response);			
			
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (AppDataException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/TypeBookable/Show").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/TypeBookable/Show").forward(request, response);
		}
	}


}

