package hu.unideb.inf.pizza.services.interfaces;

import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.models.User;

import java.util.Collection;
import java.util.List;

/**
 * A rendelések kezelését végző osztály interfésze.
 */
public interface OrderServiceInterface {

    /**
     * Elmenti a rendelést a paraméterül kapott adatokkal.
     *
     * @param user       A bejelentkezett felhasználó
     * @param address    A bejelentkezett felhasználó címe
     * @param comment    A rendeléshez tartozó megjegyzés
     * @param totalPrice A rendelés végösszege
     * @param pizzas     A megrendelt pizzák
     */
    void create(User user, String address, String comment, int totalPrice, Collection<Pizza> pizzas);

    /**
     * Visszaadja a paraméterül kapott felhasználó rendeléseit.
     *
     * @param user Egy {@link User} felhasználó
     * @return A felhasználó {@link Order} rendelései
     */
    List<Order> getUserOrders(User user);

}
