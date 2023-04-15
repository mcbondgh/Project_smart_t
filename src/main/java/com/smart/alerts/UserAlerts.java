package com.smart.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;

public class UserAlerts {

    String title,headerText,contentText;

    public UserAlerts(String title, String headerText, String contentText) {
        this.title = title;
        this.headerText = headerText;
        this.contentText = contentText;
    }
    public UserAlerts(String title, String headerText) {
        this.title = title;
        this.headerText = headerText;
    }

    public void warningAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(this.title);
        alert.setHeaderText(this.headerText);
        alert.setContentText(this.contentText);
        alert.show();
    }

    public void information() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(this.title);
        alert.setHeaderText(this.headerText);
        alert.setContentText(this.contentText);
        alert.show();
    }

    public boolean confirmation() {
        boolean flag = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, contentText);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().remove(ButtonType.OK);
        if (alert.showAndWait().get().equals(ButtonType.YES)) {
            flag = true;
        }
        return flag;
    }



}








