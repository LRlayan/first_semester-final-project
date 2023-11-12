package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hotBurger.dto.tm.CustomerTm;
import java.io.IOException;

public class DeliveryFormController {

    @FXML
    private CheckBox checkBoNewCustomer;

    @FXML
    private CheckBox checkboxPrimaryAndDeliveryAddress;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private AnchorPane loadNewAddressAnchorpane;

    @FXML
    private AnchorPane loadSearchBarAnchorpane;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtAdditionalMobile;

    @FXML
    private TextArea txtAreaAddress;

    @FXML
    private TextField txtMobileNo;


    DuplicateMethodController duplicate = new DuplicateMethodController();

    CustomerTm customerTm = new CustomerTm();

    public void initialize() throws IOException {
        setCheckBoxDefaultSelected();
    }
    public void deliveryDetailConfirmBtnOnAction(javafx.event.ActionEvent actionEvent) {
       duplicate.clickButtonCloseWindow(btnConfirm);

    }

    public void setCheckBoxDefaultSelected() {
        checkBoNewCustomer.setSelected(true);
        checkboxPrimaryAndDeliveryAddress.setSelected(true);
    }

    public void clearField () {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAreaAddress.setText("");
        txtMobileNo.setText("");
        txtAdditionalMobile.setText("");
    }

    public void newCustomerCheckBoxOnAction(MouseEvent mouseEvent) throws IOException {
        if (!checkBoNewCustomer.isSelected()){
            duplicate.changeOnlyAnchorPane("/view/searchBar.fxml" , loadSearchBarAnchorpane);
           txtFirstName.setEditable(false);
           txtLastName.setEditable(false);
           txtMobileNo.setEditable(false);
           txtAreaAddress.setEditable(false);

        }else if(checkBoNewCustomer.isSelected()){
           duplicate.changeOnlyAnchorPane("/view/emptyPane.fxml" , loadSearchBarAnchorpane );
            txtFirstName.setEditable(true);
            txtLastName.setEditable(true);
            txtMobileNo.setEditable(true);
            txtAreaAddress.setEditable(true);

        }
    }

    public void primaryAndDeliveryAddressCheckBox(MouseEvent mouseEvent) throws IOException {

        if (!checkboxPrimaryAndDeliveryAddress.isSelected()) {
            duplicate.changeOnlyAnchorPane("/view/newAddressAfterNotSelectCheckBox.fxml", loadNewAddressAnchorpane);
        } else if (checkboxPrimaryAndDeliveryAddress.isSelected()) {
            duplicate.changeOnlyAnchorPane("/view/emptyPane.fxml" , loadNewAddressAnchorpane);
        }
    }
}
