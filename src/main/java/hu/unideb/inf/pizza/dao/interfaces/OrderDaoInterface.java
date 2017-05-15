package hu.unideb.inf.pizza.dao.interfaces;

import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;

import java.util.List;

/**
 * A rendelések DAO interfésze.
 */
public interface OrderDaoInterface extends GenericDaoInterface<Order, Integer> {

    /**
     * Visszaadja a paraméterül kapott felhasználó rendeléseit.
     *
     * @param user A keresett {@link User} felhasználó
     * @return A felhasználó összes {@link Order} rendelése
     */
    List<Order> findByUser(User user);

}
