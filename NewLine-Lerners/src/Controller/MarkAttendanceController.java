package Controller;

import Db.DbConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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

public class MarkAttendanceController {
    public AnchorPane MarkAttendanceContext;
    public JFXComboBox cmbBoxStdId;
    public Label lblName;
    public Label albAddress;
    public Label lblTele;
    public Label lblMarkingStats;
    public JFXButton btnLearningStu;
    public JFXButton btnTrainingStu;
    public JFXButton btnMarkAtt;
    public int selected=1; //1 for lerner 2 for trainee;
    public JFXComboBox cmbBoxLernerID;
    public ObservableList<String> obListSt_ID= FXCollections.observableArrayList();
    public ObservableList<String>obListStx_ID= FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {

        for (String temp:getAllStudentIds()) {
            obListSt_ID.add(temp);
        }
        for (String temp:getAllExamStdIDs()) {
            obListStx_ID.add(temp);
        }
        btnLearningStu.setStyle("-fx-border-color:red;"+"-fx-background-color: linear-gradient(to right, #4ca1af, #c4e0e5);"+"-fx-border-width:3px");
        cmbBoxStdId.setItems(obListSt_ID);
    }

    public void cmbStdIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //load details of each student
        if(selected==1){
            if(cmbBoxStdId.getValue()!=null) {
                Connection con = DbConnection.getInstance().getConnection();
                PreparedStatement stm = con.prepareStatement("SELECT * FROM Student WHERE St_ID=?");
                String ID = (String) cmbBoxStdId.getValue();
                stm.setObject(1, ID);
                ResultSet rst = stm.executeQuery();
                if (rst.next()) {
                    lblName.setText(rst.getString(2));
                    albAddress.setText(rst.getString(6));
                    lblTele.setText(rst.getString(7));
                } else {
                    new Alert(Alert.AlertType.WARNING, "Empty Set").show();
                }
                ObservableList<String>T_IDs= FXCollections.observableArrayList();
                for (String temp:getTeacherIDs(ID)){
                    T_IDs.add(temp);
                }
                cmbBoxLernerID.setItems(T_IDs);
            }
        }
        if(selected==2){
            if(cmbBoxStdId.getValue()!=null) {
                Connection con = DbConnection.getInstance().getConnection();
                PreparedStatement stm = con.prepareStatement("SELECT * FROM ExamWrittenStudent WHERE Stx_ID=?");
                String ID = (String) cmbBoxStdId.getValue();
                stm.setObject(1, ID);
                ResultSet rst = stm.executeQuery();
                if (rst.next()) {
                    lblName.setText(rst.getString(2));
                    albAddress.setText(rst.getString(6));
                    lblTele.setText(rst.getString(7));
                } else {
                    new Alert(Alert.AlertType.WARNING, "Empty Set").show();
                }
                ObservableList<String>Ins_IDs= FXCollections.observableArrayList();
                for (String temp:getInstructorIds(ID)){
                    Ins_IDs.add(temp);
                }
                cmbBoxLernerID.setItems(Ins_IDs);
            }
        }
    }

    public void btnClose(ActionEvent actionEvent) throws IOException {
        MarkAttendanceContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/UserDashForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void btnMarkAttOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(selected==1){
            if(isAlredyMarkedLerning((String) cmbBoxLernerID.getValue(),(String) cmbBoxStdId.getValue(),DateNow())){
                new Alert(Alert.AlertType.WARNING,"Already Marked For Today").showAndWait();
                lblMarkingStats.setText("Marked");
                return;
            }
            int count=0;
            Connection con= DbConnection.getInstance().getConnection();
            //get Last Date
            PreparedStatement stm1=con.prepareStatement("SELECT MAX(Day_Count) FROM LearningDetails WHERE St_ID=?");
            String id= (String) cmbBoxStdId.getValue();
            stm1.setObject(1,id);
            ResultSet rst=stm1.executeQuery();
            if(rst.next()){
                int currentcount=rst.getInt(1);
                count=currentcount+1;
            }
            else{
                count=1;
            }

            //Mark Attendance For Lerning Student

            PreparedStatement stm=con.prepareStatement("INSERT INTO LearningDetails VALUES(?,?,?,?)");
            String T_ID= String.valueOf(cmbBoxLernerID.getValue());
            String St_ID= String.valueOf(cmbBoxStdId.getValue());
            String Date= DateNow();
            stm.setObject(1,T_ID);
            stm.setObject(2,St_ID);
            stm.setObject(3,Date);
            stm.setObject(4,count);
            if(stm.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Attendance Marked").showAndWait();
                lblMarkingStats.setText("Marked");
            }
            else {
                new Alert(Alert.AlertType.WARNING,"Try_Again").show();
            }
        }
        if(selected==2){
            if(isAlredyMarkedTraining((String) cmbBoxLernerID.getValue(),(String) cmbBoxStdId.getValue(),DateNow())){
                new Alert(Alert.AlertType.WARNING,"Already Marked For Today").showAndWait();
                lblMarkingStats.setText("Marked");
                return;
            }
            int count=0;
            Connection con= DbConnection.getInstance().getConnection();
            //get Last Date
            PreparedStatement stm1=con.prepareStatement("SELECT MAX(Day_Count) FROM TrainingDetail WHERE Stx_ID=?");
            String id= (String) cmbBoxStdId.getValue();
            stm1.setObject(1,id);
            ResultSet rst=stm1.executeQuery();
            if(rst.next()){
                int currentcount=rst.getInt(1);
                count=currentcount+1;
            }
            else{
                count=1;
            }
            //Mark Attendance For Training Student
            PreparedStatement stm=con.prepareStatement("INSERT INTO TrainingDetail VALUES(?,?,?,?)");
            String Ins_ID= String.valueOf(cmbBoxLernerID.getValue());
            String Stx_ID= String.valueOf(cmbBoxStdId.getValue());
            String Date= DateNow();
            stm.setObject(1,Stx_ID);
            stm.setObject(2,Ins_ID);
            stm.setObject(3,Date);
            stm.setObject(4,count);
            if(stm.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Attendance Marked").showAndWait();
                lblMarkingStats.setText("Marked");
            }
            else {
                new Alert(Alert.AlertType.WARNING,"Try_Again").show();
            }
        }
    }

    public boolean isAlredyMarkedLerning(String T_ID,String St_ID,String date) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        PreparedStatement stm=con.prepareStatement("SELECT * FROM LearningDetails WHERE T_ID=? AND St_ID=? AND Session_Date=?");
        stm.setObject(1,T_ID);
        stm.setObject(2,St_ID);
        stm.setObject(3,date);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isAlredyMarkedTraining(String Ins_ID,String Stx_ID,String date) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        PreparedStatement stm=con.prepareStatement("SELECT * FROM TrainingDetail WHERE Stx_ID=? AND Ins_ID=? AND Session_Date=?");
        stm.setObject(1,Stx_ID);
        stm.setObject(2,Ins_ID);
        stm.setObject(3,date);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            return true;
        }
        else{
            return false;
        }
    }

    public void btnlerningStuOnAction(ActionEvent actionEvent) {
        cmbBoxLernerID.getSelectionModel().clearSelection();
        cmbBoxLernerID.getItems().clear();
        cmbBoxStdId.getSelectionModel().clearSelection();
        albAddress.setText("");
        lblName.setText("");
        lblTele.setText("");
        lblMarkingStats.setText("");
        btnLearningStu.setStyle("-fx-border-color:red;"+"-fx-background-color: linear-gradient(to right, #4ca1af, #c4e0e5);"+"-fx-border-width:3px");
        btnTrainingStu.setStyle("-fx-border-color:Transparent;"+"-fx-background-color: linear-gradient(to right, #4ca1af, #c4e0e5);"+"-fx-border-width:3px");
        selected=1;
        //Load All Student IDs(St_ID)
        cmbBoxStdId.setItems(obListSt_ID);
    }

    public void btnTrainingStuOnAction(ActionEvent actionEvent) {
        cmbBoxLernerID.getSelectionModel().clearSelection();
        cmbBoxStdId.getSelectionModel().clearSelection();
        cmbBoxLernerID.getItems().clear();
        albAddress.setText("");
        lblName.setText("");
        lblTele.setText("");
        lblMarkingStats.setText("");
        btnTrainingStu.setStyle("-fx-border-color:red;"+"-fx-background-color: linear-gradient(to right, #4ca1af, #c4e0e5);"+"-fx-border-width:3px");
        btnLearningStu.setStyle("-fx-border-color:Transparent;"+"-fx-background-color: linear-gradient(to right, #4ca1af, #c4e0e5);"+"-fx-border-width:3px");
        selected=2;
        //Load All Ex.Written Student Ids(Stx_ID)
        cmbBoxStdId.setItems(obListStx_ID);
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
    public List<String> getTeacherIDs(String ST_ID) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT T_ID FROM TeacherDetail WHERE St_ID=?");
        stm.setObject(1, ST_ID);
        ResultSet rst = stm.executeQuery();
        List<String>ids=new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }
    public List<String> getInstructorIds(String ST_ID) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT Ins_ID FROM InstructorDetails WHERE Stx_ID=?");
        stm.setObject(1, ST_ID);
        ResultSet rst = stm.executeQuery();
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
