package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static com.example.parkomania.HelloApplication.sceneManager;

public class MainMenuController {
    @FXML
    private Button goToAccount;

    @FXML
    private Button goToMap;

    @FXML
    private Button goToNotifications;
    @FXML
    void goToAccount(ActionEvent event) {
//        System.out.println("Switching to Account");
        sceneManager.switchScene("account");
    }

    @FXML
    void goToMap(ActionEvent event) throws IOException {
//        System.out.println("Switching to Map");
        sceneManager.switchScene("map");
        sceneManager.loadScene("bookPlace", "templates/bookPlace.fxml");
    }

    @FXML
    void goToNotifications(ActionEvent event) {
//        System.out.println("Switching to Notifications");
        sceneManager.switchScene("notifications");
    }
}
