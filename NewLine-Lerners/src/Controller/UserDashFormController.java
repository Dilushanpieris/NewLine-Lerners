package Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.OffsetDateTime;

public class UserDashFormController {
    public AnchorPane userDashContext;
    public Label lblTime;

    public void initialize(){
        TimeNow(lblTime);
    }


    public void TimeNow(Label l) {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            OffsetDateTime offsetDT = OffsetDateTime.now();
            l.setText(offsetDT.toLocalDate() + " " + currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void btnRegStd(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../View/UserDashContent/RegisterNewStudent.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnMarkPay(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../View/UserDashContent/MarkPaymentForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnCheckExam(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../View/UserDashContent/CheckExamEligibility.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
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
