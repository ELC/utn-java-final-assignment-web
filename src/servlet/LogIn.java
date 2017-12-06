package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ControllerABMCPerson;
import entities.Person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;


@WebServlet({ "/LogIn", "/login" })
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = LogManager.getLogger(getClass());

    public LogIn() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String user=request.getParameter("user");
			String pass=request.getParameter("pass");
			
			Person per=new Person();
			per.setUsername(user);
			per.setPassword(pass);
			
			ControllerABMCPerson ctrl= new ControllerABMCPerson();
			
			per = ctrl.LoginPerson(per);
			
			logger.log(Level.INFO, "User " + per.getDni() + " has logged in");

			request.getSession().setAttribute("user", per);
			request.getSession().setAttribute("messageInfo", "Wellcome back!!");
			request.getRequestDispatcher("/index.jsp").forward(request, response);			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
