package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.PizzaDaoInterface;
import hu.unideb.inf.pizza.models.Pizza;

import javax.persistence.EntityManager;

/**
 * A Pizza DAO interfészt megvalósító osztály.
 */
public class PizzaDao extends GenericDao<Pizza, Integer> implements PizzaDaoInterface {

    public PizzaDao(EntityManager entityManager) {
        super(entityManager);
    }
}
