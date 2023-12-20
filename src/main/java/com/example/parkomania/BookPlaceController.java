package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import static com.example.parkomania.HelloApplication.sceneManager;

public class BookPlaceController {

    @FXML
    private Button addTime;

    @FXML
    private Label choosingTimer;

    @FXML
    private Button goBack;

    @FXML
    private Button showCalendar;

    @FXML
    private Button showInfo;

    @FXML
    private ImageView startAndStopImg;

    @FXML
    private Button startCounting;

    @FXML
    private Button substractTime;

    @FXML
    void substractTime(ActionEvent event) {
        System.out.println("-");
    }
    @FXML
    void addTime(ActionEvent event) {
        System.out.println("+");
    }

    @FXML
    void startCounting(ActionEvent event) {

    }

    @FXML
    void showCalendar(ActionEvent event) {

    }

    @FXML
    void showInfo(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("map");
    }
}
