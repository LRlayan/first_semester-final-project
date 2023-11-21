package lk.ijse.hotBurger.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hotBurger.dto.ItemCategoryDto;
import lk.ijse.hotBurger.dto.tm.ItemCategoryTm;
import lk.ijse.hotBurger.model.ItemCategoryModel;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class ItemCategoryFormController {
    @FXML
    private TableView<ItemCategoryTm> categoryTable;

    @FXML
    private TableColumn<?, ?> colCategoryId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TextField categorySearchBar;

    ItemCategoryModel itemCategoryModel = new ItemCategoryModel();

    public void initialize(){
        setCellValueFactory();
        loadAllItemCategory();
        categorySearchBar();
    }

    public void setCellValueFactory(){
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    ObservableList<ItemCategoryTm> obList = FXCollections.observableArrayList();

    public void loadAllItemCategory(){

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

    @FXML
    void categorySearchBarOnAction(ActionEvent event) {


    }

    public void categorySearchBar(){
        FilteredList<ItemCategoryTm> filteredList = new FilteredList<>(obList, b -> true);
        categorySearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(itemCategoryDto -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                String categoryId = String.valueOf(itemCategoryDto.getId());
                String categoryName = itemCategoryDto.getName();
                String description = itemCategoryDto.getDescription();

                if (categoryId != null && categoryId.toLowerCase().contains(searchKeyword)
                        || description != null && description.toLowerCase().contains(searchKeyword)
                        || categoryName != null && categoryName.toLowerCase().contains(searchKeyword)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<ItemCategoryTm> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(categoryTable.comparatorProperty());
        categoryTable.setItems(sortedData);
    }
}
