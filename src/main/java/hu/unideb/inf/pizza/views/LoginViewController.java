package hu.unideb.inf.pizza.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * A bejelentkezést biztosító felhasználói felületet vezérlő osztály.
 */
public class LoginViewController implements Initializable {

    /**
     * A bejelentkezési ablak stage-e.
     */
    private Stage stage;

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

    protected static void loadView(Stage stage) {

    }

    @FXML
    private void loginButtonHandler() {

    }

    @FXML
    private void cancelButtonHandler() {

    }

}
