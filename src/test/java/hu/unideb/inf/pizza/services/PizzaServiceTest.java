package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.managers.ConnectionManager;
import hu.unideb.inf.pizza.managers.JpaConnectionManager;
import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.services.interfaces.PizzaServiceInterface;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PizzaServiceTest {

    private ConnectionManager connectionManager;

    private PizzaServiceInterface pizzaService;

    @Before
    public void init() {
        connectionManager = new JpaConnectionManager("test");
        pizzaService = new PizzaService(connectionManager);
    }

    @After
    public void destroy() {
        connectionManager.close();
    }

    @Test
    public void getAllPizza() {
        // Given
        this.createTestPizza();

        // When
        List<Pizza> pizzaList = pizzaService.getAllPizza();

        // Then
        Assert.assertEquals(1, pizzaList.size());
    }

    private Pizza createTestPizza() {
        Pizza pizza = new Pizza();
        pizza.setName("Margarita");
        pizza.setPrice(1350);
        pizza.setDescription("description");

        return pizza;
    }
}
