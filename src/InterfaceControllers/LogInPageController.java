package InterfaceControllers;

import backend.service.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

import static InterfaceControllers.StartPoint.*;

public class LogInPageController {
    @FXML
    private TextField agreementNumberTF;

    @FXML
    private TextField fioTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField phoneNumberTF;

    @FXML
    private CheckBox importantCheckBox;

    @FXML
    private Button nextPageButton;

    @FXML
    void acceptEverything(ActionEvent event) {
        if(importantCheckBox.isSelected()) {
            nextPageButton.setDisable(false);
        } else {
            nextPageButton.setDisable(true);
        }
    }

    @FXML
    void nextPage(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//mainPage.fxml")));

        String fio = fioTF.getText();
        String agreementNumber = agreementNumberTF.getText();
        String phoneNumber = phoneNumberTF.getText();
        String email = emailTF.getText();

        if(!importantCheckBox.isSelected()) {
            return;
        }

        ClientService clientService = applicationContext.getClientService();
        currentClient = clientService.saveClientData(fio, agreementNumber, phoneNumber, email);

        if(currentClient == null) {
            openSecondWindow("Не удалось сохранить ваши данные. Попробуйте еще раз позже.",
                    "Ошибка сервера.");

            return;
        }
        System.out.println(currentClient);

        try {
            loadNewStage(event, fxmlLoader);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки FXMLLoader");
        }

        MainPageController mainPageController = fxmlLoader.getController();
        mainPageController.init(null);
    }
}
