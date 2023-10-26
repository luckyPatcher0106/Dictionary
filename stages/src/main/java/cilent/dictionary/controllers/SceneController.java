package cilent.dictionary.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PushbackReader;
import base.advanced.Dictionary;

import java.util.Objects;

public class SceneController {
    private static Stage stage;
    private static Parent parent;
    private static Scene scene;
    private static FXMLLoader root;

    public static String DARK_CSS = Objects.requireNonNull(SceneController.class.getResource("/cilent/css/application-dark.css")).toExternalForm();
    private static Stage getStage() {
        return stage;
    }

    private static Scene getScene() {
        return scene;
    }

    private static FXMLLoader getRoot() {
        return root;
    }

    public static void initializeApplication(Stage _stage, FXMLLoader _root) throws IOException {
        Dictionary.initialize();
        stage = _stage;
        root = _root.load();
        parent = root.load();
        scene = new Scene(parent, 1280, 720, false, SceneAntialiasing.BALANCED);
        scene.getStylesheets().add(DARK_CSS);
        renderScene();
    }

    public static void switchToOffline() throws IOException {
        root = new FXMLLoader(SceneController.class.getResource("/cilent/dictionary/offline-view.fxml"));
        parent = root.load();
        renderScene();
    }

//    public static void switchToSynonym() throws IOException {
//        root = new FXMLLoader(SceneController.class.getResource("/cilent/dictionary/synonym-view.fxml"));
//        parent = root.load();
//        renderScene();
//    }

    public static void switchToSettings() throws IOException {
        root = new FXMLLoader(SceneController.class.getResource("/cilent/dictionary/settings-view.fxml"));
        parent = root.load();
        renderScene();
    }

//    public static void switchToOnline() throws IOException {
//        root = new FXMLLoader(SceneController.class.getResource("/cilent/dictionary/online-view.fxml"));
//        parent = root.load();
//        renderScene();
//    }

    private static void renderScene() {
        scene.setRoot(parent);
        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();
    }
}
