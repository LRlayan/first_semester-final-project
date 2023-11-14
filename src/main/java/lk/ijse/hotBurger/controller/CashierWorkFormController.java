package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lk.ijse.hotBurger.dto.GridPaneItemDto;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.model.ItemModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CashierWorkFormController implements Initializable {

    @FXML
    private AnchorPane cashierMainAnchorpane;

    @FXML
    private JFXButton btnDelivery;

    @FXML
    private JFXButton btnDineIn;

    @FXML
    private JFXButton btnPickUp;

    @FXML
    private AnchorPane mainAnchorpane;

    private BurgerCategoryFormController burgerGrid;
    DuplicateMethodController duplicate = new DuplicateMethodController();

    public void onBurgerClick(ActionEvent actionEvent) throws IOException {
        loadGrid(1);
        burgerGrid.ingrediantAnchorpane.setVisible(true);
        burgerGrid.toppingsAnchorpane.setVisible(true);
    }

    @FXML
    void onComboBoxClick(ActionEvent event) throws IOException {
        loadGrid(2);
    }

    @FXML
    void onSnacksClick(ActionEvent event) {
        loadGrid(3);
    }

    @FXML
    void onSaucesClick(ActionEvent event) {
        loadGrid(4);
    }

    @FXML
    void onDrinksClick(ActionEvent event) {
        loadGrid(5);
    }

    @FXML
    void onOfferseClick(ActionEvent event) {
        loadGrid(6);
    }

    private void loadGrid(int categoryId) {
        if (burgerGrid != null) {
            burgerGrid.clickLoadGridPane(categoryId, "/view/gridPaneItem.fxml");
            burgerGrid.ingrediantAnchorpane.setVisible(false);
            burgerGrid.toppingsAnchorpane.setVisible(false);
        } else {
            System.out.println("burger grid ek null");
        }
    }

    public void btnDeliveryDetailOnAction(ActionEvent actionEvent) throws IOException {
        duplicate.clickButtonChangeColor(btnDelivery,btnPickUp,btnDineIn);
        duplicate.popUpWindow("/view/delivery_form.fxml");
    }

    public void cashierLogoutOnAction(MouseEvent mouseEvent) throws IOException {
        duplicate.navigate("/view/adminLogin_form.fxml" , cashierMainAnchorpane);
    }

    @FXML
    void clickOnDineInBtnAction(ActionEvent event) {
        duplicate.clickButtonChangeColor(btnDineIn,btnPickUp,btnDelivery);
    }
    @FXML
    void clickOnPickUpBtnAction(ActionEvent event) {
        duplicate.clickButtonChangeColor(btnPickUp,btnDelivery,btnDineIn);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/burgerCategory_form.fxml"));
            Parent root = loader.load();
            burgerGrid = loader.getController();
            mainAnchorpane.getChildren().removeAll();
            mainAnchorpane.getChildren().setAll(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
