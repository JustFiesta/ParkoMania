package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import static com.example.parkomania.HelloApplication.sceneManager;

public class HistoryController {
    @FXML
    private Button goBack;
    @FXML //Reservation
    private TableView<?> historyTable;
    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("account");
    }
}
