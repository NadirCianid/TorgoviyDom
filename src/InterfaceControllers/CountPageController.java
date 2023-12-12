package InterfaceControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

        warehouseController.makeOrder(currentClient.basket);
    }
}
