package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.hotBurger.dto.AddNewItemDto;
import lk.ijse.hotBurger.model.AddNewItemModel;
import lk.ijse.hotBurger.model.CustomerModel;

import java.sql.SQLException;

public class AddNewItemController {
    @FXML
    private TextField txtItemCategory;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtUnitCost;

    @FXML
    private TextField txtUnitPrice;
    @FXML
    private JFXButton btnCreate;

    DuplicateMethodController duplicate = new DuplicateMethodController();
    CustomerModel customerModel = new CustomerModel();
    public void createBtnOnAction(ActionEvent actionEvent) {
        String code = txtItemCode.getText();
        String name = txtItemName.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double unitCost = Double.parseDouble(txtUnitCost.getText());
        String categoryId = txtItemCategory.getText();

        var newItemDto = new AddNewItemDto(code,name,unitPrice,unitCost,categoryId);
        try{
            boolean isAdd = AddNewItemModel.addNewItem(newItemDto);
            if (isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"Added Successfully!").show();
                duplicate.clickButtonCloseWindow(btnCreate);
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
}
