package hu.unideb.inf.pizza.dao.interfaces;

import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;

import java.util.List;

/**
 * A rendelések DAO interfésze.
 */
public interface OrderDaoInterface {

    /**
     * Elmenti a paraméterül kapott rendelést az adatbázisba.
     *
     * @param order A menteni kívánt {@link Order} rendelés
     */
    void create(Order order);

    /**
     * Módosítja a paraméterül kapott rendelést az adatbázisban.
     *
     * @param order A módosítandó {@link Order} rendelés
     */
    void update(Order order);

    /**
     * Törli a paraméterül kapott rendelést az adatbázisból.
     *
     * @param order A törlendő {@link Order} rendelés
     */
    void delete(Order order);

    /**
     * Visszaadja a paraméterül kapott felhasználó rendeléseit.
     *
     * @param user A keresett {@link User} felhasználó
     * @return A felhasználó összes {@link Order} rendelése
     */
    List<Order> findByUser(User user);

}
