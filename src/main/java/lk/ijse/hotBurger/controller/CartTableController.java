package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.dto.tm.ItemTm;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CartTableController implements Initializable {
    public static double price;
    public static String  itemName;

    @FXML
    private TextField text;

    @FXML
    private TextField txtName;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;
    @FXML
    private TableView<ItemTm> tbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setValueFactory();
        loardClickItemDetail();

            text.setText(String.valueOf(price));
            txtName.setText(itemName);
    }

    public void setValueFactory(){
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    ItemTm itemTm = new ItemTm(price , itemName);
    ObservableList<ItemTm> observableList = FXCollections.observableArrayList();
    public void loardClickItemDetail(){

        observableList.add(new ItemTm(
                itemTm.getUnitPrice(),
                itemTm.getName()
                ));

        tbl.setItems(observableList);
    }
//
//     for (ItemDto dto : dtoList) {
//        observableList.add(new ItemTm(
//                dto.getId(),
//                dto.getItemCode(),
//                dto.getName(),
//                dto.getUnitPrice(),
//                dto.getUnitCost(),
//                dto.getCategoryId(),
//                new JFXButton("Update"),
//                new JFXButton("Delete")
//        ));
//    }
//            itemtable.setItems(observableList);

}
