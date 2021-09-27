package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AdminDashController {
    public AnchorPane AdminFormContext;



    public void btnClose(ActionEvent actionEvent) throws IOException {
        AdminFormContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/MainLogin.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    public void btnAddLernerOnAction(MouseEvent mouseEvent) throws IOException {
        AdminFormContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/AdminDashContent/AddLerner.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    public void btnViewIncomeReportsOnAction(MouseEvent mouseEvent) throws IOException {
        AdminFormContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/AdminDashContent/ViewIncomeReports.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    public void btnViewWithdrawReportsOnAction(MouseEvent mouseEvent) {


    }

    public void btnWithdrawOnAction(MouseEvent mouseEvent) throws IOException {
        AdminFormContext.getScene().getWindow().hide();
        Parent load = FXMLLoader.load(getClass().getResource("../View/AdminDashContent/WithdrawIncome.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }
}
