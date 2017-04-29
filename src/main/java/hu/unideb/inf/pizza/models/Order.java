package hu.unideb.inf.pizza.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Egy rendelést reprezentáló osztály.
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    /**
     * A rendelés egyedi azonosítója.
     */
    private int id;

    /**
     * A rendeléshez tartozó megjegyzés.
     */
    private String comment;

    /**
     * A rendelés végösszege.
     */
    private double totalPrice;

    /**
     * Lezárult-e a rendelés?
     */
    private Byte isCompleted;

    /**
     * A rendeléshez tartozó pizzák.
     */
    private Collection<Pizza> pizzas;

    /**
     * A rendeléshez tartozó felhasználó.
     */
    private User user;

    /**
     * Az osztály paraméter nélküli konstruktora.
     */
    public Order() {
        super();
    }

    /**
     * Visszaadja a rendelés egyedi azonosítóját.
     *
     * @return A rendelés egyedi azonosítója
     */
    @Id
    public Integer getId() {
        return id;
    }

    /**
     * Beállítja a rendelés egyedi azonosítóját.
     *
     * @param id A rendelés egyedi azonosítója
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Visszaadja a rendeléshez tartozó megjegyzést.
     *
     * @return A megrendeléshez tartozó megjegyzés
     */
    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    /**
     * Beállítja a rendeléshez tartozó megjegyzést.
     *
     * @param comment A megrendeléshez tartozó megjegyzés
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Visszaadja a rendelés végösszegét.
     *
     * @return A rendelés végösszege
     */
    @Basic
    @Column(name = "total_price")
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Beállítja a rendelés végösszegét.
     *
     * @param totalPrice A rendelés végösszege
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Visszaadja, hogy lezárult-e a rendelés.
     *
     * @return Lezárt-e a rendelés?
     */
    @Basic
    @Column(name = "is_completed")
    public Byte getIsCompleted() {
        return isCompleted;
    }

    /**
     * Beállítja, hogy lezárt-e a rendelés.
     *
     * @param isCompleted Lezárt-e a rendelés?
     */
    public void setIsCompleted(Byte isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Visszaadja a rendeléshez tartozó felhasználót.
     *
     * @return A rendeléshez tartozó felhasználó
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    /**
     * Beállítja a rendeléshez a felhasználót.
     *
     * @param user A rendeléshez tartozó felhasználó
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Visszaadja a rendeléshez tartozó pizzákat.
     *
     * @return A rendeléshez tartozó pizzák
     */
    @ManyToMany
    @JoinTable(name = "orders_has_pizzas")
    public Collection<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Beállítja a rendeléshez tartozó pizzákat.
     *
     * @param pizzas A rendeléshez tartozó pizzák
     */
    public void setPizzas(Collection<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
