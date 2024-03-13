package lk.ijse.hotBurger.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.ResourceBundle;


public class ForgetPasswordController implements Initializable {

    @FXML
    private AnchorPane passwordAnchorpane;
    DuplicateMethodController navigate = new DuplicateMethodController();

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigate.changeOnlyAnchorPane("/view/initializeForgetPassword.fxml", passwordAnchorpane);
    }

    public void closePage() {
        passwordAnchorpane.setVisible(false);
    }
}