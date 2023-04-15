package com.smart.controllers;

import com.smart.AppStarter;
import com.smart.alerts.UserAlerts;
import com.smart.specialmethods.SpecialMethods;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.IllegalFormatCodePointException;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

    UserAlerts alerts;

//    FXML NODE FILE EJECTION
    @FXML private BorderPane borderPaneContainer;

    @FXML private Button logoutButton;
        @FXML
        private Button accountsButton;
        @FXML private Label currentUserLabel;
        @FXML
        private Button customerRegistrationButton;
        @FXML private Button homeButton;
        @FXML private Button qrVerificationButton;
        @FXML private MenuButton settingsButton;
        @FXML private Button vehicleClassificationButton;
        @FXML private MenuItem manageUsersButton,RolesPermissionButton, vehicleClassesButton;


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
        alerts = new UserAlerts("LOG OUT", "ARE YOU SURE YOU WANT TO LOG OUT?");
        if (alerts.confirmation()) {
            logoutButton.getScene().getWindow().hide();
            AppStarter.LoginStage();
        }

    }

    @FXML void homeButtonClicked(ActionEvent event) throws IOException {
        SpecialMethods.FlipView("dashboard-file.fxml", borderPaneContainer);
    }

    @FXML void manageUsersButtonClicked() throws IOException {
        SpecialMethods.FlipView("add-users.fxml", borderPaneContainer);
    }

    @FXML void RolesPermissionButtonClicked() throws IOException {
        SpecialMethods.FlipView("permission&roles.fxml", borderPaneContainer);
    }

    @FXML void vehicleClassesButtonClicked() throws IOException {
        SpecialMethods.FlipView("vehicle-classifications.fxml", borderPaneContainer);
    }
    @FXML void customerRegistrationButtonClicked() throws IOException {
        SpecialMethods.FlipView("customer-registration.fxml", borderPaneContainer);
    }
    @FXML void accountsButtonClicked() throws IOException {
        SpecialMethods.FlipView("toll&payments.fxml", borderPaneContainer);
    }


}//end of class