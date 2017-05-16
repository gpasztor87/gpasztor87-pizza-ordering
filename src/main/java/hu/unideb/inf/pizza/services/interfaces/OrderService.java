package hu.unideb.inf.pizza.services.interfaces;

import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;

import java.util.List;

/**
 * A rendelések kezelését végző osztály interfésze.
 */
public interface OrderService {

    /**
     * Elmenti a paraméterül kapott rendelést.
     *
     * @param order A létrehozandó {@link Order} rendelés
     */
    void createOrder(Order order);

    /**
     * Visszaadja a paraméterül kapott felhasználó rendeléseit.
     *
     * @param user Egy {@link User} felhasználó
     * @return A felhasználó {@link Order} rendelései
     */
    List<Order> getUserOrders(User user);

}
