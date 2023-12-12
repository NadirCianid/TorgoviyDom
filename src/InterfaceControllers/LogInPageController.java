package InterfaceControllers;

import backend.Agreement;
import backend.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

import static InterfaceControllers.StartPoint.companiesController;
import static InterfaceControllers.StartPoint.currentClient;
import static InterfaceControllers.StartPoint.loadNewStage;

public class LogInPageController {
    @FXML
    private TextField agreementNumberTF;

    @FXML
    private TextField fioTF;

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

        if(!checkData(fio, agreementNumber)) {
            return;
        }

        if(!importantCheckBox.isSelected()) {
            return;
        }

        Agreement agreement = companiesController.searchAgreement(agreementNumber);
        if(agreement == null) {
            JOptionPane.showMessageDialog(null,
                    "Не найден введенный договор. Проверьте введенные данные.",
                    "Нет такого договора в базе.",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            loadNewStage(event, fxmlLoader);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки FXMLLoader");
        }

        currentClient = new Client(fio, agreement);
        System.out.println(currentClient);

        MainPageController mainPageController = fxmlLoader.getController();
        mainPageController.init(null);
    }

    private boolean checkData(String fio, String agreementNumber) {
        boolean dataIsCorrect = false;
        try {
            dataIsCorrect = validateFio(fio) && validateAgreementNumber(agreementNumber);
        } catch (NumberFormatException e ) {
            System.out.println("Ошибка обработки введенных строк.");
        }

        return dataIsCorrect;
    }

    private boolean validateAgreementNumber(String agreementNumber) {
        // Проверка формата номера договора (две буквы кирилицы в верхнем регистре и 16 цифр)
        if (!Pattern.matches("^[А-ЯЁ]{2}\\d{16}$", agreementNumber)) {
            JOptionPane.showMessageDialog(null,
                    "Неправильный формат номера договора. Проверьте введенные данные.",
                    "Ошибка формата введенных данных.",
                    JOptionPane.INFORMATION_MESSAGE);

            return false;  // Недопустимый формат
        }

        // Если все проверки пройдены, возвращаем true
        return true;
    }

    public static boolean validateFio(String inputString) {
        // Проверка допустимых символов (только буквы, пробелы и дефисы)
        if (!Pattern.matches("^[а-яА-ЯЁё\\s\\-]+$", inputString)) {
            JOptionPane.showMessageDialog(null,
                    "Неправильный формат ФИО. Проверьте введенные данные.",
                    "Ошибка формата введенных данных.",
                    JOptionPane.INFORMATION_MESSAGE);

            return false;  // Недопустимые символы
        }

        // Проверка количества слов (требуется три слова)
        String[] words = inputString.split("\\s+");
        if (words.length < 3) {
            JOptionPane.showMessageDialog(null,
                    "ФИО должно содержать минимум 3 слова. Проверьте введенные данные.",
                    "Ошибка формата введенных данных.",
                    JOptionPane.INFORMATION_MESSAGE);

            return false;  // Недопустимое количество слов
        }

        // Проверка каждого слова на начальную заглавную букву
        for (String word : words) {
            if (!Character.isUpperCase(word.charAt(0))) {
                JOptionPane.showMessageDialog(null,
                        "Слова в ФИО должны начинаться с заглавной буквы. Проверьте введенные данные.",
                        "Ошибка формата введенных данных.",
                        JOptionPane.INFORMATION_MESSAGE);

                return false;  // Начальная буква слова не является заглавной
            }
        }

        // Проверка минимальной и максимальной длины (примерно от 2 до 100 символов)
        int minLength = 10;
        int maxLength = 100;
        if (!(inputString.length() >= minLength && inputString.length() <= maxLength)) {
            JOptionPane.showMessageDialog(null,
                    "ФИО либо слишком короткое или слишком длинное. Проверьте введенные данные.",
                    "Ошибка формата введенных данных.",
                    JOptionPane.INFORMATION_MESSAGE);

            return false;  // Недопустимая длина
        }

        // Если все проверки пройдены, возвращаем true
        return true;
    }

}
