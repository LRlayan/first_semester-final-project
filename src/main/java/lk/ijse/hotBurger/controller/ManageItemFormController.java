package lk.ijse.hotBurger.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.dto.tm.ItemTm;
import lk.ijse.hotBurger.model.ItemModel;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ManageItemFormController implements Initializable {
    @FXML
    private TableColumn<?, ?> colItemCategoryId;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemCost;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colItemPrice;

    @FXML
    private TableColumn<?, ?> colUpdateDelete;

    @FXML
    private TableView<ItemTm> itemtable;


    @FXML
    private TextField txtSearchbar;

    ItemModel itemModel = new ItemModel();
    ItemTm itemTm = new ItemTm();

    DuplicateMethodController duplicate = new DuplicateMethodController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        loadAllItem();
        searchBarItem();
    }

    /*public void initialize() {

        //searchBarItem();
    }*/

    private void setCellValueFactory(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colItemCost.setCellValueFactory(new PropertyValueFactory<>("unitCost"));
        colItemCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        colUpdateDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }
    ObservableList<ItemTm> observableList = FXCollections.observableArrayList();

    public void loadAllItem(){


        try {
            List<ItemDto> dtoList = itemModel.loadAllItem();
            for (ItemDto dto : dtoList) {
                observableList.add(new ItemTm(
                        dto.getId(),
                        dto.getItemCode(),
                        dto.getName(),
                        dto.getUnitPrice(),
                        dto.getUnitCost(),
                        dto.getCategoryId(),
                        new java.awt.Button(),
                        new java.awt.Button()
                ));
            }
            itemtable.setItems(observableList);

        } catch (HeadlessException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickNewItemBtnOnActon(ActionEvent actionEvent) throws IOException {
        duplicate.popUpWindow("/view/addNewItem.fxml");
    }

    ItemDto itemDto = new ItemDto();

    public void searchBarItem(){ //item Search logic

        FilteredList<ItemTm> filteredList = new FilteredList<>(observableList, b -> true);
        txtSearchbar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(itemDto -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                String itemCode = itemDto.getItemCode();
                String categoryId = itemDto.getCategoryId();
                String itemName = itemDto.getName();

                if (itemCode != null && itemCode.toLowerCase().contains(searchKeyword)
                        || categoryId != null && categoryId.toLowerCase().contains(searchKeyword)
                        || itemName != null && itemName.toLowerCase().contains(searchKeyword)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<ItemTm> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(itemtable.comparatorProperty());
        itemtable.setItems(sortedData);
    }
}
