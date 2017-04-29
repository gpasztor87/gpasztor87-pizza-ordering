package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.OrderDaoInterface;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Az Order DAO interfészt megvalósító osztály.
 */
public class OrderDao implements OrderDaoInterface {

    private EntityManager entityManager;

    @PersistenceContext
    private EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Elmenti a paraméterül kapott rendelést az adatbázisba.
     *
     * @param order A menteni kívánt {@link Order} rendelés
     */
    @Override
    public void create(Order order) {
        getEntityManager().persist(order);
    }

    /**
     * Módosítja a paraméterül kapott rendelést az adatbázisban.
     *
     * @param order A módosítandó {@link Order} rendelés
     */
    @Override
    public void update(Order order) {
        getEntityManager().merge(order);
    }

    /**
     * Törli a paraméterül kapott rendelést az adatbázisból.
     *
     * @param order A törlendő {@link Order} rendelés
     */
    @Override
    public void delete(Order order) {
        getEntityManager().remove(order);
    }

    /**
     * Lekér az adatbázisból egy rendelést az egyedi azonosítója alapján.
     *
     * @param id A rendelés egyedi azonosítója
     * @return Egy rendelést reprezentáló {@link Order} osztály
     */
    @Override
    public Order findById(int id) {
        return getEntityManager().find(Order.class, id);
    }

    /**
     *
     * Visszaadja a paraméterül kapott felhasználó rendeléseit.
     *
     * @param user A keresett {@link User} felhasználó
     * @return A felhasználó összes {@link Order} rendelése
     */
    @Override
    public List<Order> findByUser(User user) {
        return null;
    }
}
