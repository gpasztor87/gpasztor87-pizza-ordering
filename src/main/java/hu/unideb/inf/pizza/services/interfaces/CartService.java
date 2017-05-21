package hu.unideb.inf.pizza.services.interfaces;

import hu.unideb.inf.pizza.models.Discount;
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
     * Visszaadja a kosár tartalmának összegét, levonva a kedvezményt, ha van.
     *
     * @return A kosár tartalmának összege
     */
    int getTotal();

    /**
     * Visszaadja a kosárban levő pizzák összértékét.
     *
     * @return A kosárban levő pizzák összértéke
     */
    int getSubTotal();

    /**
     * Visszaadja a kosárban levő {@link Pizza} pizzák listáját.
     *
     * @return A kosárban levő pizzák listája
     */
    List<Pizza> getCart();

    /**
     * Visszadja a kosár tartalmához kapott {@link Discount} kedvezményt.
     *
     * @return A kosár kedvezménye
     */
    Discount getDiscount();

    /**
     * Beállítja a kosárhoz a paraméterül kapott kedvezményt.
     *
     * @param discount A beállítandó kedvezmény
     */
    void setDiscount(Discount discount);

}
