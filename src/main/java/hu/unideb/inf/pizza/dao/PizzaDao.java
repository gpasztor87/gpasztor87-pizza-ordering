package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.PizzaDaoInterface;
import hu.unideb.inf.pizza.models.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * A Pizza DAO interfészt megvalósító osztály.
 */
public class PizzaDao implements PizzaDaoInterface {

    /**
     * Az EntityManager egy példánya.
     */
    private EntityManager entityManager;

    @PersistenceContext
    private EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void create(Pizza pizza) {
        getEntityManager().persist(pizza);
    }

    @Override
    public void update(Pizza pizza) {
        getEntityManager().merge(pizza);
    }

    @Override
    public void delete(Pizza pizza) {
        getEntityManager().remove(pizza);
    }

    @Override
    public Pizza findById(int id) {
        return getEntityManager().find(Pizza.class, id);
    }

    @Override
    public List<Pizza> findAll() {
        Query query = getEntityManager().createQuery("SELECT p FROM Pizza as p");
        return query.getResultList();
    }

}
