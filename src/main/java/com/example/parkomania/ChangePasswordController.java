package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static com.example.parkomania.HelloApplication.sceneManager;

public class ChangePasswordController {

    @FXML
    private TextField checkPhoneNumber;

    @FXML
    private Button goBack;

    @FXML
    private PasswordField newPassword;

    @FXML
    private Label passwordChangeSuccesful;

    @FXML
    private Label passwordChangeSuccesful11;

    @FXML
    private Label passwordChangeUnsuccesful;

    @FXML
    private Button submitNewPassword;

    @FXML
    void checkPhoneNumber(ActionEvent event) {

    }


    @FXML
    void newPassword(ActionEvent event) {

    }

    @FXML
    void submitNewPassword(ActionEvent event) {

    }
    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("userData");
    }

}
