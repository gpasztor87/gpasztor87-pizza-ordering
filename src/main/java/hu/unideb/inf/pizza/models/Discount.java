package hu.unideb.inf.pizza.models;

/**
 * Egy kedvezményt reprezentáló osztály.
 */
public class Discount {

    /**
     * Minimum kosárösszeg a kedvezmény érvényesítéséhez.
     */
    private Integer minimumAmount;

    /**
     * A kedvezmény értéke.
     */
    private int amount;

    /**
     * Konstruktor egy kedvezményt reprezentáló osztály létrehozásához.
     *
     * @param amount        A kedvezmény értéke
     * @param minimumAmount A kedvezmény minimum kosárösszege
     */
    public Discount(int amount, int minimumAmount) {
        this.amount = amount;
        this.minimumAmount = minimumAmount;
    }

    /**
     * Visszaadja a kedvezmény minimum kosárösszegét.
     *
     * @return A kedvezmény minimum kosárösszege
     */
    public int getMinimumAmount() {
        return minimumAmount;
    }

    /**
     * Beállítja a kedvezmény minimum kosárösszegét.
     *
     * @param minimumAmount A kedvezmény minimum kosárösszege
     */
    public void setMinimumAmount(int minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    /**
     * Visszaadja a kedvezmény értékét.
     *
     * @return A kedvezmény értéke
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Beállítja a kedvezmény értékét.
     *
     * @param amount A kedvezmény értéke
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
