package InterfaceControllers;

import backend.Client;
import backend.CompaniesController;
import backend.WarehouseController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartPoint extends Application {
    public static Client currentClient;
    public static CompaniesController companiesController = new CompaniesController();
    public static WarehouseController warehouseController = new WarehouseController();

    @Override
    public void start(Stage stage) throws IOException {
        //Получение FXMLLoader объекта  для перехода к новой сцене и доступа к котроллеру этой сцены
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//logInPage.fxml")));

        prepareNewStage(stage, fxmlLoader);

        companiesController.fillAgreementList();
        warehouseController.fillWarehouse();

        LogInPageController logInPageController = fxmlLoader.getController();
    }

    private static void prepareNewStage(Stage stage, FXMLLoader fxmlLoader) throws IOException {
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void loadNewStage(ActionEvent event, FXMLLoader fxmlLoader) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        prepareNewStage(stage, fxmlLoader);
    }

    public static void main(String[] args) {
        launch();
    }
}
