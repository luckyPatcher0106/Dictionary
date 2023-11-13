package com.example.dictionarydemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Dictionary Demo");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        //stage.initStyle(StageStyle.DECORATED);

        //stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        //stage.initStyle(StageStyle.UNIFIED);
        //stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}