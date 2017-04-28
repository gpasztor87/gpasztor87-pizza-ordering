package hu.unideb.inf.pizza.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Egy vásárlót reprezentáló osztály.
 */
@Entity
@Table(name = "customers")
public class Customer {

    /**
     * A vásárló egyedi azonosítója.
     */
    private int id;

    /**
     * A vásárló neve.
     */
    private String name;

    /**
     * A vásárló email címe.
     */
    private String email;

    /**
     * A vásárló jelszava.
     */
    private String password;

    /**
     * A vásárló címe.
     */
    private String address;

    /**
     * A vásárló telefonszáma.
     */
    private String phone;

    /**
     * Az osztály paraméter nélküli konstruktora.
     */
    public Customer() {
        super();
    }

    /**
     * Konstruktor egy vásárlót reprezentáló objektum létrehozására.
     *
     * @param id        A vásárló egyedi azonosítója
     * @param name      A vásárló neve
     * @param email     A vásárló email címe
     * @param address   A vásárló címe
     * @param phone     A vásárló telefonszáma
     */
    public Customer(int id, String name, String email, String address, String phone) {
        this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}

    /**
     * A vásárló rendelései.
     */
    @OneToMany(mappedBy = "customer")
    private Collection<Order> orders;

    /**
     * Visszaadja a vásárló egyedi azonosítóját.
     *
     * @return A vásárló egyedi azonosítója
     */
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    /**
     * Beállítja a vásárló egyedi azonosítóját.
     *
     * @param id A vásárló egyedi azonosítója
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Visszaadja a vásárló nevét.
     *
     * @return A vásárló neve
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Beállítja a vásárló nevét.
     *
     * @param name A vásárló neve
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Visszaadja a vásárló email címét.
     *
     * @return A vásárló email címe
     */
    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    /**
     * Beállítja a vásárló email címét.
     *
     * @param email A vásárló email címe
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Visszaadja a vásárló jelszavát.
     *
     * @return A vásárló jelszava
     */
    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    /**
     * Beállítja a vásárló jelszavát.
     *
     * @param password A vásárló jelszava
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Visszaadja a vásárló címét.
     *
     * @return A vásárló címe
     */
    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    /**
     * Beállítja a vásárló címét.
     *
     * @param address A vásárló címe
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Visszaadja a vásáló telefonszámát.
     *
     * @return A vásáló telefonszáma
     */
    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    /**
     * Beállítja a vásárló telefonszámát.
     *
     * @param phone A vásáló telefonszáma
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Visszaadja a vásárló rendeléseit.
     *
     * @return A vásárló rendelései
     */
    public Collection<Order> getOrders() {
        return orders;
    }

}
