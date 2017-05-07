package hu.unideb.inf.pizza.services.interfaces;

import hu.unideb.inf.pizza.models.Pizza;

import java.util.List;

/**
 * A pizzák kezelését végző osztály interfésze.
 */
public interface PizzaServiceInterface {

    /**
     * Visszaadja az összes pizzát.
     *
     * @return Az összes {@link Pizza} pizza
     */
    List<Pizza> getAllPizza();

}
