package Controller;

import Db.DbConnection;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssignLernerFormController {
    public AnchorPane AssignLernerContext;
    public JFXComboBox cmbStdID;
    public Label lblName;
    public Label lblAge;
    public Label lblTel;
    public JFXComboBox cmbLerner01;
    public JFXComboBox cmbLerner02;
    public JFXComboBox cmbExamStdID1;
    public ObservableList<String>obListT_ID= FXCollections.observableArrayList();
    public ObservableList<String>obListIns_ID= FXCollections.observableArrayList();
    public String SaveAction="";

    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> obListSt_ID= FXCollections.observableArrayList();
        ObservableList<String>obListStx_ID= FXCollections.observableArrayList();

        for (String temp:getAllStudentIds()) {
            obListSt_ID.add(temp);
        }
        for (String temp:getAllExamStdIDs()) {
            obListStx_ID.add(temp);
        }
        for (String temp:getAllInsIds()) {
            obListIns_ID.add(temp);
        }
        for (String temp:getAllTeacherIds()) {
            obListT_ID.add(temp);
        }
        cmbStdID.setItems(obListSt_ID);
        cmbExamStdID1.setItems(obListStx_ID);

    }


    public void cmbStdIDOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        cmbExamStdID1.setDisable(true);
        //load Teacher IDs
        SaveAction="TeacherDetail";
        cmbLerner01.setItems(obListT_ID);
        cmbLerner02.setItems(obListT_ID);
        //load All Details.
        if(cmbStdID.getValue()!=null) {
            Connection con = DbConnection.getInstance().getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Student WHERE St_ID=?");
            String selected = (String) cmbStdID.getValue();
            stm.setObject(1, selected);
            ResultSet rst = stm.executeQuery();
            if (rst.next()) {
                lblName.setText(rst.getString(2));
                lblAge.setText(String.valueOf(rst.getInt(3)));
                lblTel.setText(rst.getString(7));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Set").show();
            }
        }
    }


    public void CloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) AssignLernerContext.getScene().getWindow();
        stage.close();
    }

    public void AssignLernerOnActionBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(SaveAction=="InstructorDetail"){
            if(cmbLerner01.getValue()==cmbLerner02.getValue()){
                new Alert(Alert.AlertType.ERROR,"Select Different Learners").show();
                return;
            }
            if(CheackAssignInsTructor((String) cmbLerner01.getValue(),(String) cmbExamStdID1.getValue())){
                new Alert(Alert.AlertType.WARNING,"Already Assigned").show();
                return;
            }
            if(CheackAssignInsTructor((String) cmbLerner02.getValue(),(String) cmbExamStdID1.getValue())){
                new Alert(Alert.AlertType.WARNING,"Already Assigned").show();
                return;
            }
            Connection con= DbConnection.getInstance().getConnection();
            PreparedStatement stm=con.prepareStatement("INSERT INTO InstructorDetails VALUES(?,?,?)");
            String ExST_ID= String.valueOf(cmbExamStdID1.getValue());
            String InsID1= String.valueOf(cmbLerner01.getValue());
            String InsID2= String.valueOf(cmbLerner02.getValue());
            stm.setObject(1,InsID1);
            stm.setObject(2,ExST_ID);
            stm.setObject(3,DateNow());
            PreparedStatement stm2=con.prepareStatement("INSERT INTO InstructorDetails VALUES(?,?,?)");
            stm2.setObject(1,InsID2);
            stm2.setObject(2,ExST_ID);
            stm2.setObject(3,DateNow());
            if(stm.executeUpdate()>0&stm2.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Assigned").showAndWait();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Data_Error").show();
            }
        }
        if(SaveAction=="TeacherDetail"){
            if(cmbLerner01.getValue()==cmbLerner02.getValue()){
                new Alert(Alert.AlertType.ERROR,"Select Different Learners").show();
                return;
            }
            if(CheackAssignTeacher((String) cmbLerner01.getValue(),(String) cmbStdID.getValue())){
                new Alert(Alert.AlertType.WARNING,"Already Assigned").show();
                return;
            }
            if(CheackAssignTeacher((String) cmbLerner02.getValue(),(String) cmbStdID.getValue())){
                new Alert(Alert.AlertType.WARNING,"Already Assigned").show();
                return;
            }
            Connection con= DbConnection.getInstance().getConnection();
            PreparedStatement stm=con.prepareStatement("INSERT INTO TeacherDetail VALUES(?,?,?)");
            String St_ID= String.valueOf(cmbStdID.getValue());
            String T_ID1= String.valueOf(cmbLerner01.getValue());
            String T_ID2= String.valueOf(cmbLerner02.getValue());
            stm.setObject(1,T_ID1);
            stm.setObject(2,St_ID);
            stm.setObject(3,DateNow());
            PreparedStatement stm2=con.prepareStatement("INSERT INTO TeacherDetail VALUES(?,?,?)");
            stm2.setObject(1,T_ID2);
            stm2.setObject(2,St_ID);
            stm2.setObject(3,DateNow());
            if(stm.executeUpdate()>0&stm2.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Assigned").showAndWait();
            }
            else {
                new Alert(Alert.AlertType.WARNING, "Data_Error").show();
            }
        }
    }
    public boolean CheackAssignTeacher(String T_ID,String St_ID) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM TeacherDetail WHERE T_ID=? AND St_ID=?");
        stm.setObject(1, T_ID);
        stm.setObject(2,St_ID);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean CheackAssignInsTructor(String Ins_ID,String Stx_ID) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM InstructorDetails WHERE Ins_ID=? AND Stx_ID=?");
        stm.setObject(1, Ins_ID);
        stm.setObject(2,Stx_ID);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            return true;
        }
        else{
            return false;
        }
    }

    public void cmbExamStdIDOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        cmbStdID.setDisable(true);
        //load Instructor
        SaveAction="InstructorDetail";
        cmbLerner01.setItems(obListIns_ID);
        cmbLerner02.setItems(obListIns_ID);
        //load All Details.
        if(cmbExamStdID1.getValue()!=null) {
            Connection con = DbConnection.getInstance().getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT * FROM ExamWrittenStudent WHERE Stx_ID=?");
            String selected = (String) cmbExamStdID1.getValue();
            stm.setObject(1, selected);
            ResultSet rst = stm.executeQuery();
            if (rst.next()) {
                lblName.setText(rst.getString(2));
                lblAge.setText(String.valueOf(rst.getInt(3)));
                lblTel.setText(rst.getString(7));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Set").show();
            }
        }
    }

    public void clearSelectionOnAction(ActionEvent actionEvent) {
        cmbExamStdID1.getSelectionModel().clearSelection();
        cmbStdID.getSelectionModel().clearSelection();
        lblAge.setText("");
        lblName.setText("");
        lblTel.setText("");
        cmbStdID.setDisable(false);
        cmbExamStdID1.setDisable(false);
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
    public List<String> getAllExamStdIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst= DbConnection.getInstance().getConnection().
                prepareStatement("SELECT Stx_ID FROM ExamWrittenStudent").executeQuery();
        List<String>ids=new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }
    public List<String> getAllTeacherIds() throws SQLException, ClassNotFoundException {
        ResultSet rst= DbConnection.getInstance().getConnection().
                prepareStatement("SELECT T_ID FROM Teacher").executeQuery();
        List<String>ids=new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }
    public List<String> getAllInsIds() throws SQLException, ClassNotFoundException {
        ResultSet rst= DbConnection.getInstance().getConnection().
                prepareStatement("SELECT Ins_ID FROM Instructor").executeQuery();
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
}
