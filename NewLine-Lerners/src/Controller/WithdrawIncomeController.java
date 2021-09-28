package Controller;

import Db.DbConnection;
import Model.Withdraw;
import com.jfoenix.controls.JFXTextField;
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
import java.util.Date;

public class WithdrawIncomeController {
    public JFXTextField txtWithdrawAmount;
    public AnchorPane withdrawIncomeContext;
    public Label lblMaxAmount;
    public void initialize() throws SQLException, ClassNotFoundException {
        lblMaxAmount.setText(String.valueOf(getBalance()));
    }

    public void btnClose(ActionEvent actionEvent) throws IOException {
        withdrawIncomeContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/AdminDash.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    public void btnWithdraw(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        double amount= Double.parseDouble(txtWithdrawAmount.getText());
        if(amount>getBalance()){
            new Alert(Alert.AlertType.WARNING,"Requested Amount Is Invalid").show();
            return;
        }
        String TempID="";
        Connection con= DbConnection.getInstance().getConnection();
        ResultSet rst=con.prepareStatement("SELECT With_ID FROM Withdrawal_Data WHERE With_ID=(SELECT max(With_ID) FROM Withdrawal_Data)").executeQuery();
        if(rst.next()){
            String lastID=rst.getString(1);
            String inc=lastID.substring(lastID.length()-1);
            int incInt=Integer.parseInt(inc);
            incInt++;
            TempID="W00"+incInt;
        }
        else{
            TempID="W001";
        }
        //if Tot> If

        //Add With Data.
        double balance=getBalance()-amount;
        Withdraw w1=new Withdraw(TempID,DateNow(),amount,balance);
        String query="INSERT INTO Withdrawal_Data VALUES(?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,w1.getWith_id());
        stm.setObject(2,w1.getWith_date());
        stm.setObject(3,w1.getWith_Amount());
        stm.setObject(4,w1.getWith_balance());
        if(stm.executeUpdate()>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Withdrawal Data Added").showAndWait();
            lblMaxAmount.setText(String.valueOf(getBalance()));
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try_Again").show();
        }

    }
    public double getBalance() throws SQLException, ClassNotFoundException {
        double balance;
        Connection con= DbConnection.getInstance().getConnection();
        ResultSet rst=con.prepareStatement("SELECT With_Balance FROM Withdrawal_Data WHERE With_Balance=(SELECT MIN(With_Balance) FROM Withdrawal_Data)").executeQuery();
        if(rst.next()){
            return rst.getDouble(1);
        }
        else{
            return getSumofIncome();
        }

    }
    public String DateNow(){
        Date date=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        return f.format(date);
    }
    public double getSumofIncome() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT SUM(Pay_amount) FROM Paymentdetails");
        ResultSet rst = stm.executeQuery();
        double tot=0.0;
        if(rst.next()){
            tot=rst.getDouble(1);
        }
        else{
            tot=0.00;
        }
        return tot;
    }

    public void ValidateKey(KeyEvent keyEvent) {
    }
}
