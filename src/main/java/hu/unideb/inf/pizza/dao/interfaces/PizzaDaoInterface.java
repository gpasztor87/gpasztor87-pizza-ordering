package hu.unideb.inf.pizza.dao.interfaces;

import hu.unideb.inf.pizza.models.Pizza;

import java.util.List;

/**
 * A pizzák DAO interfésze.
 */
public interface PizzaDaoInterface extends GenericDaoInterface<Pizza, Integer> {

    /**
     * Visszaadja az összes pizzát az adatbázisból.
     *
     * @return Az összes {@link Pizza} pizza
     */
    List<Pizza> findAll();
}
