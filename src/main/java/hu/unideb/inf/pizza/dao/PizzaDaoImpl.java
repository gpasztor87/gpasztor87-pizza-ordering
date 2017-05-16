package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.PizzaDao;
import hu.unideb.inf.pizza.models.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * A Pizza DAO interfészt megvalósító osztály.
 */
public class PizzaDaoImpl implements PizzaDao {

    /**
     * Az EntityManager egy példánya.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Az osztály konstruktora inicializálja az entitymanagert.
     *
     * @param entityManager Az entityManager
     */
    public PizzaDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Pizza pizza) {
        entityManager.persist(pizza);
    }

    @Override
    public void update(Pizza pizza) {
        entityManager.merge(pizza);
    }

    @Override
    public void delete(Pizza pizza) {
        entityManager.remove(pizza);
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
