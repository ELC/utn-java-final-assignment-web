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
import util.exceptions.ElementNotFoundException;

@WebServlet({ "/TypeBookable/Select" })
public class TypeBookableSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerABMCTypeBookable ctrlType= new ControllerABMCTypeBookable();

    public TypeBookableSelect() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			if (user == null || !user.hasPermission(AccessLevel.READ_TYPEBOOKABLE)) {
				throw new AccessDeniedException();
			}
			
			request.getRequestDispatcher("/WEB-INF/TypeBookableSelect.jsp").forward(request, response);
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
			TypeBookable type=new TypeBookable();
			type.setName(request.getParameter("name"));
			type = ctrlType.getByName(type, user);
			if (type == null){
				throw new ElementNotFoundException("TypeBookable doesn't exist",Level.ERROR);			
			}
			request.getSession().setAttribute("typeBookable", type);		
			request.getRequestDispatcher("/WEB-INF/TypeBookableInfo.jsp").forward(request, response);			
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (ElementNotFoundException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/TypeBookableInfo.jsp").forward(request, response);
		}
		  catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/TypeBookableInfo.jsp").forward(request, response);
		}

	}
}
