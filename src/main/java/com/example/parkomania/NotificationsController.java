package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import static com.example.parkomania.HelloApplication.sceneManager;

public class NotificationsController {

    @FXML
    private Button goBackBtn;

    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("mainMenu");
    }
}
