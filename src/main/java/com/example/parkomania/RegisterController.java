package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.example.parkomania.HelloApplication.sceneManager;

public class RegisterController {

    @FXML
    private TextField checkPassword;

    @FXML
    private TextField checkPasswordAgain;

    @FXML
    private TextField checkPhoneNumber;

    @FXML
    private Button goBack;

    @FXML
    private Button subtNewUser;

    @FXML
    void checkPassword(ActionEvent event) {

    }

    @FXML
    void checkPasswordAgain(ActionEvent event) {

    }

    @FXML
    void checkPhoneNumber(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("loginAndRegister");
    }

    @FXML
    void subtNewUser(ActionEvent event) {

    }

}
