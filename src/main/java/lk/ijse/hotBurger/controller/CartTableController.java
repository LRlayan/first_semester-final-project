package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.OrderDetailsDto;
import lk.ijse.hotBurger.dto.OrderDto;
import lk.ijse.hotBurger.model.CustomerModel;
import lk.ijse.hotBurger.model.DeliveryModel;
import lk.ijse.hotBurger.model.OrderDetailsModel;
import lk.ijse.hotBurger.model.OrderModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
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

    OrderDetailsModel orderDetailsModel = new OrderDetailsModel();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setValueFactory();
        loardClickItemDetail();
        minusButtonToTable();

        orderDto.setDeliveryCharge(0);

        order.setDate(DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(LocalDateTime.now()));

        lblSubTotal.setText(String.valueOf(order.getSubTotal()));
        order.setSubTotal(Double.parseDouble(String.valueOf(order.getSubTotal())));
        setOrderTotalLabelsAndDto(order.getSubTotal());
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
        order.setType("DELIVERY");
        order.setDeliveryCharge(150);
        setOrderTotalLabelsAndDto(order.getSubTotal());
        selectButton++;
    }

    public void clickOnPickUpBtnAction(ActionEvent actionEvent) throws IOException {
        duplicate.popUpWindow("/view/dineInCustomer_form.fxml");
        selectButton = 0;
        duplicate.clickButtonChangeColor(btnPickUp, btnDelivery, btnDineIn);
        order.setType("PICK UP");
        order.setDeliveryCharge(0);
        setOrderTotalLabelsAndDto(order.getSubTotal());
    }

    public void clickOnDineInBtnAction(ActionEvent actionEvent) throws IOException {
        duplicate.popUpWindow("/view/dineInCustomer_form.fxml");
        selectButton = 0;
        duplicate.clickButtonChangeColor(btnDineIn, btnPickUp, btnDelivery);

        order.setType("DINE IN");
        order.setDeliveryCharge(0);
        setOrderTotalLabelsAndDto(order.getSubTotal());
    }

    @FXML
    void placeOrderOnAction(ActionEvent event) throws SQLException, JRException {

        try {
            if (selectButton > 0) {
                customerModel.saveCustomer(DeliveryFormController.customerDto);

                if (DeliveryFormController.customerDto.getId() != 0) {

                    DeliveryFormController.deliveryDto.setCustomerId(DeliveryFormController.customerDto.getId());
                    deliveryModel.saveDelivery(DeliveryFormController.deliveryDto);

                    if (DeliveryFormController.deliveryDto.getId() != 0) {
                        order.setCustomerId(DeliveryFormController.customerDto.getId());
                        orderModel.saveOrder(order);

                        if (order.getId() != 0) {
                            orderDetails.forEach(orderDetailsDto -> {
                                try {
                                    orderDetailsDto.setOrderId(order.getId());
                                    orderDetailsModel.saveOrderDetail(orderDetailsDto);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            billGenerate(order.getId());
                            clearOrder();
                        }
                    }
                }
            } else if (selectButton == 0) {
                dineInAndPickUpCustomer();
                clearOrder();

            } else if (selectButton == 0) {
                dineInAndPickUpCustomer();
                clearOrder();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void dineInAndPickUpCustomer() throws SQLException, JRException {
        customerModel.dineCustomerSave(DineInCustomerFormController.customerDto);
        if (DineInCustomerFormController.customerDto.getId() != 0) {
            order.setCustomerId(DineInCustomerFormController.customerDto.getId());
            orderModel.saveOrder(order);
            if (order.getId() != 0) {
                orderDetails.forEach(orderDetailsDto -> {
                    try {
                        orderDetailsDto.setOrderId(order.getId());
                        orderDetailsModel.saveOrderDetail(orderDetailsDto);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                billGenerate(order.getId());
                clearOrder();
                //new Alert(Alert.AlertType.INFORMATION, "Order created successfully!").show();
            }
        }
    }

    private void minusButtonToTable() { //add column and change qty button
        TableColumn<OrderDetailsDto, Void> colBtn = new TableColumn("Qty(-)  ");
        Callback<TableColumn<OrderDetailsDto, Void>, TableCell<OrderDetailsDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<OrderDetailsDto, Void> call(final TableColumn<OrderDetailsDto, Void> param) {
                final TableCell<OrderDetailsDto, Void> cell = new TableCell<>() {
                    private final JFXButton btn = new JFXButton(" - ");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            OrderDetailsDto orderDetailsDto = getTableView().getItems().get(getIndex());
                            if (orderDetailsDto.getQty() > 1) {
                                orderDetailsDto.setQty(orderDetailsDto.getQty() - 1);
                                orderDetailsDto.setTotal(orderDetailsDto.getUnitPrice() * orderDetailsDto.getQty());
                                getTableView().refresh();
                                changeItemValues();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            btn.setCursor(Cursor.HAND);
                            btn.setStyle("-fx-background-color: #e66767");
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

    private void changeItemValues() {
        double total = 0;
        for (OrderDetailsDto orderDetailsDto : order.getOrderItem()) {
            total += orderDetailsDto.getTotal();
        }
        setOrderTotalLabelsAndDto(total);
    }

    private void setOrderTotalLabelsAndDto(double total) {
        if (total > 5800) {
            lblDiscount.setText(String.valueOf(total * 14 / 100));
            order.setDiscount(total * 14 / 100);
            lblTotal.setText(String.valueOf((((total) - (total * 14 / 100) + (order.getDeliveryCharge())))));
            order.setTotal((((total) - (total * 14 / 100) + (order.getDeliveryCharge()))));
        } else {
            lblDiscount.setText("0.00");
            lblTotal.setText(String.valueOf(total + order.getDeliveryCharge()));
            lblSubTotal.setText(String.valueOf(total));
            order.setDiscount(0);
            order.setSubTotal(total);
            order.setTotal(total + order.getDeliveryCharge());
        }
        lblDeliveryCharge.setText(String.valueOf(order.getDeliveryCharge()));
    }

    private void clearOrder(){
        lblDeliveryCharge.setText(String.valueOf(0.0));
        lblSubTotal.setText(String.valueOf(0.0));
        lblTotal.setText(String.valueOf(0.0));
        lblDiscount.setText(String.valueOf(0.0));

        for (int i = 0; i < tbl.getItems().size(); i++){
            tbl.getItems().clear();
        }

        order.setOrderItem(null);
        order.setId(0);
        order.setType(null);
        order.setCustomerId(0);
        order.setTotal(0);
        order.setDiscount(0);
        order.setDeliveryCharge(0);
        order.setSubTotal(0);
        order.setDate(null);
        orderDetails.clear();
    }

    public void billGenerate(int orderId) throws JRException, SQLException {
        HashMap parameter = new HashMap();
        parameter.put("orderId", orderId);
        InputStream reportsAsStream = getClass().getResourceAsStream("/reports/generateBill.jrxml");
        JasperDesign load = JRXmlLoader.load(reportsAsStream);
        JasperReport jasperReport1 = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport1,
                parameter,
                DbConnection.getInstance().getConnection()
        );
        JasperViewer.viewReport(jasperPrint, false);
    }
}
