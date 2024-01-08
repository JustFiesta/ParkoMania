package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static com.example.parkomania.HelloApplication.loggedUser;
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
    void logOut(ActionEvent event) {
        System.gc();
        sceneManager.switchScene("loginAndRegister");
        System.out.println("Debug: Log out");
        loggedUser = null;
    }
    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("mainMenu");
    }
}
