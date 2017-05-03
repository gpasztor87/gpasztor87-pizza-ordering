package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.OrderDaoInterface;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;

import javax.persistence.EntityManager;
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

    public OrderDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void update(Order order) {
        entityManager.merge(order);
    }

    @Override
    public void delete(Order order) {
        entityManager.remove(order);
    }

    @Override
    public Order findById(int id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> findByUser(User user) {
        TypedQuery<Order> query = entityManager.createQuery(
            "SELECT  o FROM Order o WHERE user.id = :user_id", Order.class
        );

        return query.setParameter("user_id", user.getId()).getResultList();
    }
}
