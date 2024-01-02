package com.example.parkomania;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    public static SceneManager sceneManager;
    @Override
    public void start(Stage stage) throws IOException {
        // create instance of scene manager singleton
        sceneManager = SceneManager.getInstance(stage);

        sceneManager.loadScene("loginAndRegister", "loginAndRegister.fxml");
        sceneManager.loadScene("login", "login.fxml");
        sceneManager.loadScene("register", "register.fxml");
        sceneManager.loadScene("mainMenu", "mainMenu.fxml");
        sceneManager.loadScene("map", "map.fxml");
        sceneManager.loadScene("account", "account.fxml");
        sceneManager.loadScene("bookPlace", "bookPlace.fxml");
        sceneManager.loadScene("notifications", "notifications.fxml");
        sceneManager.loadScene("singleNotification", "singleNotification.fxml");
        sceneManager.loadScene("userData", "userData.fxml");
        sceneManager.loadScene("history", "history.fxml");
        sceneManager.loadScene("reservations", "reservations.fxml");
        sceneManager.loadScene("addVehicle", "addVehicle.fxml");
        sceneManager.loadScene("changePassword", "changePassword.fxml");

//        sceneManager.switchScene("mainMenu");
        sceneManager.switchScene("loginAndRegister");

        //Debugging scene manager singleton
//        System.out.println("Scene manager instance: " + sceneManager);

        //Debugging for one scene
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setScene(scene);


        //get path to image - platform independent
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        String path = Objects.requireNonNull(classLoader.getResource("/images/logo.jpg")).getPath();
//        stage.getIcons().add(new Image(path));
        stage.setTitle("ParkoMania");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}