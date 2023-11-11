package com.example.dictionarydemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {
    public Button learningBtn;
    public Button playGameBtn;
    public Button workManagementBtn;
    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        learningBtn.setOnAction(this::openLearningView);
        playGameBtn.setOnAction(actionEvent -> openPlayGameView());
        workManagementBtn.setOnAction(actionEvent -> openWordManagement(actionEvent));
    }

    public void openLearningView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("learning-view.fxml"));
            Parent root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Học từ vựng");

            scene = new Scene(root);

            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openPlayGameView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Chơi game");

            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void openWordManagement(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("work-management-view.fxml"));
                Parent root = loader.load();

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Quản lý từ vựng");

                scene = new Scene(root);

                stage.setScene(scene);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}

