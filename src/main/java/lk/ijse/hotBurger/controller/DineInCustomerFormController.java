package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.ijse.hotBurger.dto.CustomerDto;

public class DineInCustomerFormController {

    @FXML
    private JFXButton closeButton;

    @FXML
    private TextField dineInCusFName;

    @FXML
    private TextField dineInCusLName;

    @FXML
    private TextField dineInCusMobileNo;

    static CustomerDto customerDto = new CustomerDto();

    DuplicateMethodController duplicate = new DuplicateMethodController();
    public void dineInCloseButtonOnAction(ActionEvent actionEvent) {
        duplicate.clickButtonCloseWindow(closeButton);
    }

    public void DineInConfirmOnAction(ActionEvent actionEvent) {
        customerDto.setId(0);
        customerDto.setFName(dineInCusFName.getText());
        customerDto.setLName(dineInCusLName.getText());
        customerDto.setMobile(dineInCusMobileNo.getText());
    }
}
