package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.managers.DiscountManager;
import hu.unideb.inf.pizza.models.Discount;
import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.services.interfaces.CartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A CartService interfészt megvalósító osztály.
 */
public class CartServiceImpl implements CartService {

    /**
     * A kosár tartalmát tartalmazó lista.
     */
    private List<Pizza> cart = new ArrayList<Pizza>();

    /**
     * Az aktuális kedvezmény.
     */
    private Discount discount;

    /**
     * Az osztály paraméter nélküli konstruktora.
     */
    public CartServiceImpl() {
        super();
    }

    @Override
    public void add(Pizza pizza) {
        getCart().add(pizza);
        calculateDiscount();
    }

    @Override
    public void remove(Pizza pizza) {
        getCart().remove(pizza);
        calculateDiscount();
    }

    @Override
    public void clear() {
        getCart().clear();
    }

    @Override
    public int count() {
        return getCart().size();
    }

    @Override
    public int sum() {
        int itemAmount = getCart().stream().mapToInt(Pizza::getPrice).sum();
        if (discount != null) {
            return itemAmount - getDiscount().getAmount();
        }

        return itemAmount;
    }

    @Override
    public List<Pizza> getCart() {
        return cart;
    }

    @Override
    public Discount getDiscount() {
        return discount;
    }

    @Override
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * Beállítja, hogy az aktuális kosár tartalmához milyen kedvezmény társul.
     */
    private void calculateDiscount() {
        int sumOfItems = cart.stream().mapToInt(Pizza::getPrice).sum();

        List<Discount> discounts = DiscountManager.getInstance().getDiscounts();
        Optional<Discount> discount = discounts.stream()
                .filter(d -> d.getMinimumAmount() <= sumOfItems)
                .reduce((d1, d2) -> d1.getAmount() > d2.getAmount() ? d1 : d2);

        if (discount.isPresent()) {
            setDiscount(discount.get());
        }
    }
}
