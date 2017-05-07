package hu.unideb.inf.pizza.views;

import hu.unideb.inf.pizza.services.UserService;
import hu.unideb.inf.pizza.services.interfaces.UserServiceInterface;
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
 * A regisztrációt biztosító felhasználói felületet vezérlő osztály.
 */
public class RegisterViewController implements Initializable {

    /**
     * A logger egy példánya.
     */
    private static Logger logger = LoggerFactory.getLogger(RegisterViewController.class);

    /**
     * A regisztrációs ablak stage-e.
     */
    private static Stage stage;

    /**
     * Név beviteli mező.
     */
    @FXML
    private TextField nameField;

    /**
     * Email beviteli mező.
     */
    @FXML
    private TextField emailField;

    /**
     * Telefonszám beviteli mező.
     */
    @FXML
    private TextField phoneField;

    /**
     * Cím beviteli mező.
     */
    @FXML
    private TextField addressField;

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

    private UserServiceInterface userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageLabel.setText("");

        userService = new UserService();
    }

    static void loadView(Window window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainViewController.class.getResource("/views/RegisterView.fxml"));

        try {
            AnchorPane registerPane = loader.load();

            Scene scene = new Scene(registerPane);

            stage = new Stage();
            stage.setTitle("Regisztráció");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(window);
            stage.setScene(scene);

            stage.showAndWait();
        } catch (IOException e) {
            logger.error(
                "IO exception has occurred in " + RegisterViewController.class + " loading registerDialog: " + e.getMessage()
            );
        }
    }

    @FXML
    private void registerButtonHandler() {
        if (userService.getUserByEmail(emailField.getText()) != null) {
            messageLabel.setText("Ezzel az email címmel már létezik felhasználó.");

        } else {
            userService.save(
                    nameField.getText(),
                    emailField.getText(),
                    passwordField.getText(),
                    addressField.getText(),
                    phoneField.getText()
            );

            logger.info("A user has been registered with this email: " + emailField.getText());
            stage.close();
        }
    }

    @FXML
    private void cancelButtonHandler() {
        stage.close();
    }
}
