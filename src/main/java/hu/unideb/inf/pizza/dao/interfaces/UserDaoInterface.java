package hu.unideb.inf.pizza.dao.interfaces;

import hu.unideb.inf.pizza.models.User;

/**
 * A felhasználók DAO interfésze.
 */
public interface UserDaoInterface extends GenericDaoInterface<User, Integer> {

    /**
     * Lekér az adatbázisból egy felhasználót az email címe alapján.
     *
     * @param email A felhasználó email címe
     * @return Egy felhasználót reprezentáló {@link User} osztály
     */
    User findByEmail(String email);

}
