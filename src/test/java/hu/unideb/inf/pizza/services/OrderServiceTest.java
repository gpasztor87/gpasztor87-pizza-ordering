package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.OrderDao;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OrderServiceTest {

    private OrderDao orderDao;

    @Before
    public void setUp() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("testing");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        orderDao = new OrderDao(entityManager);
    }

    @Test
    public void testGetUserOrders() {

    }

}
