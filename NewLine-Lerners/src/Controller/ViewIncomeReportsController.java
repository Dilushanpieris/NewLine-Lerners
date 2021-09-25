package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ViewIncomeReportsController {
    public AnchorPane viewIncomeReportsContext;
    public TableView incomeTableContext;
    public TableColumn colPayId;
    public TableColumn colStId;
    public TableColumn colPayDate;
    public TableColumn colAmount;
    public Label lblTotal;

    public void initialize(){
        //Load Table Data Of Income
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
