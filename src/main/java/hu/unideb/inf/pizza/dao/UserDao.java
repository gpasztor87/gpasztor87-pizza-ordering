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

    /**
     * Elmenti a paraméterül kapott felhasználó az adatbázisba.
     *
     * @param user A menteni kívánt {@link User} felhasználó
     */
    public void create(User user) {
        getEntityManager().persist(user);
    }

    /**
     * Módosítja a paraméterül kapott felhasználó adatait.
     *
     * @param user A módosítandó {@link User} felhasználó
     */
    public void update(User user) {
        getEntityManager().merge(user);
    }

    /**
     * Lekér az adatbázisból egy felhasználót az email címe alapján.
     *
     * @param email A felhasználó email címe
     * @return Egy felhasználót reprezentáló {@link User} osztály
     */
    public User findByEmail(String email) {
        return getEntityManager().find(User.class, email);
    }

    /**
     * Lekér az adatbázisból egy felhasználót az egyedi azonosítója alapján.
     *
     * @param id A felhasználó egyedi azonosítója
     * @return Egy felhasználót reprezentáló {@link User} osztály
     */
    public User findById(int id) {
        return getEntityManager().find(User.class, id);
    }

}
