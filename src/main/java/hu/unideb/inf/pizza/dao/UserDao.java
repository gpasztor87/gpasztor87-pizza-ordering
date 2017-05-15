package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.UserDaoInterface;
import hu.unideb.inf.pizza.models.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

/**
 * A User DAO interfészt megvalósító osztály.
 */
public class UserDao extends GenericDao<User, Integer> implements UserDaoInterface {

    /**
     * A logger egy példánya.
     */
    private static Logger logger = LoggerFactory.getLogger(UserDao.class);

    public UserDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = getEntityManager().createQuery(
                "select u from User u where email = :email", User.class
        );

        try {
            return query.setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            logger.error("No query result for the given email: " + email);
            return null;
        }
    }

}
