package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.UserDaoInterface;
import hu.unideb.inf.pizza.models.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * A User DAO interfészt megvalósító osztály.
 */
public class UserDao implements UserDaoInterface {

    /**
     * A logger egy példánya.
     */
    private static Logger logger = LoggerFactory.getLogger(UserDao.class);

    /**
     * Az EntityManager egy példánya.
     */
    private EntityManager entityManager;

    public UserDao() {
        entityManager = Persistence.createEntityManagerFactory("production").createEntityManager();
    }

    @Override
    public void create(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
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
