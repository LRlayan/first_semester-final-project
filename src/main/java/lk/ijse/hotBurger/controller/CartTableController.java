package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hotBurger.dto.DeliveryDto;
import lk.ijse.hotBurger.dto.OrderDetailsDto;
import lk.ijse.hotBurger.dto.OrderDto;
import lk.ijse.hotBurger.model.CustomerModel;
import lk.ijse.hotBurger.model.DeliveryModel;
import lk.ijse.hotBurger.model.OrderModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CartTableController implements Initializable {
    public static OrderDto order = new OrderDto();
    public static List<OrderDetailsDto> orderDetails = new ArrayList<>();
    ObservableList<OrderDetailsDto> observableList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<OrderDetailsDto> tbl;

    @FXML
    private Label lblDiscount;

    @FXML
    private Label lblSubTotal;

    @FXML
    private Label lblTotal;

    @FXML
    private JFXButton btnDelivery;

    @FXML
    private JFXButton btnDineIn;

    @FXML
    private JFXButton btnPickUp;

    DuplicateMethodController duplicate = new DuplicateMethodController();

    CustomerModel customerModel = new CustomerModel();

    DeliveryModel deliveryModel = new DeliveryModel();

    OrderModel orderModel = new OrderModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setValueFactory();
        loardClickItemDetail();
        lblSubTotal.setText("Rs : " + order.getSubTotal());
        if (order.getSubTotal() > 5800) {
            lblDiscount.setText("Rs : " + (order.getSubTotal() * 14/100));
            lblTotal.setText("Rs : " + ((order.getSubTotal()) - (order.getSubTotal() * 14/100)));
        } else {
            lblTotal.setText("Rs : " + order.getSubTotal());
        }
    }

    public void setValueFactory() {
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
    }

    public void loardClickItemDetail() {
        if (order.getOrderItem() != null) {
            order.getOrderItem().forEach(orderDetailsDto -> {
                observableList.add(orderDetailsDto);
            });
        }
        tbl.setItems(observableList);
    }

    public void btnDeliveryDetailOnAction(ActionEvent actionEvent) throws IOException {
        duplicate.clickButtonChangeColor(btnDelivery, btnPickUp, btnDineIn);
        duplicate.popUpWindow("/view/delivery_form.fxml");
    }

    public void clickOnPickUpBtnAction(ActionEvent actionEvent) {
        duplicate.clickButtonChangeColor(btnPickUp, btnDelivery, btnDineIn);
    }

    public void clickOnDineInBtnAction(ActionEvent actionEvent) {
        duplicate.clickButtonChangeColor(btnDineIn, btnPickUp, btnDelivery);
    }
    @FXML
    void placeOrderOnAction(ActionEvent event)  {
        try {
            customerModel.saveCustomer(DeliveryFormController.customerDto);
            if (DeliveryFormController.customerDto.getId()!= 0){

                DeliveryFormController.deliveryDto.setCustomerId(DeliveryFormController.customerDto.getId());
                deliveryModel.saveDelivery(DeliveryFormController.deliveryDto);

                if (DeliveryFormController.deliveryDto.getId() != 0){
                    ManageOrderFormController.orderDto.setCustomerId(DeliveryFormController.customerDto.getId());
                    orderModel.saveOrder(order);
                    if (ManageOrderFormController.orderDto.getId() !=0) {
                        new Alert(Alert.AlertType.INFORMATION,"order success!").show();
                    }
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
}
