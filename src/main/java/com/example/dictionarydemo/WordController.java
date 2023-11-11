package com.example.dictionarydemo;

import com.example.dictionarydemo.service.DictionaryService;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Dictionary;
import java.util.ResourceBundle;

public class WordController implements Initializable {

    private DictionaryService dictionarymanagement = new DictionaryService();

    public TextField inputSearch;
    public ListView listViewWord;
    public TextField txtTarget;
    public TextField txtExplain;
    public TextArea txtInterjection;
    public TextField txtRelation;
    public Button btnInsert;
    public Button btnEdit;
    public Button btnDelete;
    public Button btnBack;
    private Stage stage;
    private Scene scene;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
