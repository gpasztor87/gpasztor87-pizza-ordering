package hu.unideb.inf.pizza.views;

import hu.unideb.inf.pizza.dao.UserDaoImpl;
import hu.unideb.inf.pizza.dao.interfaces.UserDao;
import hu.unideb.inf.pizza.managers.JpaConnectionManager;
import hu.unideb.inf.pizza.models.User;
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
     * Jelszó beviteli mező.
     */
    @FXML
    private TextField passwordField;

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
     * Hibaüzeneteket tartalmazó címke.
     */
    @FXML
    private Label messageLabel;

    /**
     * A bejelentkezett felhasználó.
     */
    private User currentUser;

    /**
     * A {@link UserServiceInterface} interfész egy implementációjának példánya.
     */
    private UserServiceInterface userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageLabel.setText("");
        emailField.setEditable(false);

        currentUser = mainViewController.getCurrentUser();

        nameField.setText(currentUser.getName());
        emailField.setText(currentUser.getEmail());
        addressField.setText(currentUser.getAddress());
        phoneField.setText(currentUser.getPhone());

        JpaConnectionManager connectionManager = new JpaConnectionManager("production");
        UserDao userDao = new UserDaoImpl(connectionManager.getEntityManager());

        userService = new UserService(connectionManager, userDao);
    }

    /**
     * Létrehozza és inicializálja a profil ablakot.
     *
     * @param window     A szülő ablak
     * @param controller A fő ablak vezérlőjének egy példánya
     */
    static void loadView(Window window, MainViewController controller) {
        mainViewController = controller;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainViewController.class.getResource("/views/ProfileView.fxml"));

        try {
            AnchorPane registerPane = loader.load();

            Scene scene = new Scene(registerPane);

            stage = new Stage();
            stage.setTitle("Adatok módosítása");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(window);
            stage.setScene(scene);

            stage.showAndWait();
        } catch (IOException e) {
            logger.error(
                    "IO exception has occurred in " + ProfileViewController.class + " loading profileDialog: " + e.getMessage()
            );
        }
    }

    /**
     * Lekezeli a mentés gomb eseményét.
     * Módosítja a felhasználó adatait.
     */
    @FXML
    private void updateButtonHandler() {
        if (nameField.getText().isEmpty() || phoneField.getText().isEmpty() || addressField.getText().isEmpty()) {
            messageLabel.setText("Minden mező kitöltése kötelező.");
        } else {
            currentUser.setName(nameField.getText());
            currentUser.setPhone(phoneField.getText());
            currentUser.setAddress(addressField.getText());
            currentUser.setPassword(passwordField.getText());

            userService.updateUser(currentUser);

            logger.info("The current user has been updated.");

            stage.close();
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
