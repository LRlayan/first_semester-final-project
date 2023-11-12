package lk.ijse.hotBurger.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.hotBurger.AppInitializer;
import lk.ijse.hotBurger.dto.GridPaneItemDto;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.model.ItemModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GridPaneItemController implements Initializable {
    public static int x ;
    public static int categoryId;
    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    private GridPaneItemDto burger;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setImgAndNameAndPrice();
    }

    public void setImgAndNameAndPrice(){
        List<ItemDto> itemDto = ItemModel.loadAllItemCategoryVise(categoryId);
        System.out.println("OBJ:  "+itemDto.size());
       // Image image = new Image(url);
       // img.setImage(Image.fromPlatformImage(itemDto.get(x).getImage()));
        Image image = new Image(itemDto.get(x).getImage());
        ImageView imageView = new ImageView(image);

        img.setImage(imageView.getImage());
        nameLabel.setText(itemDto.get(x).getName());
        priceLabel.setText(String.valueOf(itemDto.get(x).getUnitPrice()));
    }
}
