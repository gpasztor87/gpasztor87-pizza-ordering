package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.OrderDaoInterface;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Az Order DAO interfészt megvalósító osztály.
 */
public class OrderDao implements OrderDaoInterface {

    /**
     * A logger egy példánya.
     */
    private static Logger logger = LoggerFactory.getLogger(OrderDao.class);

    /**
     * Az EntityManager egy példánya.
     */
    private EntityManager entityManager;

    /**
     * Az osztály paraméter nélküli konstruktora inicializálja az entitymanagert.
     */
    public OrderDao() {
        entityManager = Persistence.createEntityManagerFactory("production").createEntityManager();
    }

    @Override
    public void create(Order order) {
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Order order) {
        entityManager.getTransaction().begin();
        entityManager.merge(order);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Order order) {
        entityManager.getTransaction().begin();
        entityManager.remove(order);
        entityManager.getTransaction().commit();
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

        try {
            return query.setParameter("user_id", user.getId()).getResultList();
        } catch (NoResultException e) {
            logger.error("No query result for the given user.");
            return null;
        }
    }
}
