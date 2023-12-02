package lk.ijse.hotBurger.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hotBurger.dto.UserDto;
import lk.ijse.hotBurger.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForgerPasswordChangePasswordController {

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private Label lblConfirmPassword;

    @FXML
    private AnchorPane changePasswordAnchorpane;

    public static int userId;

    UserModel userModel = new UserModel();

    DuplicateMethodController navigate = new DuplicateMethodController();

    @FXML
    void changePasswordOnAction(ActionEvent event) throws SQLException {
        String confirmPassword = txtConfirmPassword.getText();

        var user = new UserDto(confirmPassword);

        ArrayList<UserDto> allUsers = UserModel.getAllUsers();

        for (int i =0; i < allUsers.size(); i++){
            userId = allUsers.get(i).getId();
        }
        try {

            boolean isUpdatePassword = userModel.updateUserPassword(confirmPassword , userId);
            if (isUpdatePassword){
                new Alert(Alert.AlertType.CONFIRMATION,"Update Password Successfully!").show();
                clearTextField(txtNewPassword,txtConfirmPassword);
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void clearTextField(TextField txtNewPassword, TextField txtConfirmPassword) {
        txtNewPassword.setText("");
        txtConfirmPassword.setText("");
    }

    @FXML
    void confirmPasswordOnAction(KeyEvent event) {
        String newPassword = txtNewPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (!newPassword.equals(confirmPassword)){
            lblConfirmPassword.setText("Please confirm new password");
        }else {
            lblConfirmPassword.setVisible(false);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        navigate.changeOnlyAnchorPane("/view/sendCodeForEmail.fxml",changePasswordAnchorpane);
    }

    public void signInOnAction(MouseEvent mouseEvent) throws IOException {
        navigate.changeOnlyAnchorPane("/view/adminLogin_form.fxml",changePasswordAnchorpane);
    }
}
