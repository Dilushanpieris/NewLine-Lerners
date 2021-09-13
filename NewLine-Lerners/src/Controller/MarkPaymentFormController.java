package Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MarkPaymentFormController {
    public JFXComboBox cmbStdID;
    public Label lblStdName;
    public Label lblAge;
    public Label lblAddress;
    public Label lblTelephone;
    public JFXTextField txtAmount;
    public AnchorPane makePaymentContext;

    public void AmountValidation(KeyEvent keyEvent) {
        //Validation
    }

    public void CloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) makePaymentContext.getScene().getWindow();
        stage.close();
    }

    public void markPaymentOnAction(ActionEvent actionEvent) {
        //Save Payment Details
    }
}
