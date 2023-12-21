package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import static com.example.parkomania.HelloApplication.sceneManager;

public class UserDataController {

    @FXML
    private Label email;

    @FXML
    private Button goBack;

    @FXML
    private Button goToAddVehicle;

    @FXML
    private Button goToChangePassword;

    @FXML
    private Label nrTel;

    @FXML
    private HBox vehicleContainer;

    @FXML
    private Label vehicleRegistration;


    @FXML
    void goToAddVehicle(ActionEvent event) {
        sceneManager.switchScene("addVehicle");
    }

    @FXML
    void goToChangePassword(ActionEvent event) {
        sceneManager.switchScene("changePassword");
    }
    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("account");
    }
}
