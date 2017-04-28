package hu.unideb.inf.pizza.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Egy pizzát reprezentáló osztály.
 */
@Entity
@Table(name = "pizzas")
public class Pizza {

    /**
     * A pizza egyedi azonosítója.
     */
    private Integer id;

    /**
     * A pizza neve.
     */
    private String name;

    /**
     * A pizza leírása.
     */
    private String description;

    /**
     * A pizza mérete.
     */
    private String size;

    /**
     * A pizza ára.
     */
    private double price;

    /**
     * A pizza képének helye.
     */
    private String imageUrl;

    /**
     * A pizza feltétei.
     */
    private Collection<Topping> toppings;

    private Collection<Order> orders;

    /**
     * Az osztály paraméter nélküli konstruktora.
     */
    public Pizza() {
        super();
    }

    public Pizza(String name, String description, String size, double price, String imageUrl, Collection<Topping> toppings) {
		this.name = name;
		this.description = description;
		this.size = size;
		this.price = price;
		this.imageUrl = imageUrl;
		this.toppings = toppings;
	}

    /**
     * Visszaadja a pizza egyedi azonosítóját.
     *
     * @return A pizza egyedi azonosítója
     */
    @Id
    public Integer getId() {
        return id;
    }

    /**
     * Beállítja a pizza egyedi azonosítóját.
     *
     * @param id A pizza egyedi azonosítója
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Viisszaadja apizza nevét.
     *
     * @return A pizza neve
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Beállítja a pizza nevét.
     *
     * @param name A pizza neve
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Visszaadja a pizza leírását.
     *
     * @return A pizaz leírása
     */
    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    /**
     * Beállítja a pizza leírását.
     *
     * @param description A pizza leírása
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Visszaadja a pizza méretét.
     *
     * @return A pizza mérete
     */
    @Basic
    @Column(name = "size")
    public String getSize() {
        return size;
    }

    /**
     * Beállítja a pizza méretét.
     *
     * @param size A pizza mérete
     */
    public void setSize(String size) {
        this.size = size;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ManyToMany
    @JoinTable(name = "pizzas_has_toppings")
    public Collection<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(Collection<Topping> toppings) {
        this.toppings = toppings;
    }

    @ManyToMany(mappedBy = "pizzas")
    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

}
