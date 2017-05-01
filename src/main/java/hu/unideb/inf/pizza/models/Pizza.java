package hu.unideb.inf.pizza.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Egy pizzát reprezentáló osztály.
 */
@Entity
@Table(name = "pizzas")
public class Pizza implements Serializable {

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
    private int price;

    /**
     * A pizza képének az elérési útvonala.
     */
    private String imagePath;

    /**
     * A pizza feltétei.
     */
    private Collection<Topping> toppings;

    /**
     * Az osztály paraméter nélküli konstruktora.
     */
    public Pizza() {
        super();
    }

    /**
     * Konstruktor egy pizzát reprezentáló osztály létrehozására.
     *
     * @param name A pizza neve
     * @param description A pizza leírása
     * @param size A pizza mérete
     * @param price A pizza ára
     * @param imagePath A pizza képének elérési útvonala
     * @param toppings A pizza feltétei
     */
    public Pizza(String name, String description, String size, int price, String imagePath, Collection<Topping> toppings) {
        this.name = name;
		this.description = description;
		this.size = size;
		this.price = price;
		this.imagePath = imagePath;
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
     * Visszaadja a pizza nevét.
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
     * @return A pizza leírása
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

    /**
     * Visszaadja a pizza árát.
     *
     * @return A pizza ára
     */
    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    /**
     * Beállítja a pizza árát.
     *
     * @param price A pizza ára
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Visszaadja a pizza képének elérési útvonalát.
     *
     * @return A pizza képének elérési útvonala
     */
    @Basic
    @Column(name = "image_path")
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Beállítja a pizza elérési útvonalát.
     *
     * @param imagePath A pizza képének elérési útvonala
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Visszaadja a pizza feltéteit.
     *
     * @return A pizza feltétei
     */
    @ManyToMany
    @JoinTable(name = "pizzas_has_toppings")
    public Collection<Topping> getToppings() {
        return toppings;
    }

    /**
     * Beállítja a pizza feltéteit.
     *
     * @param toppings A pizza feltétei
     */
    public void setToppings(Collection<Topping> toppings) {
        this.toppings = toppings;
    }

}
