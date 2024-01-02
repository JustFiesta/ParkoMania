package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static com.example.parkomania.HelloApplication.sceneManager;

public class AccountController {
    @FXML
    private Button goBack;

    @FXML
    private Button goToUserData;

    @FXML
    private Button goToHistory;

    @FXML
    private Button goToReservation;

    @FXML
    private Button goToSettings;

    @FXML
    private Button logOut;
    @FXML
    void goToUserData(ActionEvent event) {
        sceneManager.switchScene("userData");
    }

    @FXML
    void goToHistory(ActionEvent event) {
        sceneManager.switchScene("history");
    }

    @FXML
    void goToReservations(ActionEvent event) {
        sceneManager.switchScene("reservations");
    }

    @FXML
    void goToSettings(ActionEvent event) {
        sceneManager.switchScene("settings");
    }

    @FXML
    void logOut(ActionEvent event) {
        System.out.println("Log out");
    }
    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("mainMenu");
    }
}
