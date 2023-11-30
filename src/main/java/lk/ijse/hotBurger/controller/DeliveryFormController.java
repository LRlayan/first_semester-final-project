package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.hotBurger.dto.CustomerDto;
import lk.ijse.hotBurger.dto.DeliveryDto;
import lk.ijse.hotBurger.dto.tm.CustomerTm;
import lk.ijse.hotBurger.model.CustomerModel;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryFormController {

    @FXML
    private CheckBox checkBoNewCustomer;

    @FXML
    private CheckBox checkboxPrimaryAndDeliveryAddress;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtAdditionalMobile;

    @FXML
    private TextArea txtAreaAddress;

    @FXML
    private TextField searchBarAutoTextField;

    @FXML
    private TextField learnTextField;

    @FXML
    private TextField txtMobileNo;

    @FXML
    private Label lblNewAddress;

    @FXML
    private JFXTextArea txtAdditionalAddress;


    @FXML
    private JFXButton closeButton;

    public DeliveryFormController() throws SQLException {
    }

    DuplicateMethodController duplicate = new DuplicateMethodController();

    CustomerModel customerModel = new CustomerModel();

    static CustomerDto customerDto = new CustomerDto();

    static DeliveryDto deliveryDto = new DeliveryDto();

    private AutoCompletionBinding<String> autoCompletionBinding;

    private List<CustomerDto> customerDtoList = customerModel.getAllCustomers();

    private Set<String> _customerDtoLis = new HashSet<>();

    public void initialize() throws IOException {
        customerDtoList.forEach(customerDto -> {
            _customerDtoLis.add(customerDto.getId()+ " - " +customerDto.getFName() + " " + customerDto.getLName());
        });
        setCheckBoxDefaultSelected();
        searchBarAutoTextField.setVisible(false);
        txtAdditionalAddress.setVisible(false);
        lblNewAddress.setVisible(false);


        TextFields.bindAutoCompletion(searchBarAutoTextField,_customerDtoLis);
        autoCompletionBinding = TextFields.bindAutoCompletion( learnTextField,_customerDtoLis);
        learnTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case ENTER:
                    autoCompletionLearnword(learnTextField.getText().trim());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void autoCompletionLearnword(String newWord){
        System.out.println(customerDtoList);
        _customerDtoLis.add(newWord);

        if (autoCompletionBinding != null){
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(learnTextField , _customerDtoLis);
    }
    public void deliveryDetailConfirmBtnOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            customerDto.setId(0);
            customerDto.setFName(txtFirstName.getText());
            customerDto.setLName(txtLastName.getText());
            customerDto.setAddress(txtAreaAddress.getText());
            customerDto.setMobile(txtMobileNo.getText());
            deliveryDto.setAddress(txtAdditionalAddress.getText());
            deliveryDto.setAdditionalMobileNo(txtAdditionalMobile.getText());
            clearField();
            new Alert(Alert.AlertType.INFORMATION,"Add customer details!").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }

    }

    @FXML
    void closeButtonOnAction(ActionEvent event) {
        duplicate.clickButtonCloseWindow(closeButton);
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
            searchBarAutoTextField.setVisible(true);
           txtFirstName.setEditable(false);
           txtLastName.setEditable(false);
           txtMobileNo.setEditable(false);
           txtAreaAddress.setEditable(false);

        }else if(checkBoNewCustomer.isSelected()){
            searchBarAutoTextField.setVisible(false);
            txtFirstName.setEditable(true);
            txtLastName.setEditable(true);
            txtMobileNo.setEditable(true);
            txtAreaAddress.setEditable(true);
        }
    }

    public void primaryAndDeliveryAddressCheckBox(MouseEvent mouseEvent) throws IOException {

        if (!checkboxPrimaryAndDeliveryAddress.isSelected()) {
            txtAdditionalAddress.setVisible(true);
           lblNewAddress.setVisible(true);
        } else if (checkboxPrimaryAndDeliveryAddress.isSelected()) {
            txtAdditionalAddress.setText(txtAreaAddress.getText());
            txtAdditionalAddress.setVisible(false);
            lblNewAddress.setVisible(false);
        }
    }
}
