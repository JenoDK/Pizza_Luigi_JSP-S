package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import be.vdab.dao.PizzaDAO;
import be.vdab.entities.Pizza;

/**
 * Servlet implementation class PizzaToevoegenServlet
 */
@MultipartConfig
@WebServlet("/pizzas/toevoegen.htm")
public class PizzaToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzatoevoegen.jsp";
	private static final String REDIRECT_URL = "%s/pizzas.htm";
	private final PizzaDAO pizzaDAO = new PizzaDAO();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		if (!Pizza.isNaamValid(naam)) {
			fouten.put("naam", "verplicht");
		}
		BigDecimal prijs = null;
		try {
			prijs = new BigDecimal(request.getParameter("prijs"));
			if (!Pizza.isPrijsValid(prijs)) {
				fouten.put("prijs", "tik een positief getal");
			}
		} catch (NumberFormatException ex) {
			fouten.put("prijs", "tik een getal");
		}
		Part fotoPart = request.getPart("foto");
		boolean fotoIsOpgeladen = fotoPart != null && fotoPart.getSize() != 0;
		if (fotoIsOpgeladen && !fotoPart.getContentType().contains("jpeg")) {
			fouten.put("foto", "geen JPEG foto");
		}
		if (fouten.isEmpty()) {
			boolean pikant = "pikant".equals(request.getParameter("pikant"));
			Pizza pizza = new Pizza(naam, prijs, pikant);
			pizzaDAO.create(pizza);
			if (fotoIsOpgeladen) {
				String pizzaFotosPad = this.getServletContext().getRealPath(
						"/pizzafotos");
				fotoPart.write(String.format("%s/%d.jpg", pizzaFotosPad,
						pizza.getId()));
			}
			response.sendRedirect(response.encodeRedirectURL(
					String.format(REDIRECT_URL, request.getContextPath())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
