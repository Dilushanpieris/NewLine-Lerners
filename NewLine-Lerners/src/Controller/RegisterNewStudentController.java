package Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RegisterNewStudentController {
    public JFXComboBox cmbVehType;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXTextField txtAge;
    public JFXTextField txtTelephone;
    public Rectangle RegNewStdCotext;
    public JFXComboBox cmbStdType;

    public void initialize(){
        cmbVehType.getItems().add("Auto");
        cmbVehType.getItems().add("Manual");
        cmbStdType.getItems().add("New Student");
        cmbStdType.getItems().add("Exam Written Student");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if(cmbStdType.getValue().equals("New Student")){
            //New Student save..
        }
        if(cmbStdType.getValue().equals("Exam Written Student")){
            //save As Exam Written Student.
        }
    }

    public void btnClose(ActionEvent actionEvent) {
        Stage stage = (Stage) RegNewStdCotext.getScene().getWindow();
        stage.close();
    }
}
