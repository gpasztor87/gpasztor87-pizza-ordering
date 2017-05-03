package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.UserDaoInterface;
import hu.unideb.inf.pizza.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * A User DAO interfészt megvalósító osztály.
 */
public class UserDao implements UserDaoInterface {

    /**
     * Az EntityManager egy példánya.
     */
    private EntityManager entityManager;

    @PersistenceContext
    private EntityManager getEntityManager() {
        return entityManager;
    }

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(User user) {
        getEntityManager().persist(user);
    }

    @Override
    public void update(User user) {
        getEntityManager().merge(user);
    }

    @Override
    public User findByEmail(String email) {
        return getEntityManager().find(User.class, email);
    }

    @Override
    public User findById(int id) {
        return getEntityManager().find(User.class, id);
    }

}
