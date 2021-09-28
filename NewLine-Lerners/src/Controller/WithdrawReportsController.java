package Controller;

import Db.DbConnection;
import Model.TableModel.IncomeTM;
import Model.TableModel.WithdrawTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class WithdrawReportsController {
    public AnchorPane viewWithdrawReportsContext;
    public TableView withdrawTableContext;
    public TableColumn colWithID;
    public TableColumn colWithDate;
    public TableColumn colWithAmount;
    public TableColumn colWithBalance;

    public void initialize() throws SQLException, ClassNotFoundException {
        colWithID.setCellValueFactory(new PropertyValueFactory<>("with_id"));
        colWithDate.setCellValueFactory(new PropertyValueFactory<>("with_Date"));
        colWithAmount.setCellValueFactory(new PropertyValueFactory<>("with_Amount"));
        colWithBalance.setCellValueFactory(new PropertyValueFactory<>("with_Balance"));

        loadAllWithdrawlData();

    }
    public void loadAllWithdrawlData() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM Withdrawal_Data");
        ResultSet rst = stm.executeQuery();
        ObservableList<WithdrawTM> withdrawTm= FXCollections.observableArrayList();
        while (rst.next()){
            withdrawTm.add(new WithdrawTM(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getDouble(4)));
        }
        withdrawTableContext.setItems(withdrawTm);
    }

    public void closeOnACtion(ActionEvent actionEvent) throws IOException {
        viewWithdrawReportsContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/AdminDash.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }
}
