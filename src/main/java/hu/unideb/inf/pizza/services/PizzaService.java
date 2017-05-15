package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.PizzaDao;
import hu.unideb.inf.pizza.dao.interfaces.PizzaDaoInterface;
import hu.unideb.inf.pizza.managers.ConnectionManager;
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
     * A {@link ConnectionManager} interfész egy implementációjának példánya.
     */
    private ConnectionManager connectionManager;

    /**
     * Az osztály konstruktora inicializálja a pizzaDao objektumot.
     *
     * @param connectionManager A connectionManager
     */
    public PizzaService(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        pizzaDao = new PizzaDao(connectionManager.getEntityManager());
    }

    @Override
    public void createPizza(Pizza pizza) {
        try {
            connectionManager.beginTransaction();
            pizzaDao.create(pizza);
            connectionManager.commit();
        } catch (Exception e) {
            connectionManager.rollback();
        }
    }

    @Override
    public List<Pizza> getAllPizza() {
        return pizzaDao.findAll();
    }
}
