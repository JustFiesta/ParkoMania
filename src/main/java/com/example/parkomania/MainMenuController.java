package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    }

    @FXML
    void goToNotifications(ActionEvent event) {
//        System.out.println("Switching to Notifications");
        sceneManager.switchScene("notifications");
    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            sceneManager.loadScene("bookPlace", "templates/bookPlace.fxml");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
