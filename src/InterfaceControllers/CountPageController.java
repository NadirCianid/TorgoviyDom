package InterfaceControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static InterfaceControllers.StartPoint.currentClient;
import static InterfaceControllers.StartPoint.warehouseController;

public class CountPageController {

    @FXML
    private Label agreementNumberLabel;

    @FXML
    private Label clientCompanyLabel;

    @FXML
    private Label clientNameLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label positionCountLabel;

    @FXML
    private Label totalMoneyAmountLabel;

    public void init() {
        clientNameLabel.setText("Заказчик: " + currentClient.getFio());
        clientCompanyLabel.setText("Компания заказчика: " + currentClient.getAgreement().getCompanyName());
        agreementNumberLabel.setText("Номер договора: " + currentClient.getAgreement().getAgreementNumber());
        positionCountLabel.setText("Позиций в заказе: " + currentClient.basket.getSelectedPositions().size() + " шт.");
        totalMoneyAmountLabel.setText("Итого к оплате:  " + currentClient.basket.getTotalSum() + " P.");
        dateLabel.setText("Дата заказа: " + LocalDate.now().format(DateTimeFormatter.ISO_DATE));

        warehouseController.makeOrder(currentClient.basket);
    }
}
