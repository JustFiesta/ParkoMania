package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static com.example.parkomania.HelloApplication.sceneManager;

public class SingleNotificationController {

    @FXML
    private Button goBackBtn;

    @FXML
    private Button showMore;

    @FXML
    void showMore(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("notifications");
    }

}
