package InterfaceControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class MessagePageController {
    @FXML
    private Text messageTextText;

    @FXML
    private Label messageTitleLabel;

    public void setMessage(String messageText, String messageTitle) {
        messageTitleLabel.setText(messageTitle);
        messageTextText.setText(messageText);
    }
}
