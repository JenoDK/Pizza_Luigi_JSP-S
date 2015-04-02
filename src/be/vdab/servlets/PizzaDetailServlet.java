package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.PizzaDAO;
import be.vdab.entities.Pizza;

/**
 * Servlet implementation class PizzaDetailServlet
 */
@WebServlet("/pizzas/detail.htm")
public class PizzaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzadetail.jsp";
	private final PizzaDAO pizzaDAO = new PizzaDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Pizza pizza = pizzaDAO.read(Long.parseLong(request.getParameter("id")));
			if (pizza == null) {
				request.setAttribute("fout", "Pizza niet gevonden");
			} else {
				request.setAttribute("pizza", pizza);
			}			
		} catch (NumberFormatException ex) {
			request.setAttribute("fout", "Nummer niet correct");
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}