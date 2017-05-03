package hu.unideb.inf.pizza.dao;

import hu.unideb.inf.pizza.dao.interfaces.UserDaoInterface;
import hu.unideb.inf.pizza.models.User;

import javax.persistence.EntityManager;

/**
 * A User DAO interfészt megvalósító osztály.
 */
public class UserDao implements UserDaoInterface {

    /**
     * Az EntityManager egy példánya.
     */
    private EntityManager entityManager;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findByEmail(String email) {
        return entityManager.find(User.class, email);
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

}
