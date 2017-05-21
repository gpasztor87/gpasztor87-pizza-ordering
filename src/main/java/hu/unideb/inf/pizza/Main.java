package hu.unideb.inf.pizza;

import hu.unideb.inf.pizza.managers.DiscountManager;
import hu.unideb.inf.pizza.models.Discount;
import hu.unideb.inf.pizza.views.MainViewController;

import javafx.application.Application;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A belépési pontot tartalmazó osztály.
 */
public class Main extends Application {

    /**
     * A naplózó egy példánya.
     */
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Az alkalmazás belépési pontja.
     *
     * @param args Az alkalmazás paraméterei
     */
    public static void main(String[] args) {
        logger.info("The application is running.");

        DiscountManager discountManager = DiscountManager.getInstance();

        discountManager.add(new Discount(500, 2000));
        discountManager.add(new Discount(1000, 5000));

        launch(args);
    }

    /**
     * A JavaFX belépési pontja.
     *
     * @param primaryStage Fő stage
     */
    public void start(Stage primaryStage) {
        MainViewController.loadView(primaryStage);
    }
}
