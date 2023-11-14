package com.example.controllers;

import api.GoogleAPI;
import api.TextToSpeechAPIOffline;
import api.TextToSpeechAPIOnline;
import com.example.mainApp.Notification;
import com.example.settings.AudioSetting;
import com.example.settings.cssSetting;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ParagraphController extends MainController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ChoiceBox<String> inputTypeLanguage;

    @FXML
    private ChoiceBox<String> outputTypeLanguage;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    @FXML
    public void initialize() {
        inputTypeLanguage.setItems(FXCollections.observableArrayList("English", "Vietnamese"));
        outputTypeLanguage.setItems(FXCollections.observableArrayList("Vietnamese", "English"));
        inputTypeLanguage.setValue("English");
        outputTypeLanguage.setValue("Vietnamese");
    }


    @FXML
    public void onTranslateButtonClick() throws IOException {
        String input = inputTextArea.getText();
        Thread thread = new Thread(() -> {
            String output = null;
            try {
                if (inputTypeLanguage.getValue().equals("English")) {
                    output = GoogleAPI.translate("en", "vi", input);
                } else {
                    output = GoogleAPI.translate("vi", "en", input);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String finaloutput = output;
            Platform.runLater(() -> outputTextArea.setText(finaloutput));
        });
        thread.setDaemon(true);
        thread.start();
        outputTextArea.setPromptText("Đang dịch...");
        outputTextArea.setText(null);
    }

    public void onPlayAudioInputBtn() {
        if (AudioSetting.getConfig()) {
            Thread thread = new Thread(() -> TextToSpeechAPIOnline.getTextToSpeech(inputTextArea.getText()));
            thread.setDaemon(true);
            thread.start();
        } else {
            Thread thread = new Thread(() -> TextToSpeechAPIOffline.getTextToSpeech(inputTextArea.getText()));
            thread.setDaemon(true);
            thread.start();
        }
    }

    public void onSwitchInputLanguage() {
        if (inputTypeLanguage.getValue().equals("English")) outputTypeLanguage.setValue("Vietnamese");
        else outputTypeLanguage.setValue("English");
    }

    public void onSwitchOutputLanguage() {
        if (outputTypeLanguage.getValue().equals("English")) inputTypeLanguage.setValue("Vietnamese");
        else inputTypeLanguage.setValue("English");
    }

    public void onPlayAudioOutputBtn() {
        if (AudioSetting.getConfig()) {
            Thread thread = new Thread(() -> TextToSpeechAPIOnline.getTextToSpeech(outputTextArea.getText()));
            thread.setDaemon(true);
            thread.start();
        } else {
            Thread thread = new Thread(() -> TextToSpeechAPIOffline.getTextToSpeech(outputTextArea.getText()));
            thread.setDaemon(true);
            thread.start();
        }
    }

    @FXML
    public void onInputCopyToClipboard() {
        copyToClipBoard(inputTextArea);
    }

    @FXML
    public void onOutputCopyToClipboard() {
        copyToClipBoard(outputTextArea);
    }


    public void copyToClipBoard(TextArea text) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(text.getText());
        clipboard.setContent(content);
        Notification.show("Copied To Clipboard", rootPane, cssSetting.getConfig());
    }
}
