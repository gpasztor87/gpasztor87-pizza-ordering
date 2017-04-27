package hu.unideb.inf.pizza.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "toppings")
public class Topping {

    private Integer id;

    private String name;

    /**
     * Az osztály paraméter nélküli konstruktora.
     */
    public Topping() {
        super();
    }

    public Topping(String name) {
		this.name = name;
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

}
