package com.smart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AppStarter extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginStage();
    }

    public static void LoginStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppStarter.class.getResource("login-file.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Login");
        Image logo = new Image("E:\\JAVA APPLICATIONS\\Smart T\\smartT\\src\\main\\resources\\com\\img\\smart-t.png");
        stage.getIcons().add(logo);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }


    public static void Homepage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppStarter.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Homepage");
        Image logo = new Image("E:\\JAVA APPLICATIONS\\Smart T\\smartT\\src\\main\\resources\\com\\img\\smart-t.png");
        stage.getIcons().add(logo);
        stage.sizeToScene();
        stage.setTitle("HOMEPAGE");
        stage.isFullScreen();
        stage.setResizable(true);
        stage.getScene().getWindow().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "you can only log out with the signout button");
            alert.setTitle("Close Button Clicked");
            alert.setHeaderText("LOGOUT BY CLICKING THE LOG OUT BUTTON.");
            alert.showAndWait();
        });
        stage.show();
    }






    public static void main(String[] args) {
        launch();
    }
}