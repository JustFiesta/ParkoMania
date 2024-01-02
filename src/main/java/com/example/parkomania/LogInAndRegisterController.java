package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static com.example.parkomania.HelloApplication.sceneManager;

public class LogInAndRegisterController {

    @FXML
    private Button goToLogInPage;

    @FXML
    private Button goToRegisterPage;

    @FXML
    void goToLogInPage(ActionEvent event) {
        sceneManager.switchScene("login");
    }

    @FXML
    void goToRegisterPage(ActionEvent event) {
        sceneManager.switchScene("register");
    }

}
