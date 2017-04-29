package hu.unideb.inf.pizza.views;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * A belépést megvalósító felület üzleti logikája.
 */
public class LoginViewController {

    private Stage stage;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private void loginButtonHandler() {

    }

    @FXML
    private void cancelButtonHandler() {
        stage.close();
    }

    public final void setStage(Stage stage) {
        this.stage = stage;
    }
}
