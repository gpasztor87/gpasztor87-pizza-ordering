package hu.unideb.inf.pizza;

import hu.unideb.inf.pizza.views.LoginViewController;
import hu.unideb.inf.pizza.views.RegisterViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * A belépési pontot tartalmazó osztály.
 */
public final class Main extends Application {

    private BorderPane rootView;

    /**
     * A fő stage egy példánya.
     */
    private Stage primaryStage;

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

        launch(args);
    }

    /**
     * A JavaFX belépési pontja.
     *
     * @param primaryStage Fő stage
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("PizzaRendelő");

        loadRootView();
    }

    private void loadRootView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/RootView.fxml"));

        try {
            rootView = (BorderPane) loader.load();

            Scene scene = new Scene(rootView);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadLoginView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/LoginView.fxml"));

        try {
            AnchorPane loginView = loader.load();

            LoginViewController controller = loader.getController();

            Stage dialog = new Stage();
            dialog.setTitle("Belépés");
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(primaryStage);

            controller.setStage(dialog);

            Scene scene = new Scene(loginView);
            dialog.setScene(scene);

            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRegisterView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/RegisterView.fxml"));

        try {
            AnchorPane registerView = loader.load();

            RegisterViewController controller = loader.getController();

            Stage dialog = new Stage();
            dialog.setTitle("Regisztráció");
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(primaryStage);

            controller.setStage(dialog);

            Scene scene = new Scene(registerView);
            dialog.setScene(scene);

            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
