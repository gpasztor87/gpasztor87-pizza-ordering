package hu.unideb.inf.pizza.views;

import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    static void loadView(Window window) {

    }
}
