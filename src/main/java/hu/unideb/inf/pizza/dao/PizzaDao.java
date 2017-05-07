package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.PizzaDaoInterface;
import hu.unideb.inf.pizza.models.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * A Pizza DAO interfészt megvalósító osztály.
 */
public class PizzaDao implements PizzaDaoInterface {

    /**
     * Az EntityManager egy példánya.
     */
    private EntityManager entityManager;

    public PizzaDao() {
        entityManager = Persistence.createEntityManagerFactory("production").createEntityManager();
    }

    @Override
    public void create(Pizza pizza) {
        entityManager.getTransaction().begin();
        entityManager.persist(pizza);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Pizza pizza) {
        entityManager.getTransaction().begin();
        entityManager.merge(pizza);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Pizza pizza) {
        entityManager.getTransaction().begin();
        entityManager.remove(pizza);
        entityManager.getTransaction().commit();
    }

    @Override
    public Pizza findById(int id) {
        return entityManager.find(Pizza.class, id);
    }

    @Override
    public List<Pizza> findAll() {
        TypedQuery<Pizza> query = entityManager.createQuery("SELECT p FROM Pizza as p", Pizza.class);
        return query.getResultList();
    }

}
