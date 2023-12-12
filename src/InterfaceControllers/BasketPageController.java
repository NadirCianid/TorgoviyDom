package InterfaceControllers;

import backend.Category;
import backend.Position;
import backend.WarehouseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Objects;

import static InterfaceControllers.StartPoint.*;

public class BasketPageController {
    @FXML
    private TableColumn<Position, Integer> amountTC;

    @FXML
    private Label clientCompanyLabel;

    @FXML
    private Label clientNameLabel;

    @FXML
    private TableColumn<Position, Integer> costTC;

    @FXML
    private TableColumn<Position, Integer> priceTC;

    @FXML
    private TableColumn<Position, String> productNameTC;

    @FXML
    private TableView<Position> positionsTableView;

    @FXML
    private Label totalLabel;

    @FXML
    void deleteFromBasket(ActionEvent event) {
        Position selectedPosition = positionsTableView.getSelectionModel().getSelectedItem();
        if(selectedPosition == null) {
            StartPoint.openSecondWindow("Вы не выбрали ни одной позиции.",
                    "Необходимо выбрать позицию.");
            return;
        }

        currentClient.basket.dropPosition(selectedPosition);
        updatePage(event);
    }

    @FXML
    void oneLess(ActionEvent event) {
        Position selectedPosition = positionsTableView.getSelectionModel().getSelectedItem();
        if(selectedPosition == null) {
            StartPoint.openSecondWindow("Вы не выбрали ни одной позиции.",
                    "Необходимо выбрать позицию.");
            return;
        }

        selectedPosition.removeFromBasket(1);

        updatePage(event);
    }

    @FXML
    void oneMore(ActionEvent event) {
        Position selectedPosition = positionsTableView.getSelectionModel().getSelectedItem();
        if(selectedPosition == null) {
            StartPoint.openSecondWindow("Вы не выбрали ни одной позиции.",
                    "Необходимо выбрать позицию.");
            return;
        }

        selectedPosition.addInBasket(1);

        updatePage(event);
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
        if(currentClient.basket.basketIsEmpty()) {
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

    public void init() {
        clientNameLabel.setText(currentClient.getFio());
        clientCompanyLabel.setText(currentClient.getAgreement().getCompanyName());

        fillBasketTableView();

        totalLabel.setText("Итого: " +currentClient.basket.getTotalSum() + " Р");
    }

    private void fillBasketTableView() {
        positionsTableView.getItems().clear();

        //Настройка соответствия столбцов и полей хранимой сущности.
        //Таблица будет хранить позиции (объекты Position).
        productNameTC.setCellValueFactory(new PropertyValueFactory<>("productName"));
        amountTC.setCellValueFactory(new PropertyValueFactory<>("amountInBasket"));
        priceTC.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        costTC.setCellValueFactory(new PropertyValueFactory<>("positionCost"));

        positionsTableView.setItems(currentClient.basket.getSelectedPositions());
    }

    private void updatePage(ActionEvent event) {
        //Получение FXMLLoader объекта  для перехода к новой сцене и доступа к котроллеру этой сцены
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//basketPage.fxml")));

        try {
            loadNewStage(event, fxmlLoader);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки FXMLLoader");
        }

        BasketPageController basketPageController = fxmlLoader.getController();
        basketPageController.init();
    }
}
