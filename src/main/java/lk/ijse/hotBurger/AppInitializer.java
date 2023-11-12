package lk.ijse.hotBurger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {

   public static final String CURRENCY = "Rs";
    @Override
    public void start(Stage primaryStage) throws Exception {
       Parent rootNode =  FXMLLoader.load(getClass().getResource("/view/adminLogin_form.fxml"));
        Scene scene = new Scene(rootNode);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
