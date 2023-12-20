package com.example.parkomania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.example.parkomania.HelloApplication.sceneManager;

public class MapController {
    //import fxid from fxml
    @FXML
    private Button reservePlace;
    @FXML
    private VBox shiftVBox;
    @FXML
    private ImageView place1;

    @FXML
    private ImageView place10;

    @FXML
    private ImageView place11;

    @FXML
    private ImageView place12;

    @FXML
    private ImageView place13;

    @FXML
    private ImageView place14;

    @FXML
    private ImageView place2;

    @FXML
    private ImageView place3;

    @FXML
    private ImageView place4;

    @FXML
    private ImageView place5;

    @FXML
    private ImageView place6;

    @FXML
    private ImageView place7;

    @FXML
    private ImageView place8;

    @FXML
    private ImageView place9;
    @FXML
    private Button goBackBtn;

    //methods for choosing corresponding place
    @FXML
    void bookPlace1(ActionEvent event) throws IOException {
        showReserveBtn(1);
//        setReserveImg(place1);
    }

    @FXML
    void bookPlace2(ActionEvent event) {
        showReserveBtn(2);
    }

    //sets reserve btn into pane and enables it. Place number will be used in other functions to send place id
    private void showReserveBtn(int placeNumber) {
        shiftVBox.getChildren().clear();
        shiftVBox.getChildren().add(reservePlace);
//        setReserveImg("place" + placeNumber);

        reservePlace.setVisible(true);
        reservePlace.setDisable(false);
    }

    //change img with white P to red P - indicating clicked button
    private void setReserveImg(ImageView correspondingPlace) throws IOException {
        String dirName = "./src/main/resources/com/example/parkomania/images/";

        // debug paths
//        Files.list(new File(dirName).toPath())
//                .limit(30)
//                .forEach(path -> {
//                    System.out.println(path);
//                });
        correspondingPlace.setImage(new Image("file:./src/main/resources/com/example/parkomania/images/choosed_place.png"));
    }

    // sends request, checks if place is free and locks it for some time
    @FXML
    void reservePlace(ActionEvent event) {
        sceneManager.switchScene("bookPlace");
    }

    //go back to previous scene
    @FXML
    void goBack(ActionEvent event) {
        sceneManager.switchScene("mainMenu");
    }
}

