package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.OrderDao;
import hu.unideb.inf.pizza.dao.interfaces.OrderDaoInterface;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.OrderServiceInterface;

import java.util.Collection;
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
     * Az osztály paraméter nélküli konstruktora.
     */
    public OrderService() {
        orderDao = new OrderDao();
    }

    @Override
    public void createOrder(User user, String address, String comment, int totalPrice, Collection<Pizza> pizzas) {
        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setComment(comment);
        order.setTotalPrice(totalPrice);
        order.setPizzas(pizzas);

        orderDao.create(order);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.findByUser(user);
    }
}
