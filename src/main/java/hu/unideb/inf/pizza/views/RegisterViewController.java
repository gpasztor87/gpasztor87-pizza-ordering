package hu.unideb.inf.pizza.views;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * A regisztrációt megvalósító felület üzleti logikája.
 */
public class RegisterViewController {

    private Stage stage;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private void registerButtonHandler() {

    }

    @FXML
    private void cancelButtonHandler() {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
