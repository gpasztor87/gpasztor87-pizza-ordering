package hu.unideb.inf.pizza.models;

import javax.persistence.*;

/**
 * Egy feltétet reprezentáló osztály.
 */
@Entity
@Table(name = "toppings")
public class Topping {

    /**
     * A feltét egyedi azonosítója.
     */
    private Integer id;

    /**
     * A feltét neve.
     */
    private String name;

    /**
     * Az osztály paraméter nélküli konstruktora.
     */
    public Topping() {
        super();
    }

    /**
     * Konstruktor egy feltétet reprezentáló osztály létrehozására.
     *
     * @param name A feltét neve
     */
    public Topping(String name) {
		this.name = name;
	}

    /**
     * Visszaadja a feltét egyedi azonosítóját.
     *
     * @return A feltét egyedi azonosítója
     */
    @Id
    public Integer getId() {
        return id;
    }

    /**
     * Beállítja a feltét egyedi azoosítóját.
     *
     * @param id A feltét egyedi azonosítója
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Visszaadja a feltét nevét.
     *
     * @return A feltét neve
     */
	@Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Beállítja a feltét nevét.
     *
     * @param name A feltét neve
     */
    public void setName(String name) {
        this.name = name;
    }

}
