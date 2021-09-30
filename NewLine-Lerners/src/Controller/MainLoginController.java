package Controller;

import Db.DbConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MainLoginController {
    public AnchorPane loginforncontext;
    public JFXTextField UnameTxt;
    public JFXPasswordField PassTxt;
    public JFXButton LoginBtn;
    public int selected=1;
    public JFXButton btnUser;
    public JFXButton Btntch;
    public JFXButton BtnAdmin;

    public void initialize(){
        btnUser.setDisable(true);
        LoginBtn.setText("Login As User");
        btnUser.setStyle("-fx-border-color:red;" +
                "-fx-background-color:#f1c40f;"+
                "-fx-border-width:3px");
        BtnAdmin.setStyle("-fx-background-color: #ff6b6b;"+
                "-fx-border-width:3px");
        Btntch.setStyle("-fx-background-color: #8e44ad;"+
                "-fx-border-width:3px");
    }
    public void clearFields(){
        UnameTxt.setText("");
        PassTxt.setText("");
    }

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        if(selected==1){
            if(UnameTxt.getText().equals("User")&PassTxt.getText().equals("user123")) {
                Parent load = FXMLLoader.load(getClass().getResource("../View/UserDashForm.fxml"));
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                loginforncontext.getScene().getWindow().hide();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Incorrect Username Or Password").show();
            }
        }
        if(selected==2){
            if(UnameTxt.getText().equals("Teacher")&PassTxt.getText().equals("teacher123")) {
                //teacher
                Parent load = FXMLLoader.load(getClass().getResource("../View/TeacherDash.fxml"));
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                scene.setFill(Color.TRANSPARENT);
                stage.show();
                loginforncontext.getScene().getWindow().hide();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Incorrect Username Or Password").show();
            }
        }
        if(selected==3){
            if(UnameTxt.getText().equals("Admin")&PassTxt.getText().equals("admin123")) {
                //admin
                Parent load = FXMLLoader.load(getClass().getResource("../View/AdminDash.fxml"));
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                scene.setFill(Color.TRANSPARENT);
                stage.show();
                loginforncontext.getScene().getWindow().hide();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Incorrect Username Or Password").show();
            }
        }
    }

    public void CloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) loginforncontext.getScene().getWindow();
        stage.close();
    }

    public void UserLoginOnAction(ActionEvent actionEvent) {
        clearFields();
        btnUser.setDisable(true);
        selected=1;
        Btntch.setDisable(false);
        BtnAdmin.setDisable(false);
        UnameTxt.requestFocus();
        btnUser.setStyle("-fx-border-color:red;"+"-fx-background-color:#f1c40f;"+"-fx-border-width:3px");
        Btntch.setStyle("-fx-border-color:Transparent;"+"-fx-background-color: #8e44ad;"+"-fx-border-width:3px");
        BtnAdmin.setStyle("-fx-border-color:Transparent;"+"-fx-background-color: #ff6b6b;"+"-fx-border-width:3px");
        LoginBtn.setText("Login As User");
    }

    public void TeacherLoginOnAction(ActionEvent actionEvent) {
        clearFields();
        Btntch.setDisable(true);
        selected=2;
        btnUser.setDisable(false);
        BtnAdmin.setDisable(false);
        UnameTxt.requestFocus();
        Btntch.setStyle("-fx-border-color:red;"+"-fx-background-color: #8e44ad;"+"-fx-border-width:3px");
        btnUser.setStyle("-fx-border-color:Transparent;"+"-fx-background-color:#f1c40f;"+"-fx-border-width:3px");
        BtnAdmin.setStyle("-fx-border-color:Transparent;"+"-fx-background-color: #ff6b6b;"+"-fx-border-width:3px");
        LoginBtn.setText("Login As Teacher");
    }

    public void AdminLoginOnAction(ActionEvent actionEvent) {
        clearFields();
        BtnAdmin.setDisable(true);
        selected=3;
        btnUser.setDisable(false);
        Btntch.setDisable(false);
        BtnAdmin.setStyle("-fx-border-color:red;"+"-fx-background-color: #ff6b6b;"+"-fx-border-width:3px");
        Btntch.setStyle("-fx-border-color:Transparent;"+"-fx-background-color: #8e44ad;"+"-fx-border-width:3px");
        btnUser.setStyle("-fx-border-color:Transparent;"+"-fx-background-color:#f1c40f;"+"-fx-border-width:3px");
        LoginBtn.setText("Login As Admin");
    }
}
