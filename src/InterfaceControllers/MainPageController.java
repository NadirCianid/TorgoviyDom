package InterfaceControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainPageController {

    @FXML
    private Label busketConditionLabel;

    @FXML
    private ChoiceBox<?> catalogCB;

    @FXML
    private TableColumn<?, ?> priceTC;

    @FXML
    private ChoiceBox<?> productAmountCB;

    @FXML
    private TableColumn<?, ?> productNameTC;

    @FXML
    private TableView<?> productsTableView;

    @FXML
    private Button toBusketButton;

    @FXML
    void addToBusket(ActionEvent event) {

    }

    @FXML
    void toBusket(ActionEvent event) {

    }

    public void init() {
    }
}
