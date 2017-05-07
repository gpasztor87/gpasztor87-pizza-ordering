package hu.unideb.inf.pizza.views;

import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.OrderService;
import hu.unideb.inf.pizza.services.interfaces.OrderServiceInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A fizetést biztosító felhasználói felületet vezérlő osztály.
 */
public class PayViewController implements Initializable {

    /**
     * A logger egy példánya.
     */
    private static Logger logger = LoggerFactory.getLogger(PayViewController.class);

    /**
     * A fizetési ablak stage-e.
     */
    private static Stage stage;

    /**
     * A fő ablak stage-e.
     */
    private static MainViewController mainViewController;

    /**
     * Cím beviteli mező.
     */
    @FXML
    private TextField addressField;

    /**
     * Üzenet beviteli mező.
     */
    @FXML
    private TextField messageField;

    /**
     * A végösszeget megjelenítő címke.
     */
    @FXML
    private Label totalPriceLabel;

    /**
     * A rendelés végösszege.
     */
    private int totalPrice;

    /**
     * A bejelentkezett felhasználó.
     */
    private User currentUser;

    /**
     * A {@link OrderServiceInterface} interfész egy implementációjának példánya.
     */
    private OrderServiceInterface orderService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentUser = mainViewController.getCurrentUser();

        addressField.setText(currentUser.getAddress());

        orderService = new OrderService();
    }

    static void loadView(Window window, MainViewController controller) {
        mainViewController = controller;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainViewController.class.getResource("/views/PayView.fxml"));

        try {
            AnchorPane loginPane = loader.load();

            Scene scene = new Scene(loginPane);

            stage = new Stage();
            stage.setTitle("Fizetés");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(window);

            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            logger.error(
                    "IO exception has occurred in " + PayViewController.class + " loading payDialog: " + e.getMessage()
            );
        }
    }

    @FXML
    private void payButtonHandler() {
        orderService.create(
                currentUser,
                addressField.getText(),
                messageField.getText(),
                mainViewController.getCartSummary(),
                mainViewController.getCart()
        );

        mainViewController.clearCart();

        stage.close();
    }

    @FXML
    private void cancelButtonHandler() {
        stage.close();
    }

}
