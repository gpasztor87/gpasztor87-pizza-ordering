package hu.unideb.inf.pizza.views;

import hu.unideb.inf.pizza.dao.OrderDaoImpl;
import hu.unideb.inf.pizza.dao.interfaces.OrderDao;
import hu.unideb.inf.pizza.managers.JpaConnectionManager;
import hu.unideb.inf.pizza.models.Order;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.OrderServiceImpl;
import hu.unideb.inf.pizza.services.interfaces.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * A felhasználó korábbi rendeléseit listázó felhasználói felületet vezérlő osztály.
 */
public class OrdersViewController implements Initializable {

    /**
     * A logger egy példánya.
     */
    private static Logger logger = LoggerFactory.getLogger(OrdersViewController.class);

    /**
     * A fizetési ablak stage-e.
     */
    private static Stage stage;

    /**
     * A fő ablak stage-e.
     */
    private static MainViewController mainViewController;

    /**
     * A rendeléseket tartalmazó táblázat.
     */
    @FXML
    private TableView<Order> orderTableView;

    /**
     * A rendelések időpontját tartalmazó oszlop.
     */
    @FXML
    private TableColumn<Order, Date> orderDateTableColumn;

    /**
     * A rendelések végösszegét tartalmazó oszlop.
     */
    @FXML
    private TableColumn<Order, Integer> orderPriceTableColumn;

    /**
     * A rendelések címét tartalmazó oszlop.
     */
    @FXML
    private TableColumn<Order, String> orderAddressTableColumn;

    /**
     * A bejelentkezett felhasználó.
     */
    private User currentUser;

    /**
     * A felhasználó rendeléseit tartalmazó lista.
     */
    private ObservableList<Order> userOrders = FXCollections.observableArrayList();

    /**
     * A {@link OrderService} interfész egy implementációjának példánya.
     */
    private OrderService orderService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentUser = mainViewController.getCurrentUser();

        JpaConnectionManager connectionManager = new JpaConnectionManager("production");
        OrderDao orderDao = new OrderDaoImpl(connectionManager.getEntityManager());

        orderService = new OrderServiceImpl(connectionManager, orderDao);

        userOrders.addAll(orderService.getUserOrders(currentUser));
        orderTableView.setItems(userOrders);

        orderDateTableColumn.setCellValueFactory(new PropertyValueFactory<Order, Date>("orderDate"));
        orderPriceTableColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("totalPrice"));
        orderAddressTableColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("address"));
    }

    /**
     * Létrehozza és inicializálja a rendeléseim ablakot.
     *
     * @param window     A szülő ablak
     * @param controller A fő ablak vezérlőjének egy példánya
     */
    static void loadView(Window window, MainViewController controller) {
        mainViewController = controller;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainViewController.class.getResource("/views/OrdersView.fxml"));

        try {
            AnchorPane loginPane = loader.load();

            Scene scene = new Scene(loginPane);

            stage = new Stage();
            stage.setTitle("Rendeléseim");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(window);

            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            logger.error(
                    "IO exception has occurred in " + OrdersViewController.class + " loading ordersDialog: " + e.getMessage()
            );
        }
    }
}
