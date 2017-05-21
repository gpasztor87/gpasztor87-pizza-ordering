package hu.unideb.inf.pizza.managers;

import hu.unideb.inf.pizza.models.Discount;

import java.util.ArrayList;
import java.util.List;

/**
 * A kedvezményeket kezelő osztály.
 */
public class DiscountManager {

    /**
     * Az osztály egyetlen példányát tartalmazó statikus adattag.
     */
    private static DiscountManager discountManager = new DiscountManager();

    /**
     * Az összes kedvezményt tartalmazó lista.
     */
    private List<Discount> discounts = new ArrayList<Discount>();

    /**
     * Az osztály üres konstruktora.
     */
    private DiscountManager() {
    }

    /**
     * Visszaadja az osztály egyetlen példányát.
     *
     * @return Az osztály egyedlen példánya
     */
    public static DiscountManager getInstance() {
        return discountManager;
    }

    /**
     * Hozzáadja a paraméterként kapott {@link Discount} kedvezményt a kedvezményeket tartalmazó listához.
     *
     * @param discount A létrehozandó kedvezmény
     */
    public void add(Discount discount) {
        discounts.add(discount);
    }

    /**
     * Visszaadja az összes kedvezményt.
     *
     * @return Az összes kedvezményt tartalmazó lista
     */
    public List<Discount> getDiscounts() {
        return discounts;
    }

    /**
     * Törli az összes kedvezményt.
     */
    public void clear() {
        discounts.clear();
    }
}
