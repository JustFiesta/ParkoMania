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
    private Label registrationNumber;

    @FXML
    private Label reservationInfo;

    @FXML
    private Button showMoreNotaficationsBtn;

    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("notifications");
    }

}
