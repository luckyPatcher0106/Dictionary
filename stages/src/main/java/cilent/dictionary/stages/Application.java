package cilent.dictionary.stages;

import cilent.dictionary.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/cilent/dictionary/offline-view.fxml"));
        SceneController.initializeApplication(stage, root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
