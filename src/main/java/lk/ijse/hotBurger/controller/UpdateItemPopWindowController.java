package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.model.ItemModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateItemPopWindowController implements Initializable {
    @FXML
    private TextField txtCategoryId;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtUnitCost;

    @FXML
    private TextField txtUnitPrice;


    @FXML
    private JFXButton itemUpdateClose;

    DuplicateMethodController duplicate = new DuplicateMethodController();

    ItemModel itemModel = new ItemModel();
   // private List<ItemDto> dtoList;

    @FXML
    void closeButtonOnAction(ActionEvent event) {
        duplicate.clickButtonCloseWindow(itemUpdateClose);
    }

    public void updateOnAction(ActionEvent actionEvent) {

            String  categoryId = txtCategoryId.getText();
            String itemCode = txtItemCode.getText();
            String itemName = txtItemName.getText();
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            double unitCost = Double.parseDouble(txtUnitCost.getText());

            var itemDto = new ItemDto(categoryId,itemCode,itemName,unitPrice,unitCost);
            try{
                 boolean isUpdate = itemModel.updateItem(itemDto);
                 if (isUpdate){
                     new Alert(Alert.AlertType.CONFIRMATION,"Update Successfully").show();
                 }
            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDataTextField();
    }

    public void loadDataTextField(){
        //initialize weddima item table eke select karana row eke data tika updateItemPopWindow ekata set wela thiyenna one
        List<ItemDto> dtoList = itemModel.loadAllItem();

          for (int i = 0; i < dtoList.size(); i++) {
            txtItemCode.setText(dtoList.get(i).getItemCode());
            txtItemName.setText(dtoList.get(i).getName());
            txtCategoryId.setText(dtoList.get(i).getCategoryId());
            txtUnitCost.setText(String.valueOf(dtoList.get(i).getUnitCost()));
            txtUnitPrice.setText(String.valueOf(dtoList.get(i).getUnitPrice()));
        }
    }
}
