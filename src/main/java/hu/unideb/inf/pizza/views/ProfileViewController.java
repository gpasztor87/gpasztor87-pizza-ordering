package hu.unideb.inf.pizza.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * A felhasználói adatokat módosító felhasználói felületet vezérő osztály.
 */
public class ProfileViewController implements Initializable {

    /**
     * A logger egy példánya.
     */
    private static Logger logger = LoggerFactory.getLogger(ProfileViewController.class);

    /**
     * A felhasználói adatok ablak stage-e.
     */
    private static Stage stage;

    /**
     * Email beviteli mező.
     */
    @FXML
    private TextField emailField;

    /**
     * Jelszó beviteli mező.
     */
    @FXML
    private TextField passwordField;

    /**
     * Hibaüzeneteket tartalmazó címke.
     */
    @FXML
    private Label messageLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageLabel.setText("");
    }

    static void loadView(Window window) {

    }
}
