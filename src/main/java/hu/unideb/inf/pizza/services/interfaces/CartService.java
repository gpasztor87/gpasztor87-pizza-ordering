package hu.unideb.inf.pizza.services.interfaces;

import hu.unideb.inf.pizza.models.Pizza;

import java.util.List;

/**
 * A kosár kezelését végző osztály interfésze.
 */
public interface CartService {

    /**
     * Hozzáadja a paraméterként kapott {@link Pizza} pizzát a kosárhoz.
     *
     * @param pizza A kosárhoz adott pizza
     */
    void add(Pizza pizza);

    /**
     * Eltávolítja a paraméterként kapott {@link Pizza} pizzát a kosárból.
     *
     * @param pizza A kosárból törlendő pizza
     */
    void remove(Pizza pizza);

    /**
     * Törli a kosár tartalmát.
     */
    void clear();

    /**
     * Visszaadja a kosárban levő {@link Pizza} pizzák darabszámát.
     *
     * @return A kosárban levő pizzák darabszáma
     */
    int count();

    /**
     * Visszaadja a kosárban levő pizzák összértékét.
     *
     * @return A kosárban levő pizzák összértéke
     */
    int sum();

    /**
     * Visszaadja a kosárban levő {@link Pizza} pizzák listáját.
     *
     * @return A kosárban levő pizzák listája
     */
    List<Pizza> getCart();

}
