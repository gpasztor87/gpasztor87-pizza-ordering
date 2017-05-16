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
 * A bejelentkezést biztosító felhasználói felületet vezérlő osztály.
 */
public class LoginViewController implements Initializable {

    /**
     * A logger egy példánya.
     */
    private static Logger logger = LoggerFactory.getLogger(LoginViewController.class);

    /**
     * A bejelentkezési ablak stage-e.
     */
    private static Stage stage;

    /**
     * A fő ablak stage-e.
     */
    private static MainViewController mainViewController;

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

    /**
     * A {@link UserServiceInterface} interfész egy implementációjának példánya.
     */
    private UserServiceInterface userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageLabel.setText("");

        JpaConnectionManager connectionManager = new JpaConnectionManager("production");
        UserDao userDao = new UserDaoImpl(connectionManager.getEntityManager());

        userService = new UserService(connectionManager, userDao);
    }

    /**
     * Létrehozza és inicializálja a bejelentkezési ablakot.
     *
     * @param window     A szülő ablak
     * @param controller A fő ablak vezérlőjének egy példánya
     */
    static void loadView(Window window, MainViewController controller) {
        mainViewController = controller;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainViewController.class.getResource("/views/LoginView.fxml"));

        try {
            AnchorPane loginPane = loader.load();

            Scene scene = new Scene(loginPane);

            stage = new Stage();
            stage.setTitle("Belépés");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(window);

            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            logger.error(
                    "IO exception has occurred in " + LoginViewController.class + " loading loginDialog: " + e.getMessage()
            );
        }
    }

    /**
     * Lekezeli a belépés gomb eseményét.
     * Bejelentkezteti a felhasználót.
     */
    @FXML
    private void loginButtonHandler() {
        if (emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            messageLabel.setText("Az email cím és jelszó kitöltése kötelező.");
        } else {
            if (!authenticate(emailField.getText(), passwordField.getText())) {
                messageLabel.setText("Rossz email cím vagy jelszó.");
            } else {
                User user = userService.getUserByEmail(emailField.getText());
                mainViewController.setCurrentUser(user);
                mainViewController.switchMenuLoggedIn();

                logger.info("User has logged in with the following email address: " + emailField.getText());
                stage.close();
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

    /**
     * Hitelesíti a felhasználót.
     * Sikeres hitelesítés esetén igaz értékkel tér vissza.
     *
     * @param email Email címe
     * @param password Jelszó
     * @return Igaz, ha sikeres a hitelesítés
     */
    private boolean authenticate(String email, String password) {
        return userService.validateUser(email, password);
    }
}
