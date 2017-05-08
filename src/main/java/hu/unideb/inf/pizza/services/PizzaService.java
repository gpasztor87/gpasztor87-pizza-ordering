package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.PizzaDao;
import hu.unideb.inf.pizza.dao.interfaces.PizzaDaoInterface;
import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.services.interfaces.PizzaServiceInterface;

import java.util.List;

/**
 * A PizzaService interfészt megvalósító osztály.
 */
public class PizzaService implements PizzaServiceInterface {

    /**
     * A {@link PizzaDaoInterface} interfész egy implementációjának példánya.
     */
    private PizzaDaoInterface pizzaDao;

    /**
     * Az osztály paraméter nélküli konstruktora.
     */
    public PizzaService() {
        pizzaDao = new PizzaDao();
    }

    @Override
    public List<Pizza> findAllPizza() {
        return pizzaDao.findAll();
    }
}
