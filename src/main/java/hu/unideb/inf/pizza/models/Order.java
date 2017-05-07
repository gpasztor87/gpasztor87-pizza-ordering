package hu.unideb.inf.pizza.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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
     * A rendeléshez tartozó cím.
     */
    private String address;

    /**
     * A rendelés végösszege.
     */
    private double totalPrice;

    /**
     * Rendelés leadásának ideje.
     */
    private LocalDate order_date;

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
     * Konstruktor egy rendelést reprezentáló osztály létrehozására.
     *
     * @param comment    A rendeléshez tartozó megjegyzés
     * @param address    A rendeléshez tartozó cím
     * @param totalPrice A rendelés végösszege
     * @param order_date A rendelés dátuma
     * @param pizzas     A rendeléshez tartozó pizzák
     * @param user       A rendeléshez tartozó felhasználó
     */
    public Order(String comment, String address, double totalPrice, LocalDate order_date, Collection<Pizza> pizzas, User user) {
        this.comment = comment;
        this.address = address;
        this.totalPrice = totalPrice;
        this.order_date = order_date;
        this.pizzas = pizzas;
        this.user = user;
    }

    /**
     * Visszaadja a rendelés egyedi azonosítóját.
     *
     * @return A rendelés egyedi azonosítója
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * Visszaadja a rendeléshez tartozó címet.
     *
     * @return A rendeléshez tartozó cím
     */
    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    /**
     * Beállítja a rendeléshez tartozó címet.
     *
     * @param address A rendeléshez tartozó cím
     */
    public void setAddress(String address) {
        this.address = address;
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
     * Visszaadja a rendelés időpontját.
     *
     * @return A rendelés időpontja
     */
    @Basic
    @Column(name = "order_date")
    @CreationTimestamp
    public LocalDate getOrder_date() {
        return order_date;
    }

    /**
     * Beállítja a rendelés időpontját.
     *
     * @param order_date A rendelés időpontja
     */
    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
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
