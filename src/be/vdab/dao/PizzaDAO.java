package be.vdab.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import be.vdab.entities.Pizza;

public class PizzaDAO {
	private static final Map<Long, Pizza> pizzas = new ConcurrentHashMap<>();
	
	static {
		pizzas.put(12L, new Pizza(12, "Prosciutto", new BigDecimal(4), true));
		pizzas.put(14L, new Pizza(14, "Margehrita", new BigDecimal(5), false));
		pizzas.put(17L, new Pizza(17, "Calzone", new BigDecimal(4), false));
		pizzas.put(23L, new Pizza(23, "Fungi & Olive", new BigDecimal(5), false));
	}
	
	public Iterable<Pizza> findAll(){
		return new ArrayList<>(pizzas.values());
	}
	
	public Pizza read(long id){
		return pizzas.get(id);
	}
	
	public Iterable<Pizza> findByPrijsBetween(BigDecimal van, BigDecimal tot) {
		List<Pizza> pizzas = new ArrayList<>();
		for (Pizza pizza : PizzaDAO.pizzas.values()) {
			if (pizza.getPrijs().compareTo(van)>=0 && pizza.getPrijs().compareTo(tot)<=0) {
				pizzas.add(pizza);
			}
		}
		return pizzas;
	}
	public void create(Pizza pizza) { // pizza toevoegen
		pizza.setId(Collections.max(pizzas.keySet()) + 1);
		pizzas.put(pizza.getId(), pizza);
	}
}
