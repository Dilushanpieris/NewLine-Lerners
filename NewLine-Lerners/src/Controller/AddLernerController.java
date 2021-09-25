package Controller;

import Db.DbConnection;
import Model.Instructor;
import Model.Teacher;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddLernerController {
    public JFXComboBox cmbBoxLernerType;
    public JFXTextField lblName;
    public JFXTextField lblAddress;
    public JFXTextField lblTel;
    public JFXButton btnAddLerner;
    public AnchorPane addLernerContext;

    public void initialize(){
        cmbBoxLernerType.getItems().add("Teacher");
        cmbBoxLernerType.getItems().add("Instructor");
    }

    public void btnAddLernerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(cmbBoxLernerType.getValue()=="Teacher"){
            String TempID="";
            //Make Teacher ID As Acc
            Connection con= DbConnection.getInstance().getConnection();
            ResultSet rst=con.prepareStatement("SELECT T_ID FROM Teacher WHERE T_ID=(SELECT max(T_ID) FROM Teacher)").executeQuery();
            if(rst.next()){
                String lastID=rst.getString(1);
                String inc=lastID.substring(lastID.length()-1);
                int incInt=Integer.parseInt(inc);
                incInt++;
                TempID="T00"+incInt;
            }
            else{
                TempID="T001";
            }
            //Save Teacher
            Teacher t1=new Teacher(TempID,lblName.getText(),lblAddress.getText(),lblTel.getText());
            String query=("INSERT INTO Teacher VALUES(?,?,?,?)");
            PreparedStatement stm=con.prepareStatement(query);
            stm.setObject(1,t1.getT_id());
            stm.setObject(2,t1.getName());
            stm.setObject(3,t1.getAddress());
            stm.setObject(4,t1.getTel());

            if(stm.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Saved").show();
                clearFields();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Try_Again").show();
            }


        }
        if(cmbBoxLernerType.getValue()=="Instructor"){
            //Last ID Of ins
            String TempID="";
            Connection con= DbConnection.getInstance().getConnection();
            ResultSet rst=con.prepareStatement("SELECT Ins_ID FROM Instructor WHERE Ins_ID=(SELECT max(Ins_ID) FROM Instructor)").executeQuery();
            if(rst.next()){
                String lastID=rst.getString(1);
                String inc=lastID.substring(lastID.length()-1);
                int incInt=Integer.parseInt(inc);
                incInt++;
                TempID="I00"+incInt;
            }
            else{
                TempID="I001";
            }
            //Save As Instructor
            Instructor I1=new Instructor(TempID,lblName.getText(),lblAddress.getText(),lblTel.getText());
            String query="INSERT INTO Instructor VALUES(?,?,?,?)";
            PreparedStatement stm=con.prepareStatement(query);
            stm.setObject(1,I1.getIns_id());
            stm.setObject(2,I1.getName());
            stm.setObject(3,I1.getAddress());
            stm.setObject(4,I1.getTel());
            if(stm.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Saved").show();
                clearFields();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Try_Again").show();
            }

        }
    }

    public void btnClose(ActionEvent actionEvent) throws IOException {
        addLernerContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/AdminDash.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }
    public void clearFields(){
        cmbBoxLernerType.getSelectionModel().clearSelection();
        lblAddress.clear();
        lblName.clear();
        lblTel.clear();
    }
}
