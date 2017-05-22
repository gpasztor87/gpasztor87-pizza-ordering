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

    public CartServiceImpl(List<Pizza> cart) {
        this.cart = cart;
    }

    @Override
    public void add(Pizza pizza) {
        getCart().add(pizza);
    }

    @Override
    public void remove(Pizza pizza) {
        getCart().remove(pizza);
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
    public int getTotal() {
        calculateDiscount();
        int itemAmount = getSubTotal();

        return itemAmount - getDiscount().getAmount();
    }

    @Override
    public int getSubTotal() {
        return getCart().stream().mapToInt(Pizza::getPrice).sum();
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
        int sumOfItems = getSubTotal();

        List<Discount> discounts = DiscountManager.getInstance().getDiscounts();
        Optional<Discount> discount = discounts.stream()
                .filter(d -> d.getMinimumAmount() <= sumOfItems)
                .reduce((d1, d2) -> d1.getAmount() > d2.getAmount() ? d1 : d2);

        if (discount.isPresent()) {
            setDiscount(discount.get());
        } else {
            setDiscount(new Discount(0, 0));
        }
    }
}
