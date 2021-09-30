package Controller;

import Db.DbConnection;
import Model.Student;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterNewStudentController {
    public JFXComboBox cmbVehType;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXTextField txtAge;
    public JFXTextField txtTelephone;
    public Rectangle RegNewStdCotext;
    public JFXComboBox cmbStdType;
    public JFXButton btnsave;

    public void initialize(){
        cmbVehType.getItems().add("Auto");
        cmbVehType.getItems().add("Manual");
        cmbStdType.getItems().add("New Student");
        cmbStdType.getItems().add("Exam Written Student");
    }
    public boolean isempty(){
        if(cmbStdType.getSelectionModel().isEmpty()&cmbVehType.getSelectionModel().isEmpty()&txtName.getText().isEmpty()&txtAge.getText().isEmpty()&txtTelephone.getText().isEmpty()&txtEmail.getText().isEmpty()&txtAddress.getText().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(isempty()){
            new Alert(Alert.AlertType.WARNING,"Incorrect Data").show();
            return;
        }
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

    public void NameValidation(KeyEvent keyEvent) {
        String regEx="[A-z]";
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(txtName.getText()).matches();
        if(matches==true){
            btnsave.setDisable(false);
        }
        else{
            btnsave.setDisable(true);
        }
    }

    public void AddressValidate(KeyEvent keyEvent) {
    }

    public void EmailValidate(KeyEvent keyEvent) {
    }

    public void AgeValidate(KeyEvent keyEvent) {
        String regEx="[0-9]{2}";
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(txtAge.getText()).matches();
        if(matches==true){
            btnsave.setDisable(false);
        }
        else{
            btnsave.setDisable(true);
        }
    }

    public void TelValidate(KeyEvent keyEvent) {
    }
}
