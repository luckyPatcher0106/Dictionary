package com.example.dictionarydemo;


import com.darkprograms.speech.synthesiser.SynthesiserV2;
import com.example.dictionarydemo.UseForServices.Word;
import com.example.dictionarydemo.service.DictionaryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LearningController implements Initializable {

    private static Word searchText = new Word("", "", new ArrayList<>(), "");
    private final DictionaryService dictionaryManagement = new DictionaryService();
    public ImageView starIcon;
    public ImageView listIcon;
    public ImageView volumeIcon;
    public ImageView swapIcon;
    public ImageView searchIcon;
    public ImageView clockIcon;
    public TextField inputSearch;
    public ListView listViewSearch;
    public Label textResult;
    public Label textResultTranslated;
    public ImageView volumeIconTranslated;
    public Label textInterjection;
    public Label textRelation;
    public ListView interjections;
    public Button btnBackHome;

    private Stage stage;
    private Scene scene;


    @FXML
    protected void onSearchButtonClick() {
        System.out.println(inputSearch.getText());
    }
    SynthesiserV2 synthesizer ;

    public LearningController() {
        synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    }

    private void backToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home-view.fxml"));
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

    private void setDataItems(List<Word> words) {
        ObservableList<String> observableList = FXCollections.observableArrayList(words.stream().map(Word::getWordTarget).collect(Collectors.toList()));
        listViewSearch.setItems(observableList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image searchIconSource = new Image(String.valueOf(new File("src/main/java/com/example/ui_project/images/search_icon.png").toURI()));
        Image clockIconSource = new Image(String.valueOf(new File("src/main/java/com/example/ui_project/images/clock_icon.png").toURI()));
        Image listIconSource = new Image(String.valueOf(new File("src/main/java/com/example/ui_project/images/list_icon.png").toURI()));
        Image starIconSource = new Image(String.valueOf(new File("src/main/java/com/example/ui_project/images/star_icon.png").toURI()));
        Image swapIconSource = new Image(String.valueOf(new File("src/main/java/com/example/ui_project/images/swap_icon.png").toURI()));
        Image volumeIconSource = new Image(String.valueOf(new File("src/main/java/com/example/ui_project/images/volume_icon.png").toURI()));

        // TODO: init default text
        textResult.setText(searchText.getWordTarget());
        textResultTranslated.setText(searchText.getWordExplain());
        textRelation.setText(searchText.getRelation());

        // TODO: init image
        searchIcon.setImage(searchIconSource);
        clockIcon.setImage(clockIconSource);
        listIcon.setImage(listIconSource);
        starIcon.setImage(starIconSource);
        swapIcon.setImage(swapIconSource);
        volumeIcon.setImage(volumeIconSource);
        volumeIconTranslated.setImage(volumeIconSource);

        setDataItems(dictionaryManagement.getWords());

        inputSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Text changed to: " + newValue);
            setDataItems(dictionaryManagement.filterByKey(inputSearch.getText()));
        });

        listViewSearch.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                String selectedItem = (String) listViewSearch.getSelectionModel().getSelectedItem();
                searchText = dictionaryManagement.getWordByWordTarget(selectedItem);
                textResult.setText(searchText.getWordTarget());
                textResultTranslated.setText(searchText.getWordExplain());
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < searchText.getInterjections().size(); i++) {
                    s.append((i + 1)).append(". ").append(searchText.getInterjections().get(i)).append("\n");
                }
                textInterjection.setText(s.toString());
                textRelation.setText(searchText.getRelation());
            }
        });


        // TODO: Add voice english
        volumeIcon.setOnMouseClicked(mouseEvent -> {
            Thread thread = new Thread(() -> {
                try {
                    AdvancedPlayer player = new AdvancedPlayer(synthesizer.getMP3Data(searchText.getWordTarget()));
                    player.play();
                } catch (JavaLayerException | IOException e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(false);
            thread.start();
        });

        // TODO: Add voice vietnamese
        volumeIconTranslated.setOnMouseClicked(mouseEvent -> {
            Thread thread = new Thread(() -> {
                try {
                    AdvancedPlayer player = new AdvancedPlayer(synthesizer.getMP3Data(searchText.getWordExplain()));
                    player.play();
                } catch (JavaLayerException | IOException e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(false);
            thread.start();
        });
        btnBackHome.setOnAction(ActionEvent -> backToHome(ActionEvent));
    }


}