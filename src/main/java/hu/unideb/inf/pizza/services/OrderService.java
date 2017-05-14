package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.OrderDao;
import hu.unideb.inf.pizza.dao.interfaces.OrderDaoInterface;
import hu.unideb.inf.pizza.managers.ConnectionManager;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.OrderServiceInterface;

import java.util.List;

/**
 * Az OrderService interfészt megvalósító osztály.
 */
public class OrderService implements OrderServiceInterface {

    /**
     * A {@link OrderDaoInterface} interfész egy implementációjának példánya.
     */
    private OrderDaoInterface orderDao;

    /**
     * A {@link ConnectionManager} interfész egy implementációjának példánya.
     */
    private ConnectionManager connectionManager;

    /**
     * Az osztály konstruktora inicializálja az orderDao objektumot.
     *
     * @param connectionManager A connectionManager
     */
    public OrderService(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        orderDao = new OrderDao(connectionManager.getEntityManager());
    }

    @Override
    public void createOrder(Order order) {
        try {
            connectionManager.beginTransaction();
            orderDao.create(order);
            connectionManager.commit();
        } catch (Exception e) {
            connectionManager.rollback();
        }
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.findByUser(user);
    }
}
