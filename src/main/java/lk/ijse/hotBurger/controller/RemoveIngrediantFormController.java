package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.ijse.hotBurger.dto.IngrediantsDto;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RemoveIngrediantFormController implements Initializable {


    @FXML
    private JFXCheckBox checkBoxMayonnaise;

    @FXML
    private JFXCheckBox checkInoins;

    @FXML
    private JFXCheckBox checkLettuce;

    @FXML
    private JFXCheckBox checkTomato;

    @FXML
    private JFXCheckBox checkboxKetchup;

    @FXML
    void btnConfirmClickOnAction(ActionEvent event) {
        if (checkboxKetchup.isSelected() || checkBoxMayonnaise.isSelected() || checkInoins.isSelected() || checkTomato.isSelected() || checkLettuce.isSelected()) {
            new Alert(Alert.AlertType.CONFIRMATION, "Confirm Remove Ingredients!").show();

        }
    }

    public void setCheckBoxDefaultSelectedIngrediants() {
        checkboxKetchup.setSelected(true);
        checkBoxMayonnaise.setSelected(true);
        checkInoins.setSelected(true);
        checkTomato.setSelected(true);
        checkLettuce.setSelected(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCheckBoxDefaultSelectedIngrediants();
    }
}
