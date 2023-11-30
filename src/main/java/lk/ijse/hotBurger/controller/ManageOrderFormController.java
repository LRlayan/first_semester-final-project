package lk.ijse.hotBurger.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.dto.OrderDto;
import lk.ijse.hotBurger.dto.tm.ItemTm;
import lk.ijse.hotBurger.dto.tm.OrderTm;
import lk.ijse.hotBurger.model.OrderModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ManageOrderFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDeliveryCharge;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colSubTotal;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<OrderTm> orderTable;

    static OrderDto orderDto = new OrderDto();

    OrderModel orderModel = new OrderModel();

    ObservableList<OrderTm> obList = FXCollections.observableArrayList();

    public void loadAllOrders(){
        try{
          List<OrderDto> orderDto = orderModel.loadAllOrders();
            for (OrderDto dto : orderDto) {
                 obList.add(new OrderTm(
                    dto.getId(),
                    dto.getSubTotal(),
                    dto.getDeliveryCharge(),
                    dto.getDiscount(),
                    dto.getTotal(),
                    dto.getDate(),
                    dto.getCustomerId(),
                    dto.getType()
                 ));
            }
            orderTable.setItems(obList);
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void setCellValueFactory(){
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSubTotal.setCellValueFactory(new PropertyValueFactory<>("SubTotal"));
        colDeliveryCharge.setCellValueFactory(new PropertyValueFactory<>("deliveryCharge"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllOrders();
        setCellValueFactory();
    }
}
