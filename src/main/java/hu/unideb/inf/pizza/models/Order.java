package hu.unideb.inf.pizza.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Egy rendelést reprezentáló osztály.
 */
@Entity
@Table(name = "orders")
public class Order {

    /**
     * A rendelés egyedi azonosítója.
     */
    private int id;

    private String comment;

    private double totalPrice;

    private String paymentType;

    private Byte completed;

    private Collection<Pizza> pizzas;

    private Customer customer;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "total_price")
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "payment_type")
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Basic
    @Column(name = "completed")
    public Byte getCompleted() {
        return completed;
    }

    public void setCompleted(Byte completed) {
        this.completed = completed;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToMany
    @JoinTable(name = "orders_has_pizzas")
    public Collection<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Collection<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
