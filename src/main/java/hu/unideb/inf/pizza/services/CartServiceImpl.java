package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.services.interfaces.CartService;

import java.util.ArrayList;
import java.util.List;

/**
 * A CartService interfészt megvalósító osztály.
 */
public class CartServiceImpl implements CartService {

    /**
     * A kosár tartalmát tartalmazó lista.
     */
    private List<Pizza> cart = new ArrayList<Pizza>();

    /**
     * Az osztály paraméter nélküli konstruktora.
     */
    public CartServiceImpl() {
        super();
    }

    @Override
    public void add(Pizza pizza) {
        getCart().add(pizza);
    }

    @Override
    public void remove(Pizza pizza) {
        getCart().remove(pizza);
    }

    @Override
    public void clear() {
        getCart().clear();
    }

    @Override
    public int count() {
        return getCart().size();
    }

    @Override
    public int sum() {
        return getCart().stream().mapToInt(Pizza::getPrice).sum();
    }

    @Override
    public List<Pizza> getCart() {
        return cart;
    }
}
