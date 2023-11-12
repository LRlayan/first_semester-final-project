package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ManageItemCategoryFormController implements Initializable {

    @FXML
    private AnchorPane itemAnchorpane;

    @FXML
    private JFXButton categorySelection;

    @FXML
    private JFXButton itemSelection;

    @FXML
    private Label lblItem;

    @FXML
    private Label lblItemCategory;

    DuplicateMethodController duplicate = new DuplicateMethodController();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            onClickItemCategory(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void setForms(String forms) throws IOException {
        String[] form = {"/view/itemCategory_form.fxml","/view/manageItem_form.fxml"};
        Label[] lbl = {lblItemCategory,lblItem};

        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(forms)));
        itemAnchorpane.getChildren().clear();
        itemAnchorpane.getChildren().add(load);

    }
    public void onClickItemLabel(MouseEvent mouseEvent) throws IOException {
        setForms("/view/manageItem_form.fxml");
        duplicate.clickLabelChangeRectangleColor(itemSelection,categorySelection);
    }
    public void onClickItemCategory(MouseEvent mouseEvent) throws IOException {
        setForms("/view/itemCategory_form.fxml");
        duplicate.clickLabelChangeRectangleColor(categorySelection,itemSelection);
    }
}
