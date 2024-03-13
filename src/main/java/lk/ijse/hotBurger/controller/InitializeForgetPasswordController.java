package lk.ijse.hotBurger.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hotBurger.dto.UserDto;
import lk.ijse.hotBurger.model.UserModel;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitializeForgetPasswordController {

    @FXML
    private AnchorPane nextAnchorpane;

    @FXML
    private Label lblSignIn;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private TextField email;

    DuplicateMethodController navigate = new DuplicateMethodController();

    @FXML
    void signInOnAction(ActionEvent event) throws IOException {
        navigate.popUpWindow("/view/adminLogin_form.fxml");
        navigate.clickButtonCloseWindow(btnSignIn);
    }

    @FXML
    void nextButtonOnAction(ActionEvent event) throws Exception {
        String recepeintEmail = email.getText();
        sendEmail(recepeintEmail);
        navigate.changeOnlyAnchorPane("/view/sendCodeForEmail.fxml",nextAnchorpane);
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
