package hu.unideb.inf.pizza.dao.interfaces;

import hu.unideb.inf.pizza.models.User;

/**
 * A felhasználók DAO interfésze.
 */
public interface UserDaoInterface {

    /**
     * Elmenti a paraméterül kapott felhasználót az adatbázisba.
     *
     * @param user A menteni kívánt {@link User} felhasználó
     */
    void create(User user);

    /**
     * Módosítja a paraméterül kapott felhasználó adatait.
     *
     * @param user A módosítandó {@link User} felhasználó
     */
    void update(User user);

    /**
     * Lekér az adatbázisból egy felhasználót az email címe alapján.
     *
     * @param email A felhasználó email címe
     * @return Egy felhasználót reprezentáló {@link User} osztály
     */
    User findByEmail(String email);

    /**
     * Lekér az adatbázisból egy felhasználót az egyedi azonosítója alapján.
     *
     * @param id A felhasználó egyedi azonosítója
     * @return Egy felhasználót reprezentáló {@link User} osztály
     */
    User findById(int id);

}
