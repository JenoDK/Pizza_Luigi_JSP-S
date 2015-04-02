package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Pizza;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/pizzas.htm")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzas.jsp";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	public void init() throws ServletException {
		this.getServletContext().setAttribute("pizzasRequests", new AtomicInteger());
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Long, Pizza> pizzas = new LinkedHashMap<>();
		pizzas.put(12L, new Pizza(12, "Prosciutto", new BigDecimal(4), true));
		pizzas.put(14L, new Pizza(14, "Margehrita", new BigDecimal(5),false));
		pizzas.put(17L, new Pizza(17, "Calzone", new BigDecimal(4), false));
		pizzas.put(23L, new Pizza(23, "Fungi & Olive", new BigDecimal(5),false));
		request.setAttribute("pizzas", pizzas);
		((AtomicInteger) this.getServletContext().getAttribute("pizzasRequests"))
		.incrementAndGet();
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
