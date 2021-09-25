package Controller;

import Db.DbConnection;
import Model.Payment;
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

public class MarkPaymentFormController {
    public JFXComboBox cmbStdID;
    public Label lblStdName;
    public Label lblAge;
    public Label lblAddress;
    public Label lblTelephone;
    public JFXTextField txtAmount;
    public AnchorPane makePaymentContext;

    public void initialize(){
        ObservableList<String>obList= FXCollections.observableArrayList();
        try {
            for (String temp:getAllStudentIds()
                 ) {
                obList.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbStdID.setItems(obList);

    }

    public void AmountValidation(KeyEvent keyEvent) {
        //Validation
    }

    public void CloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) makePaymentContext.getScene().getWindow();
        stage.close();
    }

    public void markPaymentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //get Lst ID
        String TempPayID="";
        Connection con= DbConnection.getInstance().getConnection();
        ResultSet rst=con.prepareStatement("SELECT Pay_ID FROM PaymentDetails WHERE Pay_ID=(SELECT max(Pay_ID) FROM PaymentDetails)").executeQuery();
        if(rst.next()){
            String lastID=rst.getString(1);
            String inc=lastID.substring(lastID.length()-1);
            int incInt=Integer.parseInt(inc);
            incInt++;
            TempPayID="Pay00"+incInt;
        }
        else{
            TempPayID="Pay001";
        }
        //Save Payment Details
       double amount= Double.parseDouble(txtAmount.getText());
       String selectedId= (String) cmbStdID.getValue();
       Payment p1=new Payment(TempPayID,DateNow(),amount,selectedId);
       PreparedStatement stm=con.prepareStatement("INSERT INTO PaymentDetails VALUES(?,?,?,?,?)");
       stm.setObject(1,p1.getPayID());
       stm.setObject(2,p1.getDate());
       stm.setObject(3,p1.getAmount());
       stm.setObject(4,p1.getStId());
       stm.setObject(5,"NULL");

       if(stm.executeUpdate()>0){
           new Alert(Alert.AlertType.CONFIRMATION,"Successfully Saved").showAndWait();
           makePaymentContext.getScene().getWindow().hide();
       }
       else {
           new Alert(Alert.AlertType.WARNING,"Try_Again").show();
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

    public void cmbStdIDOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //load All Details.
        Connection con=DbConnection.getInstance().getConnection();
        PreparedStatement stm=con.prepareStatement("SELECT * FROM Student WHERE St_ID=?");
        String selected= (String) cmbStdID.getValue();
        stm.setObject(1,selected);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            lblStdName.setText(rst.getString(2));
            lblAge.setText(String.valueOf(rst.getInt(3)));
            lblAddress.setText(rst.getString(6));
            lblTelephone.setText(rst.getString(7));
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Empty Set").show();
        }
    }
    public String DateNow(){
        Date date=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        return f.format(date);
    }

    public void OpenExwrittenStuPayment(ActionEvent actionEvent) throws IOException {
        makePaymentContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/UserDashContent/PaymentExWrittenStu.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}
