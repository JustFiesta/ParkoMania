package com.example.parkomania;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// class for easy scene menagement
public class SceneManager {

    private static SceneManager instance;
    private final Stage stage;
    private final Map<String, Scene> scenes;

    private SceneManager(Stage stage) {
        this.stage = stage;
        this.scenes = new HashMap<>();
    }

    public static SceneManager getInstance(Stage stage) {
        if (instance == null) {
            instance = new SceneManager(stage);
        }
        return instance;
    }

    public void loadScene(String name, String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scenes.put(name, scene);
    }

    public void switchScene(String name) {
        Scene scene = scenes.get(name);
        if (scene != null) {
            stage.setScene(scene);
        } else {
            System.out.println("Scene not found: " + name);
        }
    }
}
