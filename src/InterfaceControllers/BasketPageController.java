package InterfaceControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BasketPageController {

    @FXML
    private TableColumn<?, ?> amountTC;

    @FXML
    private Label clientCompanyLabel;

    @FXML
    private Label clientNameLabel;

    @FXML
    private TableColumn<?, ?> costTC;

    @FXML
    private TableColumn<?, ?> priceTC;

    @FXML
    private TableColumn<?, ?> productNameTC;

    @FXML
    private TableView<?> productsTableView;

    @FXML
    private Label totalLabel;

    @FXML
    void deleteFromBusket(ActionEvent event) {

    }

    @FXML
    void oneLess(ActionEvent event) {

    }

    @FXML
    void oneMore(ActionEvent event) {

    }

    @FXML
    void toMainPage(ActionEvent event) {

    }

    @FXML
    void toNextPage(ActionEvent event) {

    }

}
