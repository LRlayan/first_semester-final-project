package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.dto.tm.ItemTm;
import lk.ijse.hotBurger.model.ItemModel;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;


import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.dto.tm.ItemTm;
import lk.ijse.hotBurger.model.ItemModel;
import javafx.scene.control.TextField;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.awt.Color.RED;
import static java.awt.SystemColor.text;

public class gmailItemController implements Initializable {
        @FXML
        private TableColumn<?, ?> colItemCategoryId;

        @FXML
        private TableColumn<?, ?> colItemCode;

        @FXML
        private TableColumn<?, ?> colItemCost;

        @FXML
        private TableColumn<?, ?> colItemName;

        @FXML
        private TableColumn<?, ?> colItemPrice;

        @FXML
        private TableColumn<?, ?> colUpdateDelete;

        @FXML
        private TableView<ItemTm> itemtable;

        @FXML
        private TextField txtSearchbar;

        @FXML
        private TableColumn<?, ?> colDelete;

        @FXML
        private TableColumn<?, ?> colUpdate;

        @FXML
        private TextField emailSend;

        @FXML
        private JFXButton closeButton;

        ItemModel itemModel = new ItemModel();

        DuplicateMethodController duplicate = new DuplicateMethodController();

        ObservableList<ItemTm> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        loadAllItem();
        searchBarItem();
    }
    private void setCellValueFactory(){
            colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colItemPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colItemCost.setCellValueFactory(new PropertyValueFactory<>("unitCost"));
            colItemCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
           // colDelete.setCellValueFactory(new PropertyValueFactory<>("update"));
            //colUpdate.setCellValueFactory(new PropertyValueFactory<>("delete"));
        }

        public void loadAllItem(){

            try {
                List<ItemDto> dtoList = itemModel.loadAllItem();
                for (ItemDto dto : dtoList) {
                    observableList.add(new ItemTm(
                            dto.getId(),
                            dto.getItemCode(),
                            dto.getName(),
                            dto.getUnitPrice(),
                            dto.getUnitCost(),
                            dto.getCategoryId()
                    ));
                }
                itemtable.setItems(observableList);

            } catch (HeadlessException e) {
                throw new RuntimeException(e);
            }
           /* for (int i = 0; i < observableList.size(); i++) {
                observableList.get(i).getDelete().setTextFill(javafx.scene.paint.Color.WHITE);
                observableList.get(i).getDelete().setBackground(Background.fill(javafx.scene.paint.Color.RED));
                observableList.get(i).getDelete().setAlignment(Pos.CENTER);
                observableList.get(i).getUpdate().setTextFill(javafx.scene.paint.Color.WHITE);
                observableList.get(i).getUpdate().setBackground(Background.fill(javafx.scene.paint.Color.GREEN));
                observableList.get(i).getUpdate().setAlignment(Pos.CENTER);

                observableList.get(i).getUpdate().setOnAction(event ->{
                    try {
                        duplicate.popUpWindow("/view/updateItemPopWindow.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                observableList.get(i).getDelete().setOnAction(event ->{

                });
            }*/
        }

//        public void clickNewItemBtnOnActon(ActionEvent actionEvent) throws IOException {
//            duplicate.popUpWindow("/view/addNewItem.fxml");
//        }

        public void searchBarItem(){ //item Search logic

            FilteredList<ItemTm> filteredList = new FilteredList<>(observableList, b -> true);
            txtSearchbar.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(itemDto -> {
                    if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();

                    String itemCode = itemDto.getItemCode();
                    String categoryId = itemDto.getCategoryId();
                    String itemName = itemDto.getName();

                    if (itemCode != null && itemCode.toLowerCase().contains(searchKeyword)
                            || categoryId != null && categoryId.toLowerCase().contains(searchKeyword)
                            || itemName != null && itemName.toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            SortedList<ItemTm> sortedData = new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(itemtable.comparatorProperty());
            itemtable.setItems(sortedData);
        }

        @FXML
        void menuSendOnActionEmail(ActionEvent event) {
            String emailSendText = emailSend.getText();
            String from = "rameshlayan4@gmail.com";
            // Sender's email password
            String password = "watawala";
            // Recipient's email address
            String to = emailSendText;

            // Set the host and properties for the session
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // Create a session with the given properties
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            try {
                // Create a default MimeMessage object
                Message message = new MimeMessage(session);

                // Set From: header field of the header
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

                // Set Subject: header field
                message.setSubject("Testing JavaMail");

                // Now set the actual message
                message.setText("Hello, this is a test email from JavaMail!");

                // Send message
                Transport.send(message);

                System.out.println("Email sent successfully!");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }

        @FXML
        void closeButtonOnActon(ActionEvent event) {
            duplicate.clickButtonCloseWindow(closeButton);
        }
}
