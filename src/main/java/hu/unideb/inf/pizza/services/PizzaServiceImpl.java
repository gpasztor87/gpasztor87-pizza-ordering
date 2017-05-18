package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.interfaces.PizzaDao;
import hu.unideb.inf.pizza.managers.ConnectionManager;
import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.services.interfaces.PizzaService;

import java.util.List;

/**
 * A PizzaService interfészt megvalósító osztály.
 */
public class PizzaServiceImpl implements PizzaService {

    /**
     * A {@link PizzaDao} interfész egy implementációjának példánya.
     */
    private PizzaDao pizzaDao;

    /**
     * A {@link ConnectionManager} interfész egy implementációjának példánya.
     */
    private ConnectionManager connectionManager;

    /**
     * Az osztály konstruktora inicializálja a pizzaDao objektumot.
     *
     * @param connectionManager A connectionManager interfész egy implementációjának példánya
     * @param pizzaDao A pizzaDao niterfész egy implementációjának példánya
     */
    public PizzaServiceImpl(ConnectionManager connectionManager, PizzaDao pizzaDao) {
        this.connectionManager = connectionManager;
        this.pizzaDao = pizzaDao;
    }

    @Override
    public List<Pizza> getAllPizza() {
        return pizzaDao.findAll();
    }
}
