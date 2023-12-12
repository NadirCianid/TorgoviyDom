package InterfaceControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LogInPageController {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    @FXML
    private TextField agreem;

    @FXML
    private TextField fioTF;

    @FXML
    private CheckBox importantCheckBox;

    @FXML
    private Button nextPageButton;

    @FXML
    void agreementTF(ActionEvent event) {

    }

    @FXML
    void nextPage(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//mainPage.fxml")));


        if(!collectData()) {

            return;
        }

        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Ошибка загрузки FXMLLoader");
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        MainPageController mainPageController = fxmlLoader.getController();
        mainPageController.init();
    }

    private boolean collectData() {

        return true;
    }

}
