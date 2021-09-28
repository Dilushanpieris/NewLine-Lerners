package Controller;

import Db.DbConnection;
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
import java.util.ArrayList;
import java.util.List;

public class CheckExamEligibilityController {
    public AnchorPane checkEligibilityContext;
    public JFXComboBox cmbStdID;
    public Label lblName;
    public Label lblAge;
    public Label lblTel;
    public Label lblMarks1;
    public Label lblMarks2;
    public Label lblMarks3;
    public Label lblMarks4;
    public Label lblTotal;
    public Label lblAverage;
    public Label lblStatus;

    public void initialize(){
        ObservableList<String> obListSt_ID= FXCollections.observableArrayList();
        try {
            for (String temp:getAllStudentIds()) {
                obListSt_ID.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbStdID.setItems(obListSt_ID);

    }

    public void CloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) checkEligibilityContext.getScene().getWindow();
        stage.close();
    }

    public void cmbOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //Load All Details--Student
        if (cmbStdID.getValue() != null) {
            Connection con = DbConnection.getInstance().getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Student WHERE St_ID=?");
            String ID = (String) cmbStdID.getValue();
            stm.setObject(1, ID);
            ResultSet rst = stm.executeQuery();
            if (rst.next()) {
                lblName.setText(rst.getString(2));
                lblAge.setText(String.valueOf(rst.getInt(3)));
                lblTel.setText(rst.getString(7));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Set").show();
            }
            PreparedStatement stm2 = con.prepareStatement("SELECT * FROM ExamDetail WHERE St_ID=? AND Ex_ID=?");
            stm2.setObject(1,ID);
            stm2.setObject(2,"Ex001");
            ResultSet rst2 = stm2.executeQuery();
            if(rst2.next()){
                lblMarks1.setText(String.valueOf(rst2.getInt(4)));
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Marks Not Added Yet");
            }
            PreparedStatement stm3 = con.prepareStatement("SELECT * FROM ExamDetail WHERE St_ID=? AND Ex_ID=?");
            stm3.setObject(1,ID);
            stm3.setObject(2,"Ex002");
            ResultSet rst3 = stm3.executeQuery();
            if(rst3.next()){
                lblMarks2.setText(String.valueOf(rst3.getInt(4)));
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Marks Not Added Yet");
            }
            PreparedStatement stm4 = con.prepareStatement("SELECT * FROM ExamDetail WHERE St_ID=? AND Ex_ID=?");
            stm4.setObject(1,ID);
            stm4.setObject(2,"Ex003");
            ResultSet rst4 = stm4.executeQuery();
            if(rst4.next()){
                lblMarks3.setText(String.valueOf(rst4.getInt(4)));
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Marks Not Added Yet");
            }
            PreparedStatement stm5 = con.prepareStatement("SELECT * FROM ExamDetail WHERE St_ID=? AND Ex_ID=?");
            stm5.setObject(1,ID);
            stm5.setObject(2,"Ex004");
            ResultSet rst5 = stm5.executeQuery();
            if(rst5.next()){
                lblMarks4.setText(String.valueOf(rst5.getInt(4)));
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Marks Not Added Yet");
            }
        }
        int ex01= Integer.parseInt(lblMarks1.getText());
        int ex02= Integer.parseInt(lblMarks2.getText());
        int ex03= Integer.parseInt(lblMarks3.getText());
        int ex04= Integer.parseInt(lblMarks4.getText());
        int tot=ex01+ex02+ex03+ex04;
        double avg=tot/4;
        lblTotal.setText(String.valueOf(tot));
        lblAverage.setText(String.valueOf(avg));
        if(avg>70){
            lblStatus.setText("Eligible");
        }
        else{
            lblStatus.setText("Not Eligible");
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

    public void openMarkAsExWritten(ActionEvent actionEvent) throws IOException {
        checkEligibilityContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/UserDashContent/MarkAsExWrittenStu.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}
