package hu.unideb.inf.pizza.services.interfaces;

import hu.unideb.inf.pizza.models.User;

/**
 * A felhasználók kezelését végző osztály interfésze.
 */
public interface UserServiceInterface {

    /**
     * Elmenti a felhasználót a paraméterül kapott adatokkal.
     *
     * @param name     A felhasználó neve
     * @param email    A felhasználó email címe
     * @param password A felhasználó jelszava
     * @param address  A felhasználó címe
     * @param phone    A felhasználó telefnszáma
     */
    void save(String name, String email, String password, String address, String phone);

    /**
     * Visszaadja a paraméterül kapott emailhez tartozó {@link User} felhasználót.
     *
     * @param email A felhasználó email címe
     * @return A keresett felhasználó
     */
    User getUserByEmail(String email);


    boolean validate(String email, String password);
}
