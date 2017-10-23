package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.*;
import logic.ControllerABMCTypeBookable;

@WebServlet({ "/TypeBookable/Show" })
public class TypeBookableShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TypeBookableShow() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			ControllerABMCTypeBookable ctrlTypeBookable = new ControllerABMCTypeBookable();
			List<TypeBookable> tb = ctrlTypeBookable.getAll();
			
			request.getSession().setAttribute("TypeBookables", tb);
			
			request.getRequestDispatcher("/WEB-INF/TypeBookableShow.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/TypeBookableShow.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/TypeBookableShow.jsp").forward(request, response);
	}
}