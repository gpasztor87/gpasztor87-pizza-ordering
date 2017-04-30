package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.OrderDao;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.OrderServiceInterface;

import java.util.List;

/**
 * Az OrderService interfészt megvalósító osztály.
 */
public class OrderService implements OrderServiceInterface {

    /**
     * DAO objektum az adatbázis kezeléséhez.
     */
    private OrderDao orderDao = new OrderDao();

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.findByUser(user);
    }
}
