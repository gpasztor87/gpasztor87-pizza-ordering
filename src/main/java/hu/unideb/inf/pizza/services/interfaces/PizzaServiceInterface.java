package hu.unideb.inf.pizza.services.interfaces;

import hu.unideb.inf.pizza.models.Pizza;

import java.util.List;

/**
 * A pizzák kezelését végző osztály interfésze.
 */
public interface PizzaServiceInterface {

    /**
     * Elmenti a paraméterül kapott pizzát.
     *
     * @param pizza A létrehozandó {@link Pizza} pizza
     */
    void createPizza(Pizza pizza);

    /**
     * Visszaadja az összes pizzát.
     *
     * @return Az összes {@link Pizza} pizza
     */
    List<Pizza> getAllPizza();

}
