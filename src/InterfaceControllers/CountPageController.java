package InterfaceControllers;

import backend.model.Warehouse;
import backend.model.basket.Position;
import backend.model.order.Order;
import backend.service.WarehouseService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;

import static InterfaceControllers.StartPoint.applicationContext;
import static InterfaceControllers.StartPoint.currentClient;

public class CountPageController {
    private WarehouseService warehouseService;

    @FXML
    private Label agreementNumberLabel;

    @FXML
    private Label clientCompanyLabel;

    @FXML
    private Label clientNameLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label orderStatus;

    @FXML
    private Label positionCountLabel;

    @FXML
    private Label totalMoneyAmountLabel;

    public void init() {
        warehouseService = applicationContext.getWarehouseService();

        Order order = formOrder();
        BigDecimal totalSum = order.getTotalSum();

        clientNameLabel.setText("Заказчик: " + order.getClient().getFio());
        clientCompanyLabel.setText("Компания заказчика: " + order.getClient().getAgreement().companyName());
        agreementNumberLabel.setText("Номер договора: " + order.getClient().getAgreement().agreementNumber());
        positionCountLabel.setText("Позиций в заказе: " + order.getOrderItems().size() + " шт.");
        totalMoneyAmountLabel.setText("Итого к оплате:  " + totalSum + " P.");
        dateLabel.setText("Дата заказа: " + LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        orderStatus.setText("Статус заказа: " + order.getStatus());
    }

    private Order formOrder() {
        List<Position> selectedPositions =  currentClient.basket.getSelectedPositions();
        return warehouseService.createOrder(selectedPositions, currentClient);
    }
}
