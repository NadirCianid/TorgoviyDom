package InterfaceControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

import static InterfaceControllers.StartPoint.loadNewStage;

public class LogInPageController {
    @FXML
    private TextField agreementTF;

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
            loadNewStage(event, fxmlLoader);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки FXMLLoader");
        }


        MainPageController mainPageController = fxmlLoader.getController();
        mainPageController.init();
    }

    private boolean collectData() {

        return true;
    }

}
