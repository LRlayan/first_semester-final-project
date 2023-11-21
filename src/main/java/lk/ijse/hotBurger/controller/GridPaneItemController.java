package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hotBurger.AppInitializer;
import lk.ijse.hotBurger.dto.GridPaneItemDto;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.model.ItemModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class GridPaneItemController implements Initializable {

    public static int x;

    public static int categoryId;

    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;
    private CashierWorkFormController cashierWorkFormController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImgAndNameAndPrice();
    }

    public void setImgAndNameAndPrice() {
        List<ItemDto> itemDto = ItemModel.loadAllItemCategoryVise(categoryId);
        System.out.println("OBJ:  " + itemDto.size());

        Image image = new Image(itemDto.get(x).getImage());
        ImageView imageView = new ImageView(image);

        img.setImage(imageView.getImage());
        nameLabel.setText(itemDto.get(x).getName());
        priceLabel.setText(String.valueOf(itemDto.get(x).getUnitPrice()));
    }

    @FXML
    void allItemButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader cashierWork = new FXMLLoader(getClass().getResource("/view/CashierWork_form.fxml"));
        Parent cashierWorkRoot = cashierWork.load();

        cashierWorkFormController = cashierWork.getController();

        FXMLLoader orderPane = new FXMLLoader(getClass().getResource("/view/orderPane_form.fxml"));
        Parent orderPaneRoot = orderPane.load();

        if (cashierWorkFormController.orderAnchorPane != null) {
            cashierWorkFormController.orderAnchorPane.getChildren().removeAll();
            cashierWorkFormController.orderAnchorPane.getChildren().setAll(orderPaneRoot);
        } else {
            System.out.println("orderAnchorPane is null");
        }
    }
}
