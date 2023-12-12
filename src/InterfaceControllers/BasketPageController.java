package InterfaceControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.util.Objects;

import static InterfaceControllers.StartPoint.loadNewStage;

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
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//mainPage.fxml")));

        try {
            loadNewStage(event, fxmlLoader);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки FXMLLoader");
        }

        MainPageController mainPageController = fxmlLoader.getController();
        mainPageController.init(null);
    }



    @FXML
    void toNextPage(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//countPage.fxml")));
        if(basketIsEmpty()) {
            return;
        }

        try {
            loadNewStage(event, fxmlLoader);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки FXMLLoader");
        }

        CountPageController countPageController = fxmlLoader.getController();
        countPageController.init();
    }

    private boolean basketIsEmpty() {
        return false;
    }

    public void init() {
    }
}
