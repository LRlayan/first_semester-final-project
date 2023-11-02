package lk.ijse.hotBurger.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigateController {

    public void navigate(String fxml , AnchorPane loginAnchorPane ) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) loginAnchorPane.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
