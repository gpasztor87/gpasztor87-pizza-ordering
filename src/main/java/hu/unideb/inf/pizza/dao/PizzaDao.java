package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.PizzaDaoInterface;
import hu.unideb.inf.pizza.models.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 */
public class PizzaDao implements PizzaDaoInterface {

    private EntityManager entityManager;

    @PersistenceContext
    private EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     *
     * @param pizza A menteni kívánt {@link Pizza} pizza
     */
    public void create(Pizza pizza) {
        getEntityManager().persist(pizza);
    }

    /**
     *
     * @param pizza A módosítandó {@link Pizza} pizza
     */
    public void update(Pizza pizza) {
        getEntityManager().merge(pizza);
    }

    /**
     *
     * @param pizza A törlendő {@link Pizza} pizza
     */
    public void delete(Pizza pizza) {
        getEntityManager().remove(pizza);
    }

    /**
     * Lekér az adatbázisból egy pizzát az egyedi azonosítója alapján.
     *
     * @param id A pizza egyedi azonosítója
     * @return Egy pizzát reprezentáló {@link Pizza} osztály
     */
    @Override
    public Pizza findById(int id) {
        return getEntityManager().find(Pizza.class, id);
    }

    /**
     * Visszaadja az összes pizzát az adatbázisból.
     *
     * @return Az összes {@link Pizza} pizza
     */
    @Override
    public List<Pizza> findAll() {
        Query query = getEntityManager().createQuery("SELECT p FROM Pizza as p");
        return query.getResultList();
    }

}
