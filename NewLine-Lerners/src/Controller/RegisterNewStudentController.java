package Controller;

import Db.DbConnection;
import Model.Student;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(cmbStdType.getValue().equals("New Student")){
            //get Lst ID
            String TempID="";
            Connection con= DbConnection.getInstance().getConnection();
            ResultSet rst=con.prepareStatement("SELECT St_ID FROM Student WHERE St_ID=(SELECT max(St_ID) FROM Student)").executeQuery();
            if(rst.next()){
                String lastID=rst.getString(1);
                String inc=lastID.substring(lastID.length()-1);
                int incInt=Integer.parseInt(inc);
                incInt++;
                TempID="St00"+incInt;
            }
            else{
                TempID="St001";
            }
            //New Student save..
            String SelectedType= (String) cmbVehType.getValue();
            Student st1=new Student(TempID,txtName.getText(),Integer.parseInt(txtAge.getText()),SelectedType,txtEmail.getText(),txtAddress.getText(),txtTelephone.getText());
            String query="INSERT INTO Student VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stm=con.prepareStatement(query);
            stm.setObject(1,st1.getSt_ID());
            stm.setObject(2,st1.getName());
            stm.setObject(3,st1.getAge());
            stm.setObject(4,st1.getVehicle_Type());
            stm.setObject(5,st1.getEmail());
            stm.setObject(6,st1.getAddress());
            stm.setObject(7,st1.getTelephone());


            if(stm.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Saved").showAndWait();
                RegNewStdCotext.getScene().getWindow().hide();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Try_Again").show();
            }
        }
        if(cmbStdType.getValue().equals("Exam Written Student")){
            //get Lst ID
            String TempID="";
            Connection con= DbConnection.getInstance().getConnection();
            ResultSet rst=con.prepareStatement("SELECT Stx_ID FROM ExamWrittenStudent WHERE Stx_ID=(SELECT max(Stx_ID) FROM ExamWrittenStudent)").executeQuery();
            if(rst.next()){
                String lastID=rst.getString(1);
                String inc=lastID.substring(lastID.length()-1);
                int incInt=Integer.parseInt(inc);
                incInt++;
                TempID="Stx00"+incInt;
            }
            else{
                TempID="Stx001";
            }
            //save As Exam Written Student.
            String SelectedType= (String) cmbVehType.getValue();
            Student st1=new Student(TempID,txtName.getText(),Integer.parseInt(txtAge.getText()),SelectedType,txtEmail.getText(),txtAddress.getText(),txtTelephone.getText());
            String query="INSERT INTO ExamWrittenStudent VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stm=con.prepareStatement(query);
            stm.setObject(1,st1.getSt_ID());
            stm.setObject(2,st1.getName());
            stm.setObject(3,st1.getAge());
            stm.setObject(4,st1.getVehicle_Type());
            stm.setObject(5,st1.getEmail());
            stm.setObject(6,st1.getAddress());
            stm.setObject(7,st1.getTelephone());
            if(stm.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Saved").showAndWait();
                RegNewStdCotext.getScene().getWindow().hide();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Try_Again").show();
            }
        }
    }

    public void btnClose(ActionEvent actionEvent){
        Stage stage = (Stage) RegNewStdCotext.getScene().getWindow();
        stage.close();
    }
}
