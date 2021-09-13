package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDashFormController {
    public AnchorPane userDashContext;
    public Label lblTime;

    public void btnRegStd(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../View/UserDashContent/RegisterNewStudent.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnMarkPay(ActionEvent actionEvent) {
    }

    public void btnCheckExam(ActionEvent actionEvent) {
    }

    public void btnAssignLerner(ActionEvent actionEvent) {
    }

    public void btnMarkAttendance(ActionEvent actionEvent) {
    }

    public void btnLogut(ActionEvent actionEvent) {
        Stage stage = (Stage) userDashContext.getScene().getWindow();
        stage.close();
    }
}
