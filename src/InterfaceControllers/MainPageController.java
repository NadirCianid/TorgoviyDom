package InterfaceControllers;

import backend.Category;
import backend.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

import static InterfaceControllers.StartPoint.*;

public class MainPageController {
    @FXML
    private TableColumn<Product, Integer> amountTC;

    @FXML
    private Label basketConditionLabel;

    @FXML
    private ChoiceBox<Category> catalogCB;

    @FXML
    private TableColumn<Product, String> nameTC;

    @FXML
    private TableColumn<Product, Integer> priceTC;

    @FXML
    private ChoiceBox<Integer> productAmountCB;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private Button toBasketButton;
    private Category categoryToBeDisplayed;

    @FXML
    void addToBasket(ActionEvent event) {
        Integer amountToBeAdded = productAmountCB.getValue();
        Product productToBeAdded = productsTableView.getSelectionModel().getSelectedItem();
        if(amountToBeAdded == null || productToBeAdded == null) {
            return;
        }

        boolean addingResult = currentClient.basket.addProductInBasket(productToBeAdded, amountToBeAdded);
        updatePage(event, categoryToBeDisplayed);
    }

    @FXML
    void toBasket(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//basketPage.fxml")));
        if(currentClient.basket.basketIsEmpty()) {
            StartPoint.openSecondWindow("Ваша корзина пуста. Добавьте хотя бы один товар, чтобы перейти в корзину.",
                    "You shall not pass!");
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

    private void updatePage(ActionEvent event, Category categoryToBeDisplayed) {
        //Получение FXMLLoader объекта  для перехода к новой сцене и доступа к котроллеру этой сцены
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//mainPage.fxml")));

        try {
            loadNewStage(event, fxmlLoader);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки FXMLLoader");
        }

        MainPageController mainPageController = fxmlLoader.getController();
        mainPageController.init(categoryToBeDisplayed);
        mainPageController.categoryToBeDisplayed = categoryToBeDisplayed;
    }

    @FXML
    void setAmountAddingVariants(MouseEvent event) {
        Product product = productsTableView.getSelectionModel().getSelectedItem();

        if(product == null) {
            return;
        }

        productAmountCB.getItems().clear();
        productAmountCB.setItems(product.getPossibleAmounts());
    }

    public void init(Category categoryToBeDisplayed) {
        fillProductsTableView(categoryToBeDisplayed);
        setCatalogCB();
        setBasketInfo();
    }

    private void setBasketInfo() {
        basketConditionLabel.setText(currentClient.basket.getSelectedPositions().size()
                + " поз. на сумму "
                + currentClient.basket.getTotalSum()
                + " Р");
    }

    private void setCatalogCB() {
        catalogCB.setItems(Category.getCategories());
        // Регистрация обработчика событий при выборе элемента в ChoiceBox
        catalogCB.setOnAction(event ->  updatePage(event, catalogCB.getValue()));
    }

    private void fillProductsTableView(Category categoryToBeDisplayed) {
        productsTableView.getItems().clear();

        //Настройка соответствия столбцов и полей хранимой сущности.
        //Таблица будет хранить товары (объекты Product).
        nameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountTC.setCellValueFactory(new PropertyValueFactory<>("amount"));
        priceTC.setCellValueFactory(new PropertyValueFactory<>("price"));

        if(categoryToBeDisplayed == null) {
            productsTableView.setItems(warehouseController.getProducts());
            return;
        }

        productsTableView.setItems(warehouseController.filter(categoryToBeDisplayed));
    }
}
