package hu.unideb.inf.pizza.services.interfaces;

import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;

import java.util.List;

/**
 * Az orders controller
 */
public interface OrderServiceInterface {

    /**
     * Visszaadja a paraméterül kapott felhasználó rendeléseit.
     *
     * @param user Egy {@link User} felhasználó
     * @return A felhasználó {@link Order} rendelései
     */
    List<Order> getUserOrders(User user);

}
