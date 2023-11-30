package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    @FXML
    private AnchorPane adminanchorpane;

    @FXML
    private JFXButton btnCustomer;
    @FXML
    public JFXButton btnDashboard;
    @FXML
    public JFXButton btnItem;
    @FXML
    public JFXButton btnOrder;
    @FXML
    public JFXButton btnAccount;

    @FXML
    private AnchorPane adminPanelAnchorpane;



    DuplicateMethodController duplicate = new DuplicateMethodController();

    public void clickDashboard(ActionEvent actionEvent) throws IOException {
        duplicate.changeOnlyAnchorPane("/view/dashboardPane_form.fxml", adminanchorpane);
        duplicate.clickButtonChangeBackgroundColor(btnDashboard,btnItem,btnOrder,btnAccount,btnCustomer);
    }

    public void clickOnItem(ActionEvent actionEvent) throws IOException {
        duplicate.changeOnlyAnchorPane("/view/manageItemCategory_form.fxml", adminanchorpane);
        duplicate.clickButtonChangeBackgroundColor(btnItem,btnAccount,btnDashboard,btnOrder,btnCustomer);
    }

    public void clickOnAccountInfo(ActionEvent actionEvent) throws IOException {
        duplicate.changeOnlyAnchorPane("/view/accountInfo_form.fxml", adminanchorpane);
        duplicate.clickButtonChangeBackgroundColor(btnAccount,btnDashboard,btnItem,btnOrder,btnCustomer);
    }

    public void clickOnOrder(ActionEvent actionEvent) throws IOException {
        duplicate.changeOnlyAnchorPane("/view/manageOrder_form.fxml" , adminanchorpane);
        duplicate.clickButtonChangeBackgroundColor(btnOrder,btnAccount,btnItem,btnDashboard,btnCustomer);
    }

    @FXML
    void clickOnCustomer(ActionEvent event) throws IOException {
        duplicate.changeOnlyAnchorPane("/view/customerDetails_form.fxml" , adminanchorpane);
        duplicate.clickButtonChangeBackgroundColor(btnCustomer,btnAccount,btnItem,btnDashboard,btnOrder);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            duplicate.changeOnlyAnchorPane("/view/dashboardPane_form.fxml",adminanchorpane);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        duplicate.navigate("/view/adminLogin_form.fxml" , adminanchorpane);
    }
}
