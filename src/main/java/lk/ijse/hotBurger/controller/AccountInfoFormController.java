package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.hotBurger.dto.CustomerDto;
import lk.ijse.hotBurger.dto.UserDto;
import lk.ijse.hotBurger.model.CustomerModel;
import lk.ijse.hotBurger.model.UserModel;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountInfoFormController {


    @FXML
    private TextField txtConfirmUsername;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtNewUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private Label redLblUsername;

    @FXML
    private Label redLblConfirmUsername;

    DuplicateMethodController checkPassword = new DuplicateMethodController();
    UserModel userModel = new UserModel();

    @FXML
    void btnChangeUsernameOnAction(ActionEvent event) throws SQLException {


        List<UserDto> userDtoList = UserModel.getAllUsers();

        String confirmUsername = txtConfirmUsername.getText();
        String oldUsername = txtUsername.getText();

        var user = new UserDto(confirmUsername);

        try{
            boolean isUpdated = userModel.updateUser(confirmUsername , oldUsername);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"updated").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void setEditableTextField(TextField textField , boolean onOff ){
        textField.setEditable(onOff);
        textField.setEditable(onOff);

    }

    @FXML
    void btnChangePasswordOnAction(ActionEvent event) {
        String confirmPassword = txtConfirmPassword.getText();

        var user = new UserDto(confirmPassword);


    }


    public void verifyUsernameOnAction(javafx.scene.input.KeyEvent keyEvent) throws SQLException {
        setEditableTextField(txtNewUsername , false);
        setEditableTextField(txtConfirmUsername , false);
        String username = txtUsername.getText();

        ArrayList<UserDto> userDtoList = UserModel.getAllUsers();

        for (int i = 0; i < userDtoList.size(); i++){
            if (username.equals(userDtoList.get(i).getUsername())){
                setEditableTextField(txtNewUsername , true);
                setEditableTextField(txtConfirmUsername , true);
            }
        }
        checkPassword.incorrectCredential(userDtoList , username , "Invalid Username!" , redLblUsername);
    }

    public void confirmUsernameOnAction(javafx.scene.input.KeyEvent keyEvent) {
        String newUsername = txtNewUsername.getText();
        String confirmUsername = txtConfirmUsername.getText();

        if (newUsername.equals(confirmUsername)){
            redLblConfirmUsername.setVisible(false);
        }else {
            redLblConfirmUsername.setText("Please Confirm New Username");
        }
    }
}
