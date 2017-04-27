package hu.unideb.inf.pizza.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "customers")
public class Customer {

    private int id;

    private String name;

    private String email;

    private String password;

    private String address;

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

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

}
