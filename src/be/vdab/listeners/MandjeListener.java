package be.vdab.listeners;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class MandjeListener
 *
 */
@WebListener
public class MandjeListener implements ServletContextListener,
		HttpSessionAttributeListener {
	private static final String MANDJE = "mandje";
	private static final String AANTAL_MANDJES = "aantalMandjes";

	public MandjeListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if (MANDJE.equals(event.getName())) {
			((AtomicInteger) event.getSession().getServletContext()
					.getAttribute(AANTAL_MANDJES)).getAndIncrement();
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if (MANDJE.equals(event.getName())) {
			((AtomicInteger) event.getSession().getServletContext()
					.getAttribute(AANTAL_MANDJES)).getAndDecrement();
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		event.getServletContext().setAttribute(AANTAL_MANDJES,
				new AtomicInteger());
	}

}
