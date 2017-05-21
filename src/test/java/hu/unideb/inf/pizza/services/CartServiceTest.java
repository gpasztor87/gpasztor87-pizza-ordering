package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.managers.DiscountManager;
import hu.unideb.inf.pizza.models.Discount;
import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.services.interfaces.CartService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CartServiceTest {

    @Mock
    private List<Pizza> cart = new ArrayList<Pizza>();

    private CartService cartService;

    @Mock
    private DiscountManager discountManager;

    private Pizza testPizza1;

    private Pizza testPizza2;

    @Before
    public void setUp() throws Exception {
        discountManager = DiscountManager.getInstance();

        testPizza1 = new Pizza("Margarita", "", 1500, "margarita.jpg");
        testPizza2 = new Pizza("Szalámis", "", 1500, "szalamis.jpg");

        cart.add(testPizza1);
        cart.add(testPizza2);

        cartService = new CartServiceImpl(cart);
    }

    @After
    public void tearDown() throws Exception {
        discountManager.clear();
    }

    @Test
    public void add() throws Exception {
        cartService.add(new Pizza("Szalámis", "", 1350, "szalamis.jpg"));
        Assert.assertEquals(3, cart.size());
    }

    @Test
    public void remove() throws Exception {
        cartService.remove(testPizza1);
        Assert.assertEquals(1, cart.size());
    }

    @Test
    public void clear() throws Exception {
        cartService.clear();
        Assert.assertEquals(0, cart.size());
    }

    @Test
    public void count() throws Exception {
        Assert.assertEquals(2, cartService.count());
    }

    @Test
    public void getTotalWhenDontHaveDiscount() throws Exception {
        Assert.assertEquals(3000, cartService.getTotal());
    }

    @Test
    public void getTotalWhenDontHaveEnoughtItemForDiscount() {
        discountManager.getDiscounts().add(new Discount(500, 5000));
        Assert.assertEquals(3000, cartService.getTotal());
    }

    @Test
    public void getTotalWhenHaveDiscount() {
        discountManager.getDiscounts().add(new Discount(500, 3000));
        Assert.assertEquals(2500, cartService.getTotal());
    }

    @Test
    public void getSubTotalWhenDontHaveDiscount() throws Exception {
        Assert.assertEquals(3000, cartService.getTotal());
    }

    @Test
    public void getSubTotalWhenDontHaveEnoughtItemForDiscount() {
        discountManager.getDiscounts().add(new Discount(500, 5000));
        Assert.assertEquals(3000, cartService.getTotal());
    }

    @Test
    public void getSubtotalWhenHaveDiscount() {
        discountManager.getDiscounts().add(new Discount(500, 3000));
        Assert.assertEquals(3000, cartService.getSubTotal());
    }

    @Test
    public void getCart() throws Exception {
        Assert.assertEquals(cart, cartService.getCart());
    }

    @Test
    public void getDiscount() throws Exception {
        discountManager.getDiscounts().add(new Discount(500, 3000));
        Assert.assertEquals(500, cartService.getDiscount().getAmount());
    }

    @Test
    public void setDiscount() throws Exception {
        cartService.setDiscount(new Discount(500, 3000));
        Assert.assertEquals(500, cartService.getDiscount().getAmount());
    }

}
