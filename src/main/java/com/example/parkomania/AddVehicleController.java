package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static com.example.parkomania.HelloApplication.sceneManager;

public class AddVehicleController {

    @FXML
    private Label addVehicleSuccesful;

    @FXML
    private Label addVehicleUnsuccesful;

    @FXML
    private TextField checkVehicle;

    @FXML
    private Button goBack;

    @FXML
    private Label passwordChangeSuccesful11;

    @FXML
    private Button submitNewVehicle;

    @FXML
    void checkVehicle(ActionEvent event) {

    }

    @FXML
    void submitNewVehicle(ActionEvent event) {

    }
    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("userData");
    }

}
