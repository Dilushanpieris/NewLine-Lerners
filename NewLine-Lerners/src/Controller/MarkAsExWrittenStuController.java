package Controller;

import Db.DbConnection;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarkAsExWrittenStuController {
    public AnchorPane markFormExWrittenContext;
    public JFXComboBox cmbBoxStid;
    public JFXTextField txtName;
    public JFXTextField txtAge;
    public JFXTextField txtVehicleType;
    public JFXTextField txtEmail;
    public JFXTextField txtAddress;
    public JFXTextField txtTel;
    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList= FXCollections.observableArrayList();
        //load All IDs
        for (String temp:getAllStudentIds()) {
            obList.add(temp);
        }
        cmbBoxStid.setItems(obList);
    }

    public void btnClose(ActionEvent actionEvent) throws IOException {
        markFormExWrittenContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/UserDashContent/CheckExamEligibility.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
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

    public void cmbBoxStidOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //load All Details
        Connection con=DbConnection.getInstance().getConnection();
        PreparedStatement stm=con.prepareStatement("SELECT * FROM Student WHERE St_ID=?");
        String selected= (String) cmbBoxStid.getValue();
        stm.setObject(1,selected);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            txtName.setText(rst.getString(2));
            txtAge.setText(rst.getString(3));
            txtVehicleType.setText(rst.getString(4));
            txtEmail.setText(rst.getString(5));
            txtAddress.setText(rst.getString(6));
            txtTel.setText(rst.getString(7));
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Empty Set").show();
        }

    }

    public void MarkAsExWrittenStuOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //Make new STx Id
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
        //Add Data to Ex Written Stu
        String query="INSERT INTO ExamWrittenStudent VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,TempID);
        stm.setObject(2,txtName.getText());
        stm.setObject(3,txtAge.getText());
        stm.setObject(4,txtVehicleType.getText());
        stm.setObject(5,txtEmail.getText());
        stm.setObject(6,txtAddress.getText());
        stm.setObject(7,txtTel.getText());
        //delete Student Data.
        String delQuery="DELETE FROM Student WHERE St_ID=?";
        PreparedStatement delStm=con.prepareStatement(delQuery);
        String id=(String)cmbBoxStid.getValue();
        delStm.setObject(1,id);
        if(delStm.executeUpdate()>0&stm.executeUpdate()>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Saved As Exam Written Student").showAndWait();
            markFormExWrittenContext.getScene().getWindow().hide();
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try_Again").show();
        }
    }
}
