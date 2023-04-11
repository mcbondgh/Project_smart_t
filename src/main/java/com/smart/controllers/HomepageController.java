package com.smart.controllers;

import com.smart.AppStarter;
import com.smart.specialmethods.SpecialMethods;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

//    FXML NODE FILE EJECTION
    @FXML private BorderPane borderPaneContainer;

    @FXML private Button logoutButton;
        @FXML
        private Button accountsButton;
        @FXML private Label currentUserLabel;
        @FXML
        private Button customerButton;
        @FXML private Button homeButton;
        @FXML private Button qrVerificationButton;
        @FXML private MenuButton settingsButton;
        @FXML private Button vehicleClassificationButton;
        @FXML private MenuButton manageUsersButton;


        public static String activeUser;

    public void initialize(URL url, ResourceBundle resourceBundle) {
            currentUserLabel.setText(activeUser);
        try {
            SpecialMethods.FlipView("dashboard-file.fxml", borderPaneContainer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


//    OTHER SPECIAL METHODS



//    TRUE OR FALSE STATEMENTS





//    ACTION EVENT METHODS IMPLEMENTATION
    @FXML void logoutButtonClicked(ActionEvent event) throws IOException {
        logoutButton.getScene().getWindow().hide();
        AppStarter.LoginStage();
    }

    @FXML void homeButtonClicked(ActionEvent event) throws IOException {
        SpecialMethods.FlipView("dashboard-file.fxml", borderPaneContainer);
    }

    @FXML void manageUsersButtonClicked() throws IOException {
        SpecialMethods.FlipView("add-users.fxml", borderPaneContainer);
    }



}//end of class