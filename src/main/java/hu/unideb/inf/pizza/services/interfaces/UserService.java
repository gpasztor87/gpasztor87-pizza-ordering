package hu.unideb.inf.pizza.services.interfaces;

import hu.unideb.inf.pizza.models.User;

/**
 * A felhasználók kezelését végző osztály interfésze.
 */
public interface UserService {

    /**
     * Elmenti a paraméterül kapott felhasználót.
     *
     * @param user  A létrehozandó {@link User} felhasználó
     * @return User Visszaadja a {@link User} felhasználót
     */
    User createUser(User user);

    /**
     * Módosítja a paraméterül kapott felhasználó adatait.
     *
     * @param user  A módosítandó {@link User} felhasználó
     * @return User Visszaadja a {@link User} felhasználót
     */
    User updateUser(User user);

    /**
     * Visszaadja a paraméterül kapott egyedi azonosítóhoz tartozó {@link User} felhasználót.
     *
     * @param id A felhasználó egyedi azonosítója
     * @return A keresett felhasználó
     */
    User getUserById(int id);

    /**
     * Visszaadja a paraméterül kapott emailhez tartozó {@link User} felhasználót.
     *
     * @param email A felhasználó email címe
     * @return A keresett felhasználó
     */
    User getUserByEmail(String email);

    /**
     * Leellenőrzi, hogy a kapott adatok hitelesítenek-e egy felhasználót.
     *
     * @param email    A felhasználó email címe
     * @param password A felhasználó jelszava
     * @return Sikeres-e a hitelesítés
     */
    boolean validateUser(String email, String password);
}
