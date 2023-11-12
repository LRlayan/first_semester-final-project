package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lk.ijse.hotBurger.dto.IngrediantsDto;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RemoveIngrediantFormController implements Initializable {

    @FXML
    private JFXCheckBox checkBoxMayonnaise;

    @FXML
    private JFXCheckBox checkboxBBQ;

    @FXML
    private JFXCheckBox checkboxKetchup;

    @FXML
    private JFXCheckBox checkboxSweet;

    @FXML
    private JFXCheckBox checkboxTomato;

    @FXML
    void btnConfirmClickOnAction(ActionEvent event) {

    }

    public void setCheckBoxDefaultSelectedIngrediants() {
        checkboxKetchup.setSelected(true);
        checkBoxMayonnaise.setSelected(true);
        checkboxSweet.setSelected(true);
        checkboxBBQ.setSelected(true);
        checkboxTomato.setSelected(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCheckBoxDefaultSelectedIngrediants();
    }
}
