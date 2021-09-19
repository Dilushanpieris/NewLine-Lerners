package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MarkAttendanceController {
    public AnchorPane MarkAttendanceContext;
    public JFXComboBox cmbBoxStdId;
    public Label lblName;
    public Label albAddress;
    public Label lblTele;
    public Label lblMarkingStats;
    public JFXButton btnLearningStu;
    public JFXButton btnTrainingStu;
    public JFXButton btnMarkAtt;
    public int selected=1; //1 for lerner 2 for trainee;
    public void initialize(){
        btnLearningStu.setStyle("-fx-border-color:red;"+"-fx-background-color: linear-gradient(to right, #4ca1af, #c4e0e5);"+"-fx-border-width:3px");
    }

    public void cmbStdIdOnAction(ActionEvent actionEvent) {
        //load details of each student
    }

    public void btnClose(ActionEvent actionEvent) throws IOException {
        MarkAttendanceContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/UserDashForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void btnMarkAttOnAction(ActionEvent actionEvent) {
        if(selected==1){
            //Mark Attendance For Lerning Student
        }
        if(selected==2){
            //Mark Attendance For Training Student
        }
    }

    public void btnlerningStuOnAction(ActionEvent actionEvent) {
        btnLearningStu.setStyle("-fx-border-color:red;"+"-fx-background-color: linear-gradient(to right, #4ca1af, #c4e0e5);"+"-fx-border-width:3px");
        btnTrainingStu.setStyle("-fx-border-color:Transparent;"+"-fx-background-color: linear-gradient(to right, #4ca1af, #c4e0e5);"+"-fx-border-width:3px");
        selected=1;
        //Load All Student IDs(St_ID)
    }

    public void btnTrainingStuOnAction(ActionEvent actionEvent) {
        btnTrainingStu.setStyle("-fx-border-color:red;"+"-fx-background-color: linear-gradient(to right, #4ca1af, #c4e0e5);"+"-fx-border-width:3px");
        btnLearningStu.setStyle("-fx-border-color:Transparent;"+"-fx-background-color: linear-gradient(to right, #4ca1af, #c4e0e5);"+"-fx-border-width:3px");
        selected=2;
        //Load All Ex.Written Student Ids(Stx_ID)
    }
}
