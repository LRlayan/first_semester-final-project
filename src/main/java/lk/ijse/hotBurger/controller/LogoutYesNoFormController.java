package lk.ijse.hotBurger.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LogoutYesNoFormController {

    DuplicateMethodController duplicate = new DuplicateMethodController();

    @FXML
    private AnchorPane logoutVerifyAnchorpane;

    public void btnYesOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
       // duplicate.navigate("/view/adminLogin_form.fxml" , logoutVerifyAnchorpane);
        //logoutVerifyAnchorpane.setVisible(false);
        //logoutVerifyAnchorpane.managedProperty().bind(logoutVerifyAnchorpane.visibleProperty());
    }

    public void btnNoAction(javafx.event.ActionEvent actionEvent) {

    }
}
