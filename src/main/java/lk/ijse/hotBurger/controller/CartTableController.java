package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import lk.ijse.hotBurger.dto.DeliveryDto;
import lk.ijse.hotBurger.dto.OrderDetailsDto;
import lk.ijse.hotBurger.dto.OrderDto;
import lk.ijse.hotBurger.dto.tm.OrderDetailsTm;
import lk.ijse.hotBurger.model.CustomerModel;
import lk.ijse.hotBurger.model.DeliveryModel;
import lk.ijse.hotBurger.model.OrderModel;
import static javafx.scene.input.MouseEvent.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    private TableColumn<?, ?> colChangeQty;

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

    @FXML
    private Label lblDeliveryCharge;


    int selectButton;

    DuplicateMethodController duplicate = new DuplicateMethodController();

    CustomerModel customerModel = new CustomerModel();

    DeliveryModel deliveryModel = new DeliveryModel();

    OrderModel orderModel = new OrderModel();

    OrderDto orderDto = new OrderDto();

    //private static String total;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setValueFactory();
        loardClickItemDetail();
        minusButtonToTable();

        orderDto.setDeliveryCharge(150);

        order.setDate(DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(LocalDateTime.now()));
        lblSubTotal.setText("Rs : " + order.getSubTotal());
        order.setSubTotal(Double.parseDouble(String.valueOf(order.getSubTotal())));

        if (order.getSubTotal() > 5800) {

            lblDiscount.setText("Rs : " + (order.getSubTotal() * 14 / 100));
            order.setDiscount((order.getSubTotal() * 14 / 100));

            lblTotal.setText(("Rs : " + ((order.getSubTotal()) - (order.getSubTotal() * 14 / 100) + (orderDto.getDeliveryCharge()))));
            // total = ("Rs : " + ((order.getSubTotal()) - (order.getSubTotal() * 14/100) + (orderDto.getDeliveryCharge())));
            order.setTotal((order.getSubTotal()) - (order.getSubTotal() * 14 / 100));
            // order.setTotal(Double.parseDouble(total));
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

                observableList.add(
                        orderDetailsDto
                );
            });
        }
        tbl.setItems(observableList);
    }

    public void btnDeliveryDetailOnAction(ActionEvent actionEvent) throws IOException {

        duplicate.clickButtonChangeColor(btnDelivery, btnPickUp, btnDineIn);
        duplicate.popUpWindow("/view/delivery_form.fxml");
        order.setType("DELIVERY");
        order.setDeliveryCharge(150);
        order.setTotal(order.getSubTotal() + order.getDeliveryCharge());
        lblDeliveryCharge.setText(String.valueOf(order.getDeliveryCharge()));
        lblTotal.setText("Rs : " + order.getTotal());

        selectButton++;
    }

    public void clickOnPickUpBtnAction(ActionEvent actionEvent) throws IOException {
        duplicate.popUpWindow("/view/dineInCustomer_form.fxml");
        selectButton = 0;
        duplicate.clickButtonChangeColor(btnPickUp, btnDelivery, btnDineIn);
        order.setType("PICK UP");
        order.setDeliveryCharge(0);
        lblDeliveryCharge.setText("Rs : " + 0.00);
        order.setTotal(order.getSubTotal() + order.getDeliveryCharge());
        lblTotal.setText("Rs : " + order.getTotal());
    }

    public void clickOnDineInBtnAction(ActionEvent actionEvent) throws IOException {
        duplicate.popUpWindow("/view/dineInCustomer_form.fxml");
        selectButton = 0;
        duplicate.clickButtonChangeColor(btnDineIn, btnPickUp, btnDelivery);

        order.setType("DINE IN");
        order.setDeliveryCharge(0);
        lblDeliveryCharge.setText("Rs : " + 0.00);
        order.setTotal(order.getSubTotal() + order.getDeliveryCharge());
        lblTotal.setText("Rs : " + order.getTotal());

    }

    @FXML
    void placeOrderOnAction(ActionEvent event) throws SQLException {

        try {
            if (selectButton > 0) {
                customerModel.saveCustomer(DeliveryFormController.customerDto);

                if (DeliveryFormController.customerDto.getId() != 0) {

                    DeliveryFormController.deliveryDto.setCustomerId(DeliveryFormController.customerDto.getId());
                    deliveryModel.saveDelivery(DeliveryFormController.deliveryDto);

                    if (DeliveryFormController.deliveryDto.getId() != 0) {
                        order.setCustomerId(DeliveryFormController.customerDto.getId());
                        orderModel.saveOrder(order);
                        if (ManageOrderFormController.orderDto.getId() != 0) {
                            new Alert(Alert.AlertType.INFORMATION, "Order created successfully!").show();

                        }
                    }
                }
            } else if (selectButton == 0) {
                dineInAndPickUpCustomer();

            } else if (selectButton == 0) {
                dineInAndPickUpCustomer();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void dineInAndPickUpCustomer() throws SQLException {
        customerModel.dineCustomerSave(DineInCustomerFormController.customerDto);
        if (DineInCustomerFormController.customerDto.getId() != 0) {
            order.setCustomerId(DineInCustomerFormController.customerDto.getId());
            orderModel.saveOrder(order);
        }
    }

    private void minusButtonToTable() { //add column and change qty button
        TableColumn<OrderDetailsDto, Void> colBtn = new TableColumn("Change Qty");
        Callback<TableColumn<OrderDetailsDto, Void>, TableCell<OrderDetailsDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<OrderDetailsDto, Void> call(final TableColumn<OrderDetailsDto, Void> param) {
                final TableCell<OrderDetailsDto, Void> cell = new TableCell<OrderDetailsDto, Void>() {
                    private final JFXButton btn = new JFXButton(" - ");

                    {
                        btn.setOnAction((ActionEvent event) -> {


                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            btn.setCursor(Cursor.HAND);
                            btn.setStyle("-fx-background-color: #42c923");
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        tbl.getColumns().add(colBtn);
    }
}
