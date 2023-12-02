package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hotBurger.dto.ItemDto;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForgetPasswordController {

    @FXML
    private AnchorPane forgerPasswordAnchorpane;

    @FXML
    private JFXButton btnNext;

    @FXML
    private AnchorPane changeanchorpane;

    @FXML
    private TextField email;

    DuplicateMethodController navigate = new DuplicateMethodController();

    public void signInOnAction(MouseEvent mouseEvent) throws IOException {
        navigate.changeOnlyAnchorPane("/view/adminLogin_form.fxml", forgerPasswordAnchorpane);
    }

    @FXML
    void nextButtonOnAction(ActionEvent event) throws Exception {
        String recepeintEmail = email.getText();
        sendEmail(recepeintEmail);
        navigate.changeOnlyAnchorPane("/view/sendCodeForEmail.fxml",changeanchorpane);
    }

    public void sendEmail(String recepient) throws Exception {

        Properties properties = new Properties();

        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myAccountEmail = "rameshlayan4@gmail.com";
        String password = "lwuq wmou mvoc iywb";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail , password);
            }
        });

        Message message = prepareMessage(session,myAccountEmail,recepient);
        if (message!=null){
           // new Alert(Alert.AlertType.CONFIRMATION,"Send Email Successfully").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Please Enter Recipient's Email Address").show();
        }

        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipients(Message.RecipientType.TO , new InternetAddress[]{
                    new InternetAddress(recepient)
            });

            message.setSubject("Password Change code");

            message.setText(String.valueOf(526974));

            return message;
        } catch (Exception e) {
            Logger.getLogger(ForgetPasswordController.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }
}
