package lk.ijse.hotBurger.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import lk.ijse.hotBurger.dto.ItemCategoryDto;
import lk.ijse.hotBurger.dto.tm.ItemCategoryTm;
import lk.ijse.hotBurger.model.ItemCategoryModel;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemCategoryFormController {
    @FXML
    private TableView<ItemCategoryTm> categoryTable;

    @FXML
    private TableColumn<?, ?> colCategoryId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colName;
    ItemCategoryModel itemCategoryModel = new ItemCategoryModel();

    public void initialize(){
        setCellValueFactory();
        loadAllItemCategory();
    }

    public void setCellValueFactory(){
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }
    public void loadAllItemCategory(){
        ObservableList<ItemCategoryTm> obList = FXCollections.observableArrayList();

        try{
            List<ItemCategoryDto> itemCategoryDtoList = itemCategoryModel.loadAllItemCategory();

            for ( ItemCategoryDto dto : itemCategoryDtoList) {
                obList.add(new ItemCategoryTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getDescription()
                ));
            }

            categoryTable.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
