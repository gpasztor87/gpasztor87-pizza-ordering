package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.PizzaDao;
import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.services.interfaces.PizzaServiceInterface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * A PizzaService interfészt megvalósító osztály.
 */
public class PizzaService implements PizzaServiceInterface {

    /**
     * DAO objektum az adatbázis kezeléséhez.
     */
    private PizzaDao pizzaDao;

    public PizzaService() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("production");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        pizzaDao = new PizzaDao(entityManager);
    }

    @Override
    public List<Pizza> getAllPizza() {
        return pizzaDao.findAll();
    }
}
