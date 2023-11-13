package com.example.mainApp;

import com.example.controllers.PopController;
import com.example.controllers.SwitchController;
import com.example.settings.cssSetting;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class popWindow {
    public popWindow(String title, String word) {
        try {
            Stage stage = new Stage();
            FXMLLoader root = new FXMLLoader(getClass().getResource("/com/example/pop.fxml"));
            Scene scene = new Scene(root.load(), 720, 720, false, SceneAntialiasing.BALANCED);
            scene.getStylesheets().add(cssSetting.getConfig() ? SwitchController.DARK_CSS : SwitchController.LIGHT_CSS);
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(SwitchController.getStage());
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            PopController popController = root.getController();
            popController.setWordTextField(word);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
