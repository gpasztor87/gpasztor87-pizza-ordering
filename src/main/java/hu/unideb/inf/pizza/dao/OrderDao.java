package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.OrderDaoInterface;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

/**
 * Az Order DAO interfészt megvalósító osztály.
 */
public class OrderDao extends GenericDao<Order, Integer> implements OrderDaoInterface {

    /**
     * A logger egy példánya.
     */
    private static Logger logger = LoggerFactory.getLogger(OrderDao.class);

    @Override
    public List<Order> findByUser(User user) {
        TypedQuery<Order> query = getEntityManager().createQuery(
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
