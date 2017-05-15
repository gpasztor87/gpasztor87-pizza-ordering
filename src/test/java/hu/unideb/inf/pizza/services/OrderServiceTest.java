package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.managers.ConnectionManager;
import hu.unideb.inf.pizza.managers.JpaConnectionManager;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.OrderServiceInterface;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceTest {

    private ConnectionManager connectionManager;

    private OrderServiceInterface orderService;

    @Before
    public void init() {
        connectionManager = new JpaConnectionManager("test");
        orderService = new OrderService(connectionManager);
    }

    @After
    public void destroy() {
        connectionManager.rollback();
        connectionManager.close();
    }

    @Test
    public void createOrder() {
        // Given
        User user = this.createTestUser();
        List<Pizza> cart = new ArrayList<>();
        cart.add(this.createTestPizza());

        Order order = this.createTestOrder(user, cart);

        // When
        orderService.createOrder(order);

        // Then
        Assert.assertEquals(order.getUser(), user);
        Assert.assertEquals(order.getPizzas(), cart);
    }

    @Test
    public void UserCannotCreateEmptyOrder() {
        // Given
        User user = this.createTestUser();
        Order order = this.createTestOrder(user, null);

        // When
        orderService.createOrder(order);

        // Then

    }

    @Test
    public void getUserOrder() {
        // Given
        User user = this.createTestUser();


        // When
        List<Order> expectedOrder = orderService.getUserOrders(user);

        // Then
    }

    private Order createTestOrder(User user, List<Pizza> cart) {
        Order order = new Order();
        order.setUser(user);
        order.setPizzas(cart);
        order.setTotalPrice(2500);
        order.setComment("comment");
        order.setAddress("Budapest");

        return order;
    }

    private Pizza createTestPizza() {
        Pizza pizza = new Pizza();
        pizza.setName("Margarita");
        pizza.setPrice(1350);

        return pizza;
    }

    private User createTestUser() {
        User user = new User();
        user.setName("Gipsz Jakab");
        user.setEmail("gipsz.jakab@example.org");
        user.setAddress("Budapest");

        return user;
    }

}
