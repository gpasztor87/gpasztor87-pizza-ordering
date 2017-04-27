package hu.unideb.inf.pizza.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "pizzas")
public class Pizza {

    private Integer id;

    private String name;

    private String description;

    private String size;

    private double price;

    private String imageUrl;

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

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "size")
    public String getSize() {
        return size;
    }

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
