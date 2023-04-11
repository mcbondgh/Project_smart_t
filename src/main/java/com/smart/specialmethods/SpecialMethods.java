package com.smart.specialmethods;

import com.smart.AppStarter;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;


public class SpecialMethods {

    public static void FlipView(String fxmlFileName, BorderPane displayContainer) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppStarter.class.getResource(fxmlFileName));
        displayContainer.setCenter(fxmlLoader.load());
    }



}//End of class
