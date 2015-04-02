package be.vdab.servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import be.vdab.entities.Persoon;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(urlPatterns="/index.htm",name="indexservlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private final AtomicInteger aantalKeerBekeken = new AtomicInteger();
	private final Persoon zaakvoerder = new Persoon();
	@Override
	public void init() throws ServletException {		
		ServletContext context = this.getServletContext();
		context.setAttribute("indexRequests", new AtomicInteger());
		zaakvoerder.setVoornaam(context.getInitParameter("voornaam"));
		zaakvoerder.setFamilienaam(context.getInitParameter("familienaam"));
		zaakvoerder.setAantalKinderen(
		Integer.parseInt(context.getInitParameter("aantalKinderen")));
		zaakvoerder.setGehuwd(Boolean.parseBoolean(context.getInitParameter("gehuwd")));
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int uur = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		request.setAttribute("begroeting", uur >= 6 && uur < 12 ? "Goede morgen" :
		uur >= 12 && uur < 18 ? "Goede middag": "Goede avond");
		request.setAttribute("zaakvoerder", zaakvoerder);
		((AtomicInteger) this.getServletContext().getAttribute("indexRequests"))
		.incrementAndGet();
		request.setAttribute("aantalKeerBekeken", aantalKeerBekeken.incrementAndGet());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	

}
