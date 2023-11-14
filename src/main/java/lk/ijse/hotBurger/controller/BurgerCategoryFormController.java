package lk.ijse.hotBurger.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.model.ItemModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BurgerCategoryFormController implements Initializable {

    private static BurgerCategoryFormController instance;

    public static BurgerCategoryFormController getInstance() {
        return instance;
    }
    public GridPane gridpane;

    @FXML
    protected AnchorPane ingrediantAnchorpane;

    @FXML
    protected AnchorPane toppingsAnchorpane;

    DuplicateMethodController duplicate = new DuplicateMethodController();

    public void initializeLoadGridPane(int categoryId){
        List<ItemDto> itemDtos = ItemModel.loadAllItemCategoryVise(categoryId);
        System.out.println(itemDtos.size());

        int column = 0;
        int row = 0;
        for (int i = 0; i < itemDtos.size(); i++) {
            GridPaneItemController.x = i;
            GridPaneItemController.categoryId = categoryId;
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/gridPaneItem.fxml"));
                gridpane.add(parent,column,row++);
                GridPane.setMargin(parent,new Insets(6,6,6,6));

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    protected void clickLoadGridPane(int categoryId , String fxml){

        if (gridpane == null) {
            System.out.println("burgerGridpane is null");
            return;
        }

        gridpane.getChildren().clear();

        List<ItemDto> itemDtos = ItemModel.loadAllItemCategoryVise(categoryId);

        if (itemDtos == null) {
            System.out.println("itemDtos is null");
            return;
        }

        System.out.println(itemDtos.size());

        int column = 0;
        int row = 0;
        for (int i = 0; i < itemDtos.size(); i++) {
            GridPaneItemController.x = i;
            GridPaneItemController.categoryId = categoryId;

            try {
                Parent parent = FXMLLoader.load(getClass().getResource(fxml));

                if (parent != null) {
                    gridpane.add(parent, column, row++);
                    GridPane.setMargin(parent, new Insets(6, 6, 6, 6));
                } else {
                    System.out.println("FXMLLoader failed to load parent");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        try {
            duplicate.changeOnlyAnchorPane("/view/removeIngrediant_form.fxml" , ingrediantAnchorpane);
            duplicate.changeOnlyAnchorPane("/view/toppings_form.fxml" , toppingsAnchorpane);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        initializeLoadGridPane(1);
    }
}
