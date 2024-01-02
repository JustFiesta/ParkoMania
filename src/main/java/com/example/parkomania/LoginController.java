package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static com.example.parkomania.HelloApplication.sceneManager;

public class LoginController {
    @FXML
    private Button goBack;

    @FXML
    private Button logIn;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    void checkPassword(ActionEvent event) {

    }

    @FXML
    void checkPhoneNum(ActionEvent event) {

    }


    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("loginAndRegister");
    }

    @FXML
    void logIn(ActionEvent event) {
        System.out.println("Checking database");
//        if (success){
//            create new user singleton
//        }else {
//            somtehing went wrong, try again
//        }
    }

}
