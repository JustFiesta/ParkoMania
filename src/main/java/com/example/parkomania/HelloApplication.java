package com.example.parkomania;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

public class HelloApplication extends Application {
    public static SceneManager sceneManager;
    public static User loggedUser;
    @Override
    public void start(Stage stage) throws IOException {
        // create instance of scene manager singleton
        sceneManager = SceneManager.getInstance(stage);

        sceneManager.loadScene("loginAndRegister", "templates/loginAndRegister.fxml");
        sceneManager.loadScene("login", "templates/login.fxml");
        sceneManager.loadScene("register", "templates/register.fxml");
        sceneManager.loadScene("mainMenu", "templates/mainMenu.fxml");
        sceneManager.loadScene("account", "templates/account.fxml");
        sceneManager.loadScene("notifications", "templates/notifications.fxml");
        sceneManager.loadScene("singleNotification", "templates/singleNotification.fxml");
        sceneManager.loadScene("addVehicle", "templates/addVehicle.fxml");
        sceneManager.loadScene("deleteVehicle", "templates/deleteVehicle.fxml");
        sceneManager.loadScene("changePassword", "templates/changePassword.fxml");

        sceneManager.switchScene("loginAndRegister");

        //Debugging scene manager singleton
//        System.out.println("Scene manager instance: " + sceneManager);

        //Debugging for one scene
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setScene(scene);


        //get path to image - platform independent
        URL iconStream = getClass().getResource("/com/example/parkomania/images/miniLogo.PNG");
        Image icon = new Image(String.valueOf(iconStream));
        stage.getIcons().add(icon);
        stage.setTitle("ParkoMania");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}