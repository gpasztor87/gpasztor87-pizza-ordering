package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.interfaces.OrderDao;
import hu.unideb.inf.pizza.managers.ConnectionManager;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.OrderService;

import java.util.List;

/**
 * Az OrderService interfészt megvalósító osztály.
 */
public class OrderServiceImpl implements OrderService {

    /**
     * A {@link OrderDao} interfész egy implementációjának példánya.
     */
    private OrderDao orderDao;

    /**
     * A {@link ConnectionManager} interfész egy implementációjának példánya.
     */
    private ConnectionManager connectionManager;

    /**
     * Az osztály konstruktora inicializálja az orderDao objektumot.
     *
     * @param connectionManager A connectionManager interfész egy implementációjnak példánya
     * @param orderDao Az orderDao interfész egy implementációjának példánya
     */
    public OrderServiceImpl(ConnectionManager connectionManager, OrderDao orderDao) {
        this.connectionManager = connectionManager;
        this.orderDao = orderDao;
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
