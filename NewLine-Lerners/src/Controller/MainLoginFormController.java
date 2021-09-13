package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainLoginFormController {
    public JFXTextField UnameTxt;
    public JFXPasswordField PassTxt;
    public JFXButton btnUser;
    public JFXButton Btntchr;
    public JFXButton BtnAdmin;
    public int loginActionNum=-1;
    public StackPane loginFormContext;

    public void initialize(){
        btnUser.setDisable(true);
        loginActionNum=1;
    }

    public void UserLoginOnAction(ActionEvent actionEvent) {
        btnUser.setDisable(true);
        Btntchr.setDisable(false);
        BtnAdmin.setDisable(false);
        loginActionNum=1;
        UnameTxt.requestFocus();
    }

    public void TeacherLoginOnAction(ActionEvent actionEvent) {
        Btntchr.setDisable(true);
        btnUser.setDisable(false);
        BtnAdmin.setDisable(false);
        loginActionNum=2;
        UnameTxt.requestFocus();
    }

    public void AdminLoginOnAction(ActionEvent actionEvent) {
        BtnAdmin.setDisable(true);
        Btntchr.setDisable(false);
        btnUser.setDisable(false);
        loginActionNum=3;
        UnameTxt.requestFocus();
    }

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        if(loginActionNum==1){
            Parent load = FXMLLoader.load(getClass().getResource("../View/UserDashForm.fxml"));
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        if(loginActionNum==2){
            Parent load = FXMLLoader.load(getClass().getResource(""));
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        if(loginActionNum==3){
            Parent load = FXMLLoader.load(getClass().getResource(""));
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }

    public void CloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) loginFormContext.getScene().getWindow();
        stage.close();
    }
}
