package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.dto.tm.ItemTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CashierWorkFormController implements Initializable {

    @FXML
    AnchorPane orderAnchorPane;

    @FXML
    private AnchorPane cashierMainAnchorpane;
    private static CashierWorkFormController instance;

    @FXML
    private JFXButton btnDelivery;

    @FXML
    private JFXButton btnDineIn;

    @FXML
    private JFXButton btnPickUp;

    @FXML
    private AnchorPane mainAnchorpane;

    private BurgerCategoryFormController burgerGrid;

    DuplicateMethodController duplicateMethodController = new DuplicateMethodController();
    DuplicateMethodController duplicate = new DuplicateMethodController();

    public void onBurgerClick(ActionEvent actionEvent) throws IOException {
        loadGrid(1);
        burgerGrid.ingrediantAnchorpane.setVisible(true);
        burgerGrid.toppingsAnchorpane.setVisible(true);
    }

    @FXML
    void onComboBoxClick(ActionEvent event) throws IOException {
        loadGrid(2);
    }

    @FXML
    void onSnacksClick(ActionEvent event) {
        loadGrid(3);
    }

    @FXML
    void onSaucesClick(ActionEvent event) {
        loadGrid(4);
    }

    @FXML
    void onDrinksClick(ActionEvent event) {
        loadGrid(5);
    }

    @FXML
    void onOfferseClick(ActionEvent event) {
        loadGrid(6);
    }

    private void loadGrid(int categoryId) {
        if (burgerGrid != null) {
            burgerGrid.clickLoadGridPane(categoryId, "/view/gridPaneItem.fxml");
            burgerGrid.ingrediantAnchorpane.setVisible(false);
            burgerGrid.toppingsAnchorpane.setVisible(false);
        } else {
            System.out.println("burger grid ek null");
        }
    }


    public void cashierLogoutOnAction(MouseEvent mouseEvent) throws IOException {
        duplicate.navigate("/view/adminLogin_form.fxml", cashierMainAnchorpane);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        try {
            BurgerCategoryFormController.pane = orderAnchorPane;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/burgerCategory_form.fxml"));
            Parent root = loader.load();
            burgerGrid = loader.getController();

            if (mainAnchorpane != null) {
                mainAnchorpane.getChildren().removeAll();
                mainAnchorpane.getChildren().setAll(root);
            } else {
                System.out.println("mainAnchorpane is null");
            }

            Parent parent = FXMLLoader.load(getClass().getResource("/view/cartTable.fxml"));
            orderAnchorPane.getChildren().clear();
            orderAnchorPane.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void reportsOnAction(ActionEvent event) throws JRException, SQLException {
        InputStream reportsAsStream = getClass().getResourceAsStream("/reports/hotBurgers.jrxml");
        JasperDesign load = JRXmlLoader.load(reportsAsStream);
        JasperReport jasperReport1 = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport1,
                null,
                DbConnection.getInstance().getConnection()

        );
        JasperViewer.viewReport(jasperPrint, false);
    }

    @FXML
    void btnMailBoxOnAction(ActionEvent event) throws IOException {
        duplicate.popUpWindow("/view/gmailItem_form.fxml");

    }
}
