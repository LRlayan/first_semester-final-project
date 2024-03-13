package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.dto.tm.ItemTm;
import lk.ijse.hotBurger.model.ItemModel;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GmailItemController implements Initializable {
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
        private JFXButton closeButton;

        @FXML
        private TextField sendEmail;

        static ItemModel itemModel = new ItemModel();

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
        }


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
        void closeButtonOnActon(ActionEvent event) {
            duplicate.clickButtonCloseWindow(closeButton);
        }

        public void sendEmail(String recepient) throws Exception {

            Properties properties = new Properties();

            properties.put("mail.smtp.auth","true");
            properties.put("mail.smtp.starttls.enable","true");
            properties.put("mail.smtp.host","smtp.gmail.com");
            properties.put("mail.smtp.port","587");

            String myAccountEmail = "rameshlayan4@gmail.com";
            String password = "qnlt ncnd npse wecl";

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(myAccountEmail , password);
                }
            });

            Message message = prepareMessage(session,myAccountEmail,recepient);
            if (message!=null){
                new Alert(Alert.AlertType.CONFIRMATION,"Send Email Successfully").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Please Enter Recipient's Email Address").show();
            }

            Transport.send(message);
    }
    @FXML
    void menuSendOnActionEmail(ActionEvent event) throws Exception {
       String email = sendEmail.getText();
        sendEmail(email);
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        List<ItemDto> itemList = itemModel.loadAllItem();

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipients(Message.RecipientType.TO , new InternetAddress[]{
                    new InternetAddress(recepient)
            });

            message.setSubject("Hot Burgers Menu");

            ArrayList<String> items = new ArrayList<>();

            for (int i = 0; i < itemList.size(); i++) {
            items.add(itemList.get(i).getName() + " - Rs." + itemList.get(i).getUnitPrice() + "\n");
            }

            message.setText(String.valueOf(items));

            return message;
        } catch (Exception e) {
            Logger.getLogger(GmailItemController.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }
}
