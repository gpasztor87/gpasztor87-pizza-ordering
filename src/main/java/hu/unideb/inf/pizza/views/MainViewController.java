package hu.unideb.inf.pizza.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
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
     * Kilépés menüpont.
     */
    @FXML
    private MenuItem exitMenuItem;

    /**
     * Rendeléseim menüpont.
     */
    @FXML
    private MenuItem ordersMenuItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        profileMenuItem.setVisible(false);
        logoutMenuItem.setVisible(false);
    }

    /**
     * Létrehozza és inicializálja a főablakot.
     *
     * @param stage A szülő stage
     */
    public static void loadView(Stage stage) {
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainViewController.class.getResource("/views/LoginView.fxml"));

        try {
            AnchorPane  loginPane = loader.load();

            Scene scene = new Scene(loginPane);


        } catch (IOException e) {
            logger.error(
                "IO exception has occurred in " + MainViewController.class + " loading loginDialog: " + e.getMessage()
            );
        }
    }

    /**
     * Betölti a regisztrációs ablakkot.
     */
    @FXML
    private void registerMenuHandler() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainViewController.class.getResource("/views/RegisterView.fxml"));

        try {
            AnchorPane registerPane = loader.load();

            Scene scene = new Scene(registerPane);

        } catch (IOException e) {
            logger.error(
                "IO exception has occurred in " + MainViewController.class + " loading registerDialog: " + e.getMessage()
            );
        }
    }

    /**
     * Betölti a bejelentkezett felhasználó profil adatlapját.
     */
    @FXML
    private void profileMenuHandler() {
        FXMLLoader loader = new FXMLLoader();

        //
    }

    /**
     * Kijelentkezteti a felhasználót.
     */
    @FXML
    private void logoutMenuHandler() {

    }

    /**
     * Kilép a programból.
     */
    @FXML
    private void exitMenuHandler() {
        stage.close();
    }
}
