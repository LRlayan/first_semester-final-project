package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hotBurger.dto.UserDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DuplicateMethodController {

    public void navigate(String fxml , AnchorPane loginAnchorPane ) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) loginAnchorPane.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public void changeOnlyAnchorPane(String fxml , AnchorPane anchorPaneId) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        AnchorPane anchorPane =loader.load();
        anchorPaneId.getChildren().clear();
        anchorPaneId.getChildren().add(anchorPane);

    }
    public void incorrectCredential(ArrayList<UserDto> allUsers , String usernamePassword, String usernamePasswordMessage , javafx.scene.control.Label usernamePasswordLabelIncorrect) throws SQLException {
        for (int i = 0; i < allUsers.size(); i++) {
            if (usernamePassword.equals(allUsers.get(i).getUsername()) || usernamePassword.equals(allUsers.get(i).getPassword())) {
                usernamePasswordLabelIncorrect.setVisible(false);
            } else {
                usernamePasswordLabelIncorrect.setText(usernamePasswordMessage);
            }
        }
    }

    public void clickButtonChangeBackgroundColor(JFXButton btnColor , JFXButton btnTransparent1 , JFXButton btnTransparent2 , JFXButton btnTransparent3){
        btnColor.setStyle("-fx-background-color:rgba(255, 122, 0) ");
        btnTransparent1.setStyle("-fx-background-color:rgba(transparent) ");
        btnTransparent2.setStyle("-fx-background-color:rgba(transparent) ");
        btnTransparent3.setStyle("-fx-background-color:rgba(transparent) ");
    }
}
