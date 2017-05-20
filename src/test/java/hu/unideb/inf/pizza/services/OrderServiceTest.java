package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.interfaces.OrderDao;
import hu.unideb.inf.pizza.managers.ConnectionManager;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.OrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

public class OrderServiceTest {

    private OrderDao orderDao;

    private ConnectionManager connectionManager;

    private OrderService orderService;

    private User user;

    private List<Pizza> cart;

    private Order order;

    @Before
    public void setUp() throws Exception {
        orderDao = Mockito.mock(OrderDao.class);
        connectionManager = Mockito.mock(ConnectionManager.class);

        user = new User("Teszt Elek", "teszt@elek.org", "Debrecen", "");
        cart = Arrays.asList(
                new Pizza("Margarita", "", 1550, "margarita.jpg"),
                new Pizza("Szalámis", "", 1350, "szalamis.jpg")
        );

        order = new Order("", "", 2900, cart, user);

        Mockito.when(orderDao.findByUser(user)).thenReturn(Collections.singletonList(order));
        Mockito.when(orderDao.findById(1)).thenReturn(order);

        orderService = new OrderServiceImpl(connectionManager, orderDao);
    }

    @Test
    public void testMockCreation() throws Exception {
        Assert.assertNotNull(orderDao);
        Assert.assertNotNull(orderService);
    }

    @Test
    public void createOrder() throws Exception {
        orderService.createOrder(order);
        Mockito.verify(orderDao).create(order);
    }

    @Test
    public void getUserOrder() throws Exception {
        Assert.assertEquals(1, orderService.getUserOrders(user).size());
    }
}
