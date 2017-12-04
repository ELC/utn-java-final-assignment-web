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

@WebServlet({ "/TypeBookable/Update" })
public class TypeBookableUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerABMCTypeBookable ctrlTypeBookable= new ControllerABMCTypeBookable();

    public TypeBookableUpdate() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			if (user == null || !user.hasPermission(AccessLevel.MODIFY_TYPEBOOKABLE)) {
				throw new AccessDeniedException();
			}
			
			request.getRequestDispatcher("/WEB-INF/TypeBookableUpdate.jsp").forward(request, response);
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		}  catch (Exception e) {
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.ERROR, e.getMessage());
			request.getRequestDispatcher("/Person/Show").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id= Integer.parseInt(request.getParameter("Id"));
			String name=request.getParameter("Name");
			String days=request.getParameter("DaysLimit");
			int restriction;
			
			if (request.getParameter("Option") != null) {
				restriction=1;
			} else {restriction=0;}
			
			float hours = Float.parseFloat(request.getParameter("HoursLimit"));
			int minutes = (int)(hours * 60);
			int h = minutes / 60;
			int m = minutes % 60;
			String hoursLimit = h+":"+m;			
			
			TypeBookable t=new TypeBookable();
			t.setId(id);
			t.setName(name);
			t.setDayslimit(Integer.parseInt(days));
			t.setHourslimit(hoursLimit);
			t.setRestriction(restriction);
			
			ctrlTypeBookable.ModifyTypeBookable(t, (Person)request.getSession().getAttribute("user"));
			
			Person user = (Person)request.getSession().getAttribute("user");
			Logger logger = LogManager.getLogger(getClass());
			logger.log(Level.INFO, "Type Bookable " + t.getName() + " has been updated by " + user.getDni());
			
			request.getSession().setAttribute("messageSuccess", "TypeBookable successfully updated");
			request.getRequestDispatcher("/TypeBookable/Show").forward(request, response);
			
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/TypeBookable/Show").forward(request, response);
		}
	}


}