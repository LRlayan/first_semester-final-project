package lk.ijse.hotBurger.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hotBurger.dto.CustomerDto;
import lk.ijse.hotBurger.dto.tm.CustomerTm;
import lk.ijse.hotBurger.model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerDetailsFormController implements Initializable {
    @FXML
    private TableColumn<?, ?> address;

    @FXML
    private TableColumn<?, ?> customerId;

    @FXML
    private TableView<CustomerTm> customerTable;

    @FXML
    private TableColumn<?, ?> firstName;

    @FXML
    private TableColumn<?, ?> lastName;

    @FXML
    private TableColumn<?, ?> mobile;

    CustomerModel customerModel = new CustomerModel();

    ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllCustomers();
        setCellValueFactory();
    }

    public void loadAllCustomers(){

        try{
            List<CustomerDto> customerDto = customerModel.getAllCustomers();
            for (CustomerDto dto : customerDto) {
                obList.add(new CustomerTm(
                        dto.getId(),
                        dto.getFName(),
                        dto.getLName(),
                        dto.getAddress(),
                        dto.getMobile()
                ));
            }
            customerTable.setItems(obList);
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    public void setCellValueFactory(){
        customerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lName"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    }
}
