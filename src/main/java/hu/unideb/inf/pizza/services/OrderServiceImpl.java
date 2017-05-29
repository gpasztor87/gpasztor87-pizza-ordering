package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.interfaces.OrderDao;
import hu.unideb.inf.pizza.managers.ConnectionManager;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.OptionalInt;

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
     * A logger egy példánya.
     */
    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

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
            logger.error(e.getMessage());
        } finally {
            connectionManager.close();
        }
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.findByUser(user);
    }

    @Override
    public int getUserHighestOrderPrice(User user) {
        List<Order> orders = this.getUserOrders(user);

        OptionalInt orderTotal = orders.stream().mapToInt(Order::getPaymentTotal).max();

        if (orderTotal.isPresent()) {
            return orderTotal.getAsInt();
        } else {
            return 0;
        }
    }

    @Override
    public int getUserTotalDiscount(User user) {
        List<Order> orders = this.getUserOrders(user);
        return orders.stream().mapToInt(Order::getDiscountTotal).sum();
    }
}
