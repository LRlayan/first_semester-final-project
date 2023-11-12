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



    BurgerCategoryFormController burgerGrid = new BurgerCategoryFormController();

    public void onBurgerClick(ActionEvent actionEvent) throws IOException {
        burgerGrid.clickLoadGridPane(1,"/view/gridPaneItem.fxml");
      //  duplicate.changeOnlyAnchorPane("/view/removeIngrediant_form.fxml" , ingrediantAnchorpane);
        //duplicate.changeOnlyAnchorPane("/view/toppings_form.fxml" , toppingsAnchorpane);
        //ingrediantAnchorpane.setVisible(true);
        //toppingsAnchorpane.setVisible(true);
        burgerGrid.ingrediantAnchorpane.setVisible(true);
        burgerGrid.toppingsAnchorpane.setVisible(true);
    }

    @FXML
    void onComboBoxClick(ActionEvent event) throws IOException {
       if(burgerGrid != null) {
           // duplicate.changeOnlyAnchorPane("/view/comboPack_form.fxml",mainAnchorpane);
           burgerGrid.clickLoadGridPane(2, "/view/gridPaneItem.fxml");
           //comboPack.clickComboPackLoadGridPane(2);
//        ingrediantAnchorpane.setVisible(false);
//        toppingsAnchorpane.setVisible(false);
           burgerGrid.ingrediantAnchorpane.setVisible(false);
           burgerGrid.toppingsAnchorpane.setVisible(false);
       }else {
           System.out.println("burger grid ek null");
       }
    }

    @FXML
    void onSnacksClick(ActionEvent event) {
        burgerGrid.clickLoadGridPane(3,"/view/gridPaneItem.fxml");
//        ingrediantAnchorpane.setVisible(false);
//        toppingsAnchorpane.setVisible(false);
        burgerGrid.ingrediantAnchorpane.setVisible(false);
        burgerGrid.toppingsAnchorpane.setVisible(false);
    }

    @FXML
    void onSaucesClick(ActionEvent event) {
        burgerGrid.clickLoadGridPane(4,"/view/gridPaneItem.fxml");
//        ingrediantAnchorpane.setVisible(false);
//        toppingsAnchorpane.setVisible(false);
        burgerGrid.ingrediantAnchorpane.setVisible(false);
        burgerGrid.toppingsAnchorpane.setVisible(false);
    }

    @FXML
    void onDrinksClick(ActionEvent event) {
        burgerGrid.clickLoadGridPane(5,"/view/gridPaneItem.fxml");
//        ingrediantAnchorpane.setVisible(false);
//        toppingsAnchorpane.setVisible(false);
        burgerGrid.ingrediantAnchorpane.setVisible(false);
        burgerGrid.toppingsAnchorpane.setVisible(false);
    }

    @FXML
    void onOfferseClick(ActionEvent event) {
        burgerGrid.clickLoadGridPane(6,"/view/gridPaneItem.fxml");
        burgerGrid.ingrediantAnchorpane.setVisible(false);
        burgerGrid.toppingsAnchorpane.setVisible(false);
    }
    DuplicateMethodController duplicate = new DuplicateMethodController();

    DeliveryFormController delivery = new DeliveryFormController();
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

    private List<GridPaneItemDto> burger = new ArrayList<>();

  /*  public void initializeLoadGridPane(int categoryId){
        List<ItemDto> itemDtos = ItemModel.loadAllItemCategoryVise(categoryId);
        //System.out.println(itemDtos.size());

        int column = 0;
        int row = 0;
        for (int i = 0; i < itemDtos.size(); i++) {
            GridPaneItemController.x = i;
            GridPaneItemController.categoryId = categoryId;
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/gridPaneItem.fxml"));
                burgerGridpane.add(parent,column,row++);
                GridPane.setMargin(parent,new Insets(6,6,6,6));

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }*/

   /* public void clickLoadGridPane(int categoryId , String fxml){
        gridpane.getChildren().clear();
        List<ItemDto> itemDtos = ItemModel.loadAllItemCategoryVise(categoryId);
        System.out.println(itemDtos.size());

        int column = 0;
        int row = 0;
        for (int i = 0; i < itemDtos.size(); i++) {
            GridPaneItemController.x = i;
            GridPaneItemController.categoryId = categoryId;
            try {
                Parent parent = FXMLLoader.load(getClass().getResource(fxml));
                gridpane.add(parent,column,row++);
                GridPane.setMargin(parent,new Insets(6,6,6,6));

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       /* try {
            duplicate.changeOnlyAnchorPane("/view/removeIngrediant_form.fxml" , ingrediantAnchorpane);
            duplicate.changeOnlyAnchorPane("/view/toppings_form.fxml" , toppingsAnchorpane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        try {
            duplicate.changeOnlyAnchorPane("/view/burgerCategory_form.fxml",mainAnchorpane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       // burgerGrid.initializeLoadGridPane(1);
    }
}
