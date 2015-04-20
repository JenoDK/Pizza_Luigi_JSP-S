package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class VoorkeurPizzasServlet
 */
@WebServlet("/pizzas/voorkeuren.htm")
public class VoorkeurPizzasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/voorkeurpizzas.jsp";
	private final transient PizzaDAO pizzaDAO = new PizzaDAO();

	@Resource(name = PizzaDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		pizzaDAO.setDataSource(dataSource);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pizzas", pizzaDAO.findAll());
		if (request.getParameterValues("id") != null){
			List<Pizza> voorkeurPizzas = new ArrayList<>();
			for (String id : request.getParameterValues("id")) {
				voorkeurPizzas.add(pizzaDAO.read(Long.parseLong(id)));
			}
			request.setAttribute("voorkeurPizzas", voorkeurPizzas);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
