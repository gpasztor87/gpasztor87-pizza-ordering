package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.interfaces.PizzaDao;
import hu.unideb.inf.pizza.managers.ConnectionManager;
import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.services.interfaces.PizzaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class PizzaServiceTest {

    private PizzaDao pizzaDao;

    private ConnectionManager connectionManager;

    private PizzaService pizzaService;

    @Before
    public void setUp() {
        pizzaDao = Mockito.mock(PizzaDao.class);
        connectionManager = Mockito.mock(ConnectionManager.class);

        Pizza margarita = new Pizza("Margarita", "", 1550, "margarita.jpg");
        Pizza szalamis = new Pizza("Szalámis", "", 1350, "szalamis.jpg");

        Mockito.when(pizzaDao.findAll()).thenReturn(Arrays.asList(margarita, szalamis));

        pizzaService = new PizzaServiceImpl(connectionManager, pizzaDao);
    }

    @Test
    public void testMockCreation() {
        Assert.assertNotNull(pizzaDao);
        Assert.assertNotNull(pizzaService);
    }

    @Test
    public void getAllPizza() {
        Assert.assertEquals(2, pizzaService.getAllPizza().size());
    }
}
