package Controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CheckExamEligibilityController {
    public AnchorPane checkEligibilityContext;
    public JFXComboBox cmbStdID;
    public Label lblName;
    public Label lblAge;
    public Label lblTel;
    public Label lblMarks1;
    public Label lblMarks2;
    public Label lblMarks3;
    public Label lblMarks4;
    public Label lblTotal;
    public Label lblAverage;
    public Label lblStatus;

    public void CloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) checkEligibilityContext.getScene().getWindow();
        stage.close();
    }

    public void cmbOnAction(ActionEvent actionEvent) {
        //Make Eligibility Test..
    }
}
