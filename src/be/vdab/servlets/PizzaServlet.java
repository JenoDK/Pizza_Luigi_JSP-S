package be.vdab.servlets;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.PizzaDAO;
import be.vdab.entities.Pizza;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/pizzas.htm")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzas.jsp";
	private final transient PizzaDAO pizzaDAO = new PizzaDAO();

	@Resource(name = PizzaDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		pizzaDAO.setDataSource(dataSource);
	}
	
	@Override
	public void init() throws ServletException {
		this.getServletContext().setAttribute("pizzasRequests",
				new AtomicInteger());
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		((AtomicInteger) this.getServletContext()
				.getAttribute("pizzasRequests")).incrementAndGet();
		Iterable<Pizza> pizzas = pizzaDAO.findAll();
		String pizzaFotosPad = this.getServletContext().getRealPath(
				"/pizzafotos");
		Set<Long> pizzaIdsMetFoto = new HashSet<>();
		for (Pizza pizza : pizzas) {
			File file = new File(String.format("%s/%d.jpg", pizzaFotosPad,
					pizza.getId()));
			if (file.exists()) { // is er foto voor deze pizza ?
				pizzaIdsMetFoto.add(pizza.getId());
			}
		}
		request.setAttribute("pizzas", pizzas);
		request.setAttribute("pizzaIdsMetFoto", pizzaIdsMetFoto);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
