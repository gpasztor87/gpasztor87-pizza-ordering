package hu.unideb.inf.pizza.dao.interfaces;

import hu.unideb.inf.pizza.models.Pizza;

import java.util.List;

/**
 * A pizzák DAO interfésze.
 */
public interface PizzaDao {

    /**
     * Elmenti a paraméterül kapott pizzát az adatbázisba.
     *
     * @param pizza A menteni kívánt {@link Pizza} pizza
     */
    void create(Pizza pizza);

    /**
     * Módosítja a paraméterül kapott pizzát az adatbázisban.
     *
     * @param pizza A módosítandó {@link Pizza} pizza
     */
    void update(Pizza pizza);

    /**
     * Törli a paraméterül kapott pizzát az adatbázisból.
     *
     * @param pizza A törlendő {@link Pizza} pizza
     */
    void delete(Pizza pizza);

    /**
     * Lekér az adatbázisból egy pizzát az egyedi azonosítója alapján.
     *
     * @param id A pizza egyedi azonosítója
     * @return Egy pizzát reprezentáló {@link Pizza} osztály
     */
    Pizza findById(int id);

    /**
     * Visszaadja az összes pizzát az adatbázisból.
     *
     * @return Az összes {@link Pizza} pizza
     */
    List<Pizza> findAll();
}
