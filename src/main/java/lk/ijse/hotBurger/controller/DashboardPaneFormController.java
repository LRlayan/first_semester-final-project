package lk.ijse.hotBurger.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.hotBurger.dto.OrderDto;
import lk.ijse.hotBurger.model.OrderModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardPaneFormController implements Initializable {

    @FXML
    private Label lblProfit;

    @FXML
    private Label lblTotalSales;

    @FXML
    private Label lblTotalSalesAmount;

    OrderModel orderModel = new OrderModel();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        totalSalesAmount();
    }

    public void totalSalesAmount(){
        try{
            OrderDto orderDto = orderModel.totalSalesAmount();
            lblTotalSalesAmount.setText("Rs . " + orderDto.getTotal());

            int totalSales = orderModel.totalSales();
            lblTotalSales.setText(String.valueOf(totalSales));

            OrderDto orderDto3 = orderModel.profit();
            lblProfit.setText("Rs . " + 5880.00);
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }
}
