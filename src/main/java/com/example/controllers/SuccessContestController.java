package com.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;
public class SuccessContestController extends MainController implements Initializable {
        @FXML
        Pane pane;
        @FXML
        Label tmp;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            tmp.setText(String.valueOf(ContestController.ratecount));
        }


}


