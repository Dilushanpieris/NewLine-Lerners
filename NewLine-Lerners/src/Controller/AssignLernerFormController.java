package Controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AssignLernerFormController {
    public AnchorPane AssignLernerContext;
    public JFXComboBox cmbStdID;
    public Label lblName;
    public Label lblAge;
    public Label lblTel;
    public JFXComboBox cmbLernerType;
    public JFXComboBox cmbLerner01;
    public JFXComboBox cmbLerner02;

    public void initialize(){
        cmbLernerType.getItems().add("Teacher");
        cmbLernerType.getItems().add("Instructor");
    }

    public void cmbStdIDOnAction(ActionEvent actionEvent) {
        //Load Std Data to Labels
    }

    public void cmbLernerTypeOnAction(ActionEvent actionEvent) {
        //Load Teacher/Instructor For cmb boxes.
    }

    public void CloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) AssignLernerContext.getScene().getWindow();
        stage.close();
    }

    public void AssignLernerOnActionBtn(ActionEvent actionEvent) {
    }
}
