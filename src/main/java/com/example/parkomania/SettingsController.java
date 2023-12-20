package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static com.example.parkomania.HelloApplication.sceneManager;

public class SettingsController {
    @FXML
    private Button goBack;

    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("account");
    }
}
