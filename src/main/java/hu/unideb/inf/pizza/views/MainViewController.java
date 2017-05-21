package hu.unideb.inf.pizza.views;

import hu.unideb.inf.pizza.dao.PizzaDaoImpl;
import hu.unideb.inf.pizza.dao.interfaces.PizzaDao;
import hu.unideb.inf.pizza.managers.JpaConnectionManager;
import hu.unideb.inf.pizza.models.Pizza;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.CartServiceImpl;
import hu.unideb.inf.pizza.services.PizzaServiceImpl;
import hu.unideb.inf.pizza.services.interfaces.CartService;
import hu.unideb.inf.pizza.services.interfaces.PizzaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * A fő felhasználói felületet vezérlő osztály.
 */
public class MainViewController implements Initializable {

    /**
     * A logger egy példánya.
     */
    private static Logger logger = LoggerFactory.getLogger(MainViewController.class);

    /**
     * A Fő Stage.
     */
    @FXML
    private static Stage stage;

    /**
     * A felső menü.
     */
    @FXML
    private MenuBar menuBar;

    /**
     * Bejelentkezési menüpont.
     */
    @FXML
    private MenuItem loginMenuItem;

    /**
     * Regisztrációs menüpont.
     */
    @FXML
    private MenuItem registerMenuItem;

    /**
     * Profilom menüpont.
     */
    @FXML
    private MenuItem profileMenuItem;

    /**
     * Kijelentkezési menüpont.
     */
    @FXML
    private MenuItem logoutMenuItem;

    /**
     * A pizzákat tartalmazó panel.
     */
    @FXML
    private AnchorPane pizzaListPane;

    /**
     * Kilépés menüpont.
     */
    @FXML
    private MenuItem exitMenuItem;

    /**
     * Rendeléseim menüpont.
     */
    @FXML
    private MenuItem ordersMenuItem;

    /**
     * A pizzákat tartalmazó listanézet.
     */
    @FXML
    private ListView<Pizza> pizzaListView;

    /**
     * A felhasználói kosarat tartalmazó táblázat.
     */
    @FXML
    private TableView<Pizza> cartTable;

    /**
     * A kosárban levő pizzák neveit tartalmazó oszlop.
     */
    @FXML
    private TableColumn<Pizza, String> pizzaNameColumn;

    /**
     * A kosárban levő pizzák árát tartalmazó oszlop.
     */
    @FXML
    private TableColumn<Pizza, Integer> pizzaPriceColumn;

    /**
     * A kosár végösszegét tartalmazó címke.
     */
    @FXML
    private Label cartSumAttribute;

    /**
     * A kosár kedvezményt tartalmazó címke.
     */
    @FXML
    private Label cartDiscountAttribute;

    /**
     * A megrendelést elindító gomb.
     */
    @FXML
    private Button payButton;

    /**
     * A bejelentkezett felhasználó.
     */
    private User currentUser = null;

    /**
     * A pizzákat tartalmazó listanézet adatforrása.
     */
    private ObservableList<Pizza> listData = FXCollections.observableArrayList();

    /**
     * A {@link PizzaService} interfész egy implementációjának példánya.
     */
    private PizzaService pizzaService;

    /**
     * A {@link CartService} interfész egy implementációjának példánya.
     */
    private CartService cartService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        switchMenuLoggedOut();
        payButton.setDisable(true);

        cartService = new CartServiceImpl();

        JpaConnectionManager connectionManager = new JpaConnectionManager();
        PizzaDao pizzaDao = new PizzaDaoImpl(connectionManager.getEntityManager());

        pizzaService = new PizzaServiceImpl(connectionManager, pizzaDao);

        listData.addAll(pizzaService.getAllPizza());

        pizzaListView = new ListView<Pizza>(listData);

        pizzaListView.setCellFactory(listCellFactory());

        pizzaListPane.getChildren().add(pizzaListView);
        pizzaListView.setPrefWidth(500);
        pizzaListView.setPrefHeight(500 - menuBar.getPrefHeight());

        pizzaNameColumn.setCellValueFactory(new PropertyValueFactory<Pizza, String>("name"));
        pizzaPriceColumn.setCellValueFactory(new PropertyValueFactory<Pizza, Integer>("price"));
    }

    /**
     * Összeállítja a pizzák listáját tartalmazó lista nézetet.
     *
     * @return A lista callback-je
     */
    private Callback<ListView<Pizza>, ListCell<Pizza>> listCellFactory() {
        return new Callback<ListView<Pizza>, ListCell<Pizza>>() {

            @Override
            public ListCell<Pizza> call(ListView<Pizza> param) {
                return new ListCell<Pizza>() {

                    @Override
                    protected void updateItem(Pizza pizza, boolean bln) {
                        super.updateItem(pizza, bln);

                        if (pizza != null) {
                            Image image = new Image(pizza.getImagePath(), 120, 100, true, true);

                            Text name = new Text(pizza.getName());
                            name.setFont(Font.font("Verdana", 18));

                            Text description = new Text(pizza.getDescription());
                            description.setWrappingWidth(190);
                            description.setFont(Font.font("Verdana", 12));

                            VBox descriptionBox = new VBox(name, description);
                            descriptionBox.setAlignment(Pos.CENTER_LEFT);

                            Button addToCart = new Button("Kosárba");

                            addToCart.setOnMouseClicked(new EventHandler<MouseEvent>() {

                                @Override
                                public void handle(MouseEvent event) {
                                    cartService.add(getItem());
                                    cartTable.getItems().add(getItem());

                                    logger.info("A pizza has been added to the cart: " + getItem().getName());

                                    updateLabels();
                                    payButton.setDisable(false);
                                }
                            });

                            VBox addToCartBox = new VBox(addToCart);
                            addToCartBox.setAlignment(Pos.CENTER);

                            VBox priceBox = new VBox(new Text(String.format("%d Ft", pizza.getPrice())));
                            priceBox.setAlignment(Pos.CENTER);

                            HBox hbox = new HBox(new ImageView(image), descriptionBox, priceBox, addToCartBox);

                            hbox.setSpacing(10);

                            setGraphic(hbox);
                        }
                    }

                };
            }
        };
    }

    /**
     * Létrehozza és inicializálja a főablakot.
     *
     * @param primaryStage A szülő stage
     */
    public static void loadView(Stage primaryStage) {
        stage = primaryStage;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainViewController.class.getResource("/views/MainView.fxml"));

        try {
            BorderPane rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error(
                "IO exception has occurred in " + MainViewController.class + " loading main panel:" + e.getMessage()
            );
        }
    }

    /**
     * Betölti a bejelentkezési ablakot.
     */
    @FXML
    private void loginMenuHandler() {
        LoginViewController.loadView(menuBar.getScene().getWindow(), this);
    }

    /**
     * Betölti a regisztrációs ablakkot.
     */
    @FXML
    private void registerMenuHandler() {
        RegisterViewController.loadView(menuBar.getScene().getWindow(), this);
    }

    /**
     * Betölti a bejelentkezett felhasználó profil adatlapját.
     */
    @FXML
    private void profileMenuHandler() {
        ProfileViewController.loadView(menuBar.getScene().getWindow(), this);
    }

    /**
     * Betölti a bejelentkezett felhasználó korábbi rendeléseit.
     */
    @FXML
    private void ordersMenuItemHandler() {
        OrdersViewController.loadView(menuBar.getScene().getWindow(), this);
    }

    /**
     * Betölti a fizetési felüetet.
     */
    @FXML
    private void payButtonHandler() {
        if (currentUser == null) {
            LoginViewController.loadView(menuBar.getScene().getWindow(), this);
        } else {
            PayViewController.loadView(menuBar.getScene().getWindow(), this);
        }
    }

    /**
     * Törli a kosár tartalmát.
     */
    @FXML
    private void clearCartButtonHandler() {
        clearCart();
        logger.info("The cart has been emptied.");
    }


    CartService getCart() {
        return cartService;
    }

    /**
     * Visszaadja a kosár tartalmát.
     *
     * @return A kosárban levő pizzákat tartalmazó lista
     */
    List<Pizza> getCartContent() {
        return cartService.getCart();
    }

    /**
     * Kijelentkezteti a felhasználót.
     */
    @FXML
    private void logoutMenuHandler() {
        switchMenuLoggedOut();
    }

    /**
     * Beállítja a menu itemek láthatóságát a bejelentkezett felhasználóhoz.
     */
    void switchMenuLoggedIn() {
        profileMenuItem.setVisible(true);
        logoutMenuItem.setVisible(true);
        ordersMenuItem.setVisible(true);

        loginMenuItem.setVisible(false);
        registerMenuItem.setVisible(false);
    }

    /**
     * Beállítja a menu itemek láthatóságát a be nem jelentkezett felhasználóhoz.
     */
    void switchMenuLoggedOut() {
        profileMenuItem.setVisible(false);
        logoutMenuItem.setVisible(false);
        ordersMenuItem.setVisible(false);

        loginMenuItem.setVisible(true);
        registerMenuItem.setVisible(true);
    }

    /**
     * Törli a kosár tartalmát.
     */
    void clearCart() {
        cartService.clear();
        cartTable.getItems().clear();

        updateLabels();
        payButton.setDisable(true);
    }

    /**
     * Frissíti a végösszeg és kedvezmény címkéket.
     */
    private void updateLabels() {
        updateCartSummaryAttribute();
        updateCartDiscountAttribute();
    }

    /**
     * Frissíti a végösszeg címkét.
     */
    private void updateCartSummaryAttribute() {
        int cartSummary = cartService.getTotal();
        cartSumAttribute.setText(String.format("%d Ft", cartSummary));
    }

    /**
     * Frissíti a kedvezmény címkét.
     */
    private void updateCartDiscountAttribute() {
        int cartDiscount = cartService.getDiscount().getAmount();
        cartDiscountAttribute.setText(String.format("%d Ft", cartDiscount));
    }

    /**
     * Kilép a programból.
     */
    @FXML
    private void exitMenuHandler() {
        System.exit(1);
    }

    /**
     * Visszaadja a bejelentkezett felhasználót.
     *
     * @return A bejelentkezett felhasználó
     */
    User getCurrentUser() {
        return currentUser;
    }

    /**
     * Beállítja a bejelentkezett felhasználót.
     *
     * @param currentUser A bejelentkezett felhasználó
     */
    void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
