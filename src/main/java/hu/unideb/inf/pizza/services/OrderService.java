package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.OrderDao;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.OrderServiceInterface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Az OrderService interfészt megvalósító osztály.
 */
public class OrderService implements OrderServiceInterface {

    /**
     * DAO objektum az adatbázis kezeléséhez.
     */
    private OrderDao orderDao;

    public OrderService() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("production");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        orderDao = new OrderDao(entityManager);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.findByUser(user);
    }
}
