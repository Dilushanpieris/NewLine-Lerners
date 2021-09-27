package Controller;


import Db.DbConnection;
import Model.TableModel.IncomeTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewIncomeReportsController {
    public AnchorPane viewIncomeReportsContext;
    public TableView<IncomeTM> incomeTableContext;
    public TableColumn colPayId;
    public TableColumn colStId;
    public TableColumn colPayDate;
    public TableColumn colAmount;
    public Label lblTotal;

    public void initialize() throws SQLException, ClassNotFoundException {
        //Load Table Data Of Income
        colPayId.setCellValueFactory(new PropertyValueFactory<>("pay_ID"));
        colStId.setCellValueFactory(new PropertyValueFactory<>("st_ID"));
        colPayDate.setCellValueFactory(new PropertyValueFactory<>("pay_Date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        loadPaymentDetails();
        lblTotal.setText(String.valueOf(getSumofIncome()));

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
    public void loadPaymentDetails() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM PaymentDetails");
        ResultSet rst = stm.executeQuery();
        ObservableList<IncomeTM> incometm= FXCollections.observableArrayList();
        while (rst.next()){
            String St_ID= rst.getString(4);
            if(St_ID.equals("NULL")){
                St_ID=rst.getString(5);
            }
            incometm.add(new IncomeTM(rst.getString(1),St_ID,rst.getString(2),rst.getDouble(3)));
        }
        incomeTableContext.setItems(incometm);
    }
    public void btnCloseOnAction(ActionEvent actionEvent) throws IOException {
        viewIncomeReportsContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/AdminDash.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }
}
