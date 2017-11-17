package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.TypeBookable;
import logic.ControllerABMCTypeBookable;

@WebServlet({ "/TypeBookable/Select" })
public class TypeBookableSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TypeBookableSelect() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/TypeBookableSelect.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ControllerABMCTypeBookable ctrlType= new ControllerABMCTypeBookable();
			TypeBookable type=new TypeBookable();
			type.setName(request.getParameter("name"));
			type = ctrlType.getByName(type);
			if (type == null){
				throw new Exception("TypeBookable doesn't exist");			
			}
			request.getSession().setAttribute("typeBookable", type);		
			request.getRequestDispatcher("/WEB-INF/TypeBookableInfo.jsp").forward(request, response);			
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/TypeBookableInfo.jsp").forward(request, response);
		}

	}
}
