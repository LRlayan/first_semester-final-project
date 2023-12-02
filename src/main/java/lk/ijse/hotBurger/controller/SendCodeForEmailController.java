package lk.ijse.hotBurger.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SendCodeForEmailController {
    @FXML
    private TextField txtCode;

    @FXML
    private AnchorPane codeAnchorpane;

    @FXML
    private Label lblCode;

    @FXML
    private Label lblSignIn;

    DuplicateMethodController navigate = new DuplicateMethodController();
    @FXML
    void codeVerifyOnAction(ActionEvent event) throws IOException {
       String code = String.valueOf(526974);
        String verifyCode = txtCode.getText();

        if (verifyCode.equals(code)) {
            navigate.changeOnlyAnchorPane("/view/forgerPasswordChangePassword.fxml", codeAnchorpane);
            lblCode.setVisible(false);
        } else {
            lblCode.setText("Invalid Code!");
        }
    }
}
