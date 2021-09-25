package Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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

public class TeacherDashController {
    public AnchorPane TeacherDashContext;
    public JFXComboBox cmbBoxStdID;
    public Label lblName;
    public Label lblAddress;
    public Label lblTelephone;
    public JFXTextField lblEx01Marks;
    public JFXTextField lblEx02Marks;
    public JFXTextField lblEx03Marks;
    public JFXTextField lblEx04Marks;

    public void cmbBoxStdIDOnAction(ActionEvent actionEvent) {
    }

    public void btnClose(ActionEvent actionEvent) throws IOException {
        TeacherDashContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/MainLogin.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    public void btnAddNewMarks(ActionEvent actionEvent) {
    }

    public void btnUpdateMarks(ActionEvent actionEvent) {
    }
}
