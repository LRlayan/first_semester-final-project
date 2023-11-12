package lk.ijse.hotBurger.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.ijse.hotBurger.dto.CustomerDto;
import lk.ijse.hotBurger.dto.tm.CustomerTm;
import lk.ijse.hotBurger.model.CustomerModel;

import java.sql.SQLException;
import java.util.List;

public class CustomerDetailsFormController {

    @FXML
    private TableColumn<?, ?> addressLine01;

    @FXML
    private TableColumn<?, ?> addressLine02;

    @FXML
    private TableColumn<?, ?> addressLine03;

    @FXML
    private TableColumn<?, ?> customerId;

    @FXML
    private TableColumn<?, ?> customerId1;

    @FXML
    private TableView<?> customerTable;

    @FXML
    private TableColumn<?, ?> firstName;

    @FXML
    private TableColumn<?, ?> lastName;

    @FXML
    private TableColumn<?, ?> phone1;

    @FXML
    private TableColumn<?, ?> phone2;

   /* public void loadAllCustomerDetails(){
        var model = new CustomerModel();

        try {
            List<CustomerDto> dtoList = model.getAllCustomer();

            for ( CustomerDto customerDto : dtoList ) {
                new CustomerTm(
                        customerDto.getId(),
                        customerDto.getfName(),
                        customerDto.getlName(),
                        customerDto.getAddressLine3(),
                        customerDto.getPhone1()
                );
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }*/
}
