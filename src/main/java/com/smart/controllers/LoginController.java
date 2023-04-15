package com.smart.controllers;

import com.smart.AppStarter;
import com.smart.models.MainModel;
import com.smart.tableviewdata.UsersData;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends MainModel implements Initializable {


//  FXML NODE FILE EJECTIONS
    @FXML private Button cancelButton, loginButton;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label flagLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


//    TRUE OR FALSE STATEMENTS


//    SPECIAL METHODS IMPLEMENTATION


//    ACTION EVENT METHODS IMPLEMENTATION

    @FXML void closeButtonClicked() {
        Platform.exit();
    }

    @FXML void loginButtonClicked() throws IOException {
        HomepageController.activeUser = usernameField.getText().isBlank()? "Active User" : usernameField.getText();

        String username = usernameField.getText();
        String password = passwordField.getText();

        for (UsersData item : getAllUsers()) {
            if (username.equals(item.getUsername()) && password.equals(item.getPassword())) {
                AppStarter.Homepage();
                loginButton.getScene().getWindow().hide();
            } else {
                flagLabel.setVisible(true);
            }
            break;
        }
    }



}
