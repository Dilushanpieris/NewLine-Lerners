package Controller;

import Db.DbConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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
    public JFXButton btnAddMarksID;
    public JFXButton btnUpmarksID;


    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList= FXCollections.observableArrayList();
        for (String temp:getAllStudentIds()) {
            obList.add(temp);
        }
        cmbBoxStdID.setItems(obList);
    }

    public void cmbBoxStdIDOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        clearFields();
        Connection con=DbConnection.getInstance().getConnection();
        PreparedStatement stm=con.prepareStatement("SELECT * FROM Student WHERE St_ID=?");
        String selected= (String) cmbBoxStdID.getValue();
        stm.setObject(1,selected);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            lblName.setText(rst.getString(2));
            lblAddress.setText(rst.getString(6));
            lblTelephone.setText(rst.getString(7));
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Empty Set").show();
        }
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

    public void btnAddNewMarks(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id= (String) cmbBoxStdID.getValue();
        if(isAlredyEnterd(id)){
            new Alert(Alert.AlertType.CONFIRMATION,"Marks Already Added Please Use Update Marks").showAndWait();
            return;
        }
        String date=DateNow();
        Connection con= DbConnection.getInstance().getConnection();
        PreparedStatement stm1=con.prepareStatement("INSERT INTO ExamDetail VALUES(?,?,?,?)");
        stm1.setObject(1,"Ex001");
        stm1.setObject(2,id);
        stm1.setObject(3,date);
        stm1.setObject(4,lblEx01Marks.getText());
        PreparedStatement stm2=con.prepareStatement("INSERT INTO ExamDetail VALUES(?,?,?,?)");
        stm2.setObject(1,"Ex002");
        stm2.setObject(2,id);
        stm2.setObject(3,date);
        stm2.setObject(4,lblEx02Marks.getText());
        PreparedStatement stm3=con.prepareStatement("INSERT INTO ExamDetail VALUES(?,?,?,?)");
        stm3.setObject(1,"Ex003");
        stm3.setObject(2,id);
        stm3.setObject(3,date);
        stm3.setObject(4,lblEx03Marks.getText());
        PreparedStatement stm4=con.prepareStatement("INSERT INTO ExamDetail VALUES(?,?,?,?)");
        stm4.setObject(1,"Ex004");
        stm4.setObject(2,id);
        stm4.setObject(3,date);
        stm4.setObject(4,lblEx04Marks.getText());
        if(stm1.executeUpdate()>0&stm2.executeUpdate()>0&stm3.executeUpdate()>0&stm4.executeUpdate()>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Marks Added").showAndWait();
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try_Again").show();
        }

    }

    public void btnUpdateMarks(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id= (String) cmbBoxStdID.getValue();
        Connection con= DbConnection.getInstance().getConnection();
        //Load All Details With Marks.
        PreparedStatement stm=con.prepareStatement("UPDATE ExamDetail SET Marks=? WHERE St_ID=? AND Ex_ID=?");
        stm.setObject(1,lblEx01Marks.getText());
        stm.setObject(2,id);
        stm.setObject(3,"Ex001");
        PreparedStatement stm2=con.prepareStatement("UPDATE ExamDetail SET Marks=? WHERE St_ID=? AND Ex_ID=?");
        stm2.setObject(1,lblEx02Marks.getText());
        stm2.setObject(2,id);
        stm2.setObject(3,"Ex002");
        PreparedStatement stm3=con.prepareStatement("UPDATE ExamDetail SET Marks=? WHERE St_ID=? AND Ex_ID=?");
        stm3.setObject(1,lblEx03Marks.getText());
        stm3.setObject(2,id);
        stm3.setObject(3,"Ex003");
        PreparedStatement stm4=con.prepareStatement("UPDATE ExamDetail SET Marks=? WHERE St_ID=? AND Ex_ID=?");
        stm4.setObject(1,lblEx04Marks.getText());
        stm4.setObject(2,id);
        stm4.setObject(3,"Ex004");
        if(stm.executeUpdate()>0&stm2.executeUpdate()>0&stm3.executeUpdate()>0&stm4.executeUpdate()>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updated").showAndWait();
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try_Again").show();
        }
    }
    public boolean isAlredyEnterd(String id) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM ExamDetail WHERE St_ID=?");
        stm.setObject(1, id);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            return true;
        }
        else{
            return false;
        }
    }
    public List<String> getAllStudentIds() throws SQLException, ClassNotFoundException {
        ResultSet rst= DbConnection.getInstance().getConnection().
                prepareStatement("SELECT St_ID FROM Student").executeQuery();
        List<String>ids=new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }
    public String DateNow(){
        Date date=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        return f.format(date);
    }

    public void ex01marksValidate(KeyEvent keyEvent) {
        String regEx="^[0-9]{2}$";
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(lblEx01Marks.getText()).matches();
        if(matches==true){
            btnAddMarksID.setDisable(false);
            btnUpmarksID.setDisable(false);
        }
        else{
            btnAddMarksID.setDisable(true);
            btnUpmarksID.setDisable(true);
        }
    }

    public void ex02marksValidate(KeyEvent keyEvent) {
        String regEx="^[0-9]{2}$";
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(lblEx02Marks.getText()).matches();
        if(matches==true){
            btnAddMarksID.setDisable(false);
            btnUpmarksID.setDisable(false);
        }
        else{
            btnAddMarksID.setDisable(true);
            btnUpmarksID.setDisable(true);
        }

    }

    public void ex03marksValidate(KeyEvent keyEvent) {
        String regEx="^[0-9]{2}$";
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(lblEx03Marks.getText()).matches();
        if(matches==true){
            btnAddMarksID.setDisable(false);
            btnUpmarksID.setDisable(false);
        }
        else{
            btnAddMarksID.setDisable(true);
            btnUpmarksID.setDisable(true);
        }
    }
    public void clearFields(){
        lblEx01Marks.setText("");
        lblEx02Marks.setText("");
        lblEx03Marks.setText("");
        lblEx04Marks.setText("");
    }

    public void ex04marksValidate(KeyEvent keyEvent) {
        String regEx="^[0-9]{2}$";
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(lblEx04Marks.getText()).matches();
        if(matches==true){
            btnAddMarksID.setDisable(false);
            btnUpmarksID.setDisable(false);
        }
        else{
            btnAddMarksID.setDisable(true);
            btnUpmarksID.setDisable(true);
        }
    }
}
