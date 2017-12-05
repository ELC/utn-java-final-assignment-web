package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entities.*;
import logic.ControllerABMCTypeBookable;
import util.exceptions.AccessDeniedException;

@WebServlet({ "/TypeBookable/Show" })
public class TypeBookableShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerABMCTypeBookable ctrlTypeBookable = new ControllerABMCTypeBookable();

    public TypeBookableShow() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Person user = (Person)request.getSession().getAttribute("user");
			
			if (user == null || !user.hasPermission(AccessLevel.READ_TYPEBOOKABLE)) {
				throw new AccessDeniedException();
			}
			doPost(request, response);
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
		try{
			Person user = (Person)request.getSession().getAttribute("user");
			
			List<TypeBookable> tb = ctrlTypeBookable.getAllShowByUser(user);
			
			request.getSession().setAttribute("TypeBookables", tb);
			request.getRequestDispatcher("/WEB-INF/TypeBookableShow.jsp").forward(request, response);
		} catch (AccessDeniedException e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/TypeBookableShow.jsp").forward(request, response);
		}
	}
}