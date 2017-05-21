package hu.unideb.inf.pizza.views;

import hu.unideb.inf.pizza.dao.UserDaoImpl;
import hu.unideb.inf.pizza.managers.JpaConnectionManager;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.UserServiceImpl;
import hu.unideb.inf.pizza.services.interfaces.UserService;
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

import javax.validation.ValidationException;
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
     * A fő ablak stage-e.
     */
    private static MainViewController mainViewController;

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

    /**
     * A {@link UserService} interfész egy implementációjának példánya.
     */
    private UserService userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageLabel.setText("");

        JpaConnectionManager connectionManager = new JpaConnectionManager();
        UserDaoImpl userDao = new UserDaoImpl(connectionManager.getEntityManager());

        userService = new UserServiceImpl(connectionManager, userDao);
    }

    /**
     * Létrehozza és inicializálja a regisztrációs ablakot.
     *
     * @param window     A szülő ablak
     * @param controller A fő ablak vezérlőjének egy példánya
     */
    static void loadView(Window window, MainViewController controller) {
        mainViewController = controller;

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

    /**
     * Lekezeli a regisztráció gomb eseményét.
     * Beregisztrálja a felhasználót.
     */
    @FXML
    private void registerButtonHandler() {
        if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || phoneField.getText().isEmpty() || addressField.getText().isEmpty()) {
            messageLabel.setText("Minden mező kitöltése kötelező.");
        } else {
            if (userService.getUserByEmail(emailField.getText()) != null) {
                messageLabel.setText("Ezzel az email címmel már létezik felhasználó.");
            } else {
                User user = new User();

                user.setName(nameField.getText());
                user.setEmail(emailField.getText());
                user.setAddress(addressField.getText());
                user.setPhone(phoneField.getText());
                user.setPassword(passwordField.getText());

                try {
                    userService.createUser(user);

                    mainViewController.switchMenuLoggedIn();
                    mainViewController.setCurrentUser(user);

                    logger.info("A user has been registered with this email: " + emailField.getText());
                    stage.close();
                } catch (ValidationException e) {
                    messageLabel.setText(e.getMessage());
                    logger.error(e.getMessage());
                }
            }
        }
    }

    /**
     * Lekezeli a vissza gomb eseményét.
     */
    @FXML
    private void cancelButtonHandler() {
        stage.close();
    }
}
