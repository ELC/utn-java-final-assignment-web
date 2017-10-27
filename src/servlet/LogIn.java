package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.Application;
import logic.ControllerABMCPerson;
import entities.Person;

@WebServlet({ "/LogIn", "/login" })
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogIn() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String user=request.getParameter("user");
			String pass=request.getParameter("pass");
			
			Person per=new Person();
			per.setUsername(user);
			per.setPassword(pass);
			
			ControllerABMCPerson ctrl= new ControllerABMCPerson();
			
			ctrl.LoginPerson(per);
			Person pers=Application.getInstancia().getActivePerson();
			
			request.getSession().setAttribute("user", pers);
			
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);			
			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
