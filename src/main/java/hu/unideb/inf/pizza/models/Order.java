package hu.unideb.inf.pizza.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
    private int paymentTotal;

    /**
     * A rendeléshez tartozó kedvezmény.
     */
    private int discountTotal;

    /**
     * Rendelés leadásának ideje.
     */
    private Date orderDate;

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
     * @param comment       A rendeléshez tartozó megjegyzés
     * @param address       A rendeléshez tartozó cím
     * @param paymentTotal  A rendelés végösszege
     * @param discountTotal A rendelésnél igénybe vett kedvezmény
     * @param pizzas        A rendeléshez tartozó pizzák
     * @param user          A rendeléshez tartozó felhasználó
     */
    public Order(String comment, String address, int paymentTotal, int discountTotal, Collection<Pizza> pizzas, User user) {
        this.comment = comment;
        this.address = address;
        this.paymentTotal = paymentTotal;
        this.discountTotal = discountTotal;
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
    @Column(name = "payment_total")
    public int getPaymentTotal() {
        return paymentTotal;
    }

    /**
     * Beállítja a rendelés végösszegét.
     *
     * @param paymentTotal A rendelés végösszege
     */
    public void setPaymentTotal(int paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    /**
     * Visszaadja a rendeléshez tartozó kedvezményt.
     *
     * @return A rendeléshez tartozó kedvezmény
     */
    @Basic
    @Column(name = "discount_total")
    public int getDiscountTotal() {
        return discountTotal;
    }

    /**
     * Beállítjaja rendeléshez tartozó kedvezményt.
     *
     * @param discountTotal A rendeléshez tartozó kedvezmény
     */
    public void setDiscountTotal(int discountTotal) {
        this.discountTotal = discountTotal;
    }

    /**
     * Visszaadja a rendelés időpontját.
     *
     * @return A rendelés időpontja
     */
    @Basic
    @Column(name = "order_date")
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Beállítja a rendelés időpontját.
     *
     * @param order_date A rendelés időpontja
     */
    public void setOrderDate(Date order_date) {
        this.orderDate = order_date;
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
    @JoinTable(
            name = "orders_has_pizzas",
            joinColumns = @JoinColumn(name = "orders_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pizzas_id", referencedColumnName = "id")
    )
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

    /**
     * Létrehozáskor beállítja az order_date mező értékét az aktuális időpontra.
     */
    @PrePersist
    protected void onCreate() {
        this.orderDate = new Date();
    }
}
