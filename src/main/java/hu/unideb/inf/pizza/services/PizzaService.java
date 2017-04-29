package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.PizzaDao;
import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.services.interfaces.PizzaServiceInterface;

import java.util.List;

/**
 *
 */
public class PizzaService implements PizzaServiceInterface {

    private static PizzaDao pizzaDao;

    public PizzaService() {
        pizzaDao = new PizzaDao();
    }

    public List<Pizza> getAllPizza() {
        return pizzaDao.findAll();
    }

}
