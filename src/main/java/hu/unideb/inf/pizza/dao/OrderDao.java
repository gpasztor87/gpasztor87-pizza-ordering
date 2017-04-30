package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.OrderDaoInterface;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Az Order DAO interfészt megvalósító osztály.
 */
public class OrderDao implements OrderDaoInterface {

    /**
     * Az EntityManager egy példánya.
     */
    private EntityManager entityManager;

    @PersistenceContext
    private EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void create(Order order) {
        getEntityManager().persist(order);
    }

    @Override
    public void update(Order order) {
        getEntityManager().merge(order);
    }

    @Override
    public void delete(Order order) {
        getEntityManager().remove(order);
    }

    @Override
    public Order findById(int id) {
        return getEntityManager().find(Order.class, id);
    }

    @Override
    public List<Order> findByUser(User user) {
        TypedQuery<Order> query = getEntityManager().createQuery(
            "SELECT  o FROM Order o WHERE user.id = :user_id", Order.class
        );

        return query.setParameter("user_id", user.getId()).getResultList();
    }
}
