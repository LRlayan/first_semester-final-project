package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.dto.tm.ItemTm;
import lk.ijse.hotBurger.model.ItemModel;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.awt.Color.RED;
import static java.awt.SystemColor.text;

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

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colUpdate;

    ItemModel itemModel = new ItemModel();

    DuplicateMethodController duplicate = new DuplicateMethodController();

    ObservableList<ItemTm> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setCellValueFactory();
        loadAllItem();
        searchBarItem();
    }
    private void setCellValueFactory(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colItemCost.setCellValueFactory(new PropertyValueFactory<>("unitCost"));
        colItemCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("update"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

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
                        new JFXButton("Update"),
                        new JFXButton("Delete")
                ));
            }
            itemtable.setItems(observableList);

        } catch (HeadlessException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < observableList.size(); i++) {
            observableList.get(i).getDelete().setTextFill(Color.WHITE);
            observableList.get(i).getDelete().setBackground(Background.fill(Color.RED));
            observableList.get(i).getDelete().setAlignment(Pos.CENTER);
            observableList.get(i).getUpdate().setTextFill(Color.WHITE);
            observableList.get(i).getUpdate().setBackground(Background.fill(Color.GREEN));
            observableList.get(i).getUpdate().setAlignment(Pos.CENTER);

            String name = observableList.get(i).getName();
            String categoryId = observableList.get(i).getCategoryId();
            String itemCode = observableList.get(i).getItemCode();
            String unitPrice = String.valueOf(observableList.get(i).getUnitPrice());
            String unitCost = String.valueOf(observableList.get(i).getUnitCost());

            observableList.get(i).getUpdate().setOnAction(event -> {

            UpdateItemPopWindowController.name = name;
            UpdateItemPopWindowController.categoryId = categoryId;
            UpdateItemPopWindowController.itemCode = itemCode;
            UpdateItemPopWindowController.unitCost = unitCost;
            UpdateItemPopWindowController.unitPrice = unitPrice;

                try {
                    duplicate.popUpWindow("/view/updateItemPopWindow.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            observableList.get(i).getDelete().setOnAction(event -> {
                ButtonType yes = new ButtonType("Yes" , ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,"Are you sure delete item!",yes,no).showAndWait();

                itemtable.getSelectionModel().selectedItemProperty().addListener((observable , oldValue , newValue ) -> {
                    if (type.orElse(no) == yes) {
                        deleteDate(newValue);
                        itemtable.refresh();
                    }
                });
            });
        }
    }

    private void deleteDate(ItemTm row){
        String itemCode = row.getItemCode();

        try{
            boolean isDelete = itemModel.deleteItem(itemCode);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete Successfully!").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void clickNewItemBtnOnActon(ActionEvent actionEvent) throws IOException {
        duplicate.popUpWindow("/view/addNewItem.fxml");
    }

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
