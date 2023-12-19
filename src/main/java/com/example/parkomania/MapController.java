package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MapController {
    @FXML
    private Button reserveBtn;
    @FXML
    private VBox shiftVBox;

    //methods for choosing corresponding place
    @FXML
    void bookPlace1(ActionEvent event) {
        showReserveBtn(1);
    }

    @FXML
    void bookPlace2(ActionEvent event) {

    }

    //sets reserve btn into pane and enables it
    private void showReserveBtn(int placeNumber) {
        shiftVBox.getChildren().clear();
        shiftVBox.getChildren().add(reserveBtn);

        reserveBtn.setVisible(true);
        reserveBtn.setDisable(false);
    }
}

