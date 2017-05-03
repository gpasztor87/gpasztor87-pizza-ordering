package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.PizzaDao;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PizzaServiceTest {

    private PizzaDao pizzaDao;

    @Before
    public void setUp() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("testing");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        pizzaDao = new PizzaDao(entityManager);
    }

    @Test
    public void testGetPizzas() {

    }
}
