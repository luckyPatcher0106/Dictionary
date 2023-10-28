package com.example.controllers;

import base.advanced.Dictionary;
//import client.dictionary.configs.Config;
//import client.dictionary.configs.CssConfig;
//import client.dictionary.configs.DatabaseConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SwitchController extends MainController{
    private static Stage stage;
    private static Scene scene;
    private static FXMLLoader root;
    private static Parent parent;

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static FXMLLoader getRoot() {
        return root;
    }

    public static void initializeApplication(Stage _stage, FXMLLoader _root) throws IOException {
        root = _root;
        parent = root.load();
        Dictionary.initialize();
        stage = _stage;
        scene = new Scene(parent, 1280, 720, false, SceneAntialiasing.BALANCED);
        scene.getStylesheets().add("com/example/application-dark.css");
        renderScene();
    }

    public static void switchToWord() throws IOException {
        root = new FXMLLoader(SwitchController.class.getResource("/com/example/word.fxml"));
        parent = root.load();
        renderScene();
    }

    public static void switchToParagraph() throws IOException {
        root = new FXMLLoader(SwitchController.class.getResource("/com/example/paragraph.fxml"));
        parent = root.load();
        renderScene();
    }

    public static void switchToPop() throws IOException {
        root = new FXMLLoader(SwitchController.class.getResource("/com/example/pop.fxml"));
        parent = root.load();
        renderScene();
    }

    public static void switchToSetting() throws IOException {
        root = new FXMLLoader(SwitchController.class.getResource("/com/example/setting.fxml"));
        parent = root.load();
        renderScene();
    }

    public static void switchToSynonym() throws IOException {
        root = new FXMLLoader(SwitchController.class.getResource("/com/example/synonyms.fxml"));
        parent = root.load();
        renderScene();
    }

    public static void renderScene() {
        scene.setRoot(parent);
        stage.setTitle("ThanhNhuTrong");
        stage.setScene(scene);
        stage.show();
    }
}