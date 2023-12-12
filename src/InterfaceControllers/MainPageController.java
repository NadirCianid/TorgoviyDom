package InterfaceControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.util.Objects;

import static InterfaceControllers.StartPoint.loadNewStage;

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
    void toBasket(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//basketPage.fxml")));
        if(basketIsEmpty()) {
            return;
        }

        try {
            loadNewStage(event, fxmlLoader);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки FXMLLoader");
        }


        BasketPageController basketPageController = fxmlLoader.getController();
        basketPageController.init();
    }

    private boolean basketIsEmpty() {
        return false;
    }

    public void init() {
    }
}
