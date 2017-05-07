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
     * @param phone    A felhasználó telefonszáma
     * @return User Visszaadja a {@link User} felhasználót
     */
    User create(String name, String email, String password, String address, String phone);

    /**
     * Módosítja a felhasználó adatait a paraméterül kapott adatokkal.
     *
     * @param id       A felhasználó egyedi azonosítója
     * @param name     A felhasználó neve
     * @param password A felhasználó jelszava
     * @param address  A felhasználó címe
     * @param phone    A felhasználó telefonszáma
     * @return User Visszaadja a {@link User} felhasználót
     */
    User update(int id, String name, String password, String address, String phone);

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
    boolean validate(String email, String password);
}
