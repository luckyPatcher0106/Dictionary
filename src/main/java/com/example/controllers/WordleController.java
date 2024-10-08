package com.example.controllers;

import Game.GetDataFromFile.Data;
import Game.Services.WordPopUp;
import Game.Status;
import Game.Services.LetterState;
import Game.Services.WordTally;
import Game.Styles.LetterStyle;
import javafx.animation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static Game.Styles.LetterStyle.DisplayType.*;
import static java.awt.AWTEventMulticaster.add;


public class WordleController {

    public Button enterButton;

    public Button deleteButton;
    public Button statsButton;

    public Button infoButton;

    public Button resetButton;
    public AnchorPane Anchorpane;

    @FXML
    private FlowPane buttonFlowPane;

    @FXML
    private TilePane letterTilePane;

    private WordTally wordTally;

    private List<Label> letterLabels;

    private Map<String, Button> keyButtons;

    private final int ROWS = 6;

    private int rownum = 0;

    private final BooleanProperty isGameOver = new SimpleBooleanProperty(false);

    private final BooleanProperty isGameReset = new SimpleBooleanProperty(true);

    private final BooleanProperty processWord = new SimpleBooleanProperty(false);

    private final IntegerProperty squareSize = new SimpleIntegerProperty(0);

    private int rowsize;

    private static final Data RandomWord = Data.getInstance();

    private static final Status GameStatus = Status.getInstance();

    private static final WordPopUp WordPopup = new WordPopUp();

    private final List<String> messages = Arrays.asList();


    public void initialize() {
        Anchorpane.getStylesheets().add(getClass().getResource("/com/css/Wordle.css").toExternalForm());;

        letterTilePane.setAlignment(Pos.TOP_CENTER);
        letterTilePane.setPrefRows(6);

        letterLabels = letterTilePane.getChildren().stream().map(n -> (Label) n).collect(Collectors.toList());

        rowsize = letterTilePane.getPrefColumns();

        keyButtons = buttonFlowPane.getChildren()
                .stream()
                .map(n -> (Button) n)
                .filter(k -> k.getText().length() == 1)
                .collect(Collectors.toMap(Button::getText, k -> k));

        keyButtons.values().stream()
                .forEach(k -> k.disableProperty().bind(isGameOver.or(isGameReset).or(processWord)));

        enterButton.disableProperty().bind(squareSize.lessThan(ROWS)
                .or(isGameOver));

        deleteButton.disableProperty().bind(squareSize.isEqualTo(0)
                .or(isGameOver));

        if (isGameReset.get()) {
            doresetGame();
        }
    }
    
    public void resetGame() {
        isGameOver.set(false);
        doresetGame();
    }

    public void doresetGame() {
        RandomWord.setRandWord();
        isGameOver.set(false);
        processWord.set(false);
        squareSize.set(0);
        rownum = 0;

////        letterLabels.stream()
////                .forEach(ll -> ll.reset());
//
////        keyButtons.values().stream().forEach(e -> e.setWordDisplay(PLAIN));
//
//        GameStatus.setKeyBoardState(keyButtons.values().stream().collect(
//                Collectors.toMap(KeyButton -> KeyButton.getText())));
////                        KeyButton -> KeyButton.getWordDisplay())));
//
//        GameStatus.setWordLabel(letterLabels.stream().map(
////                        ll -> new LetterState(ll.getLetterDisplay(), ll.getText()))
//                .collect(Collectors.toList()));
    }

    public void selectLetter(ActionEvent actionEvent) {
        String s = ((Button) actionEvent.getSource()).getText();
        doselectLetter(s);
    }

    public void doselectLetter(String letter) {
        isGameReset.set(false);
        int currentSquareNum = squareSize.get();
        int index = currentSquareNum + (rowsize * rownum);

// Yêu cầu focus cho phím được chọn
        keyButtons.get(letter).requestFocus();

// Thiết lập nội dung và hiển thị cho chữ cái
        Label currentLetter = letterLabels.get(index);
        currentLetter.setText(letter);
        currentLetter.getStyleClass().add("letter");

// Tăng giá trị của squarenum
        squareSize.set(currentSquareNum + 1);

// Kiểm tra xem hàng có được điền đầy không
        if (squareSize.get() >= rowsize) {
            infoButton.getScene().getRoot().requestFocus();
        }
    }

    public void processDelete(ActionEvent actionEvent) {
        doprocessDelete();
    }

    public void doprocessDelete() {
        int id = squareSize.get() - 1 + (rownum * rowsize);
        squareSize.set(squareSize.get() - 1);
//        letterLabels.get(id).reset();
//        infoButton.getScene().getRoot().requestFocus();
    }

    public void processEnter(ActionEvent actionEvent) {
        doprocessEnter();
    }

    public void doprocessEnter() {
        isGameReset.set(false);
        processWord.set(true);
        List<Label> workinglists = letterLabels.stream()
                .skip(rownum * rowsize)
                .limit(rowsize)
                .collect(Collectors.toList());

        processWord(workinglists);
    }
    public void processWord(List<Label> list) {

        String input = list.stream().map(e -> e.getText()).reduce("", (a, b) -> a + b);
        wordTally.setGuess(input);
        wordTally.setTarget(RandomWord.getSingleWord());

        if (!RandomWord.validWord(input)) {
            //WordPopUp("Invalid Word",enterButton);
            animateBadWord(list);
            processWord.set(false);
            return;
        }

        doProcessMatching(list);
        doProcessPartial(list);
        doProcessNoMatch(list);
        animateLabelGroup(list);
    }

    public void doProcessMatching(List<Label> list) {
        list.stream()
                .filter(e -> wordTally.getGuess().charAt(list.indexOf(e)) == wordTally.getTarget().charAt(list.indexOf(e)))
                .forEach(e -> {
//                    e.setMatchResult(MATCHING);

                    int id = list.indexOf(e);

                    wordTally.setGuess(wordTally.getGuess().substring(0, id)
                            + "-" + wordTally.getGuess().substring(id + 1));

                    wordTally.setTarget(wordTally.getTarget().substring(0, id)
                            + "-" + wordTally.getTarget().substring(id + 1));
                });
    }

    public void doProcessPartial(List<Label> list) {
        list.stream()
                .filter(e -> wordTally.getGuess().charAt(list.indexOf(e)) != '-')
                .forEach(e -> {
                    char input = wordTally.getGuess().charAt(list.indexOf(e));

                    if (wordTally.getTarget().contains((Character.toString(input)))) {
//                        e.setMatchResult(PARTIALMATCH);

                        int id = wordTally.getGuess().indexOf(input);
                        int id2 = list.indexOf(e);

                        wordTally.setGuess(wordTally.getGuess().substring(0, id)
                                + "-" + wordTally.getGuess().substring(id + 1));

                        wordTally.setTarget(wordTally.getTarget().substring(0, id2)
                                + "-" + wordTally.getTarget().substring(id2 + 1));

                    }
                });
    }

    public void doProcessNoMatch(List<Label> list) {
        list.stream()
                .filter(e -> wordTally.getGuess().charAt(list.indexOf(e)) != '-');
//                .forEach(ll -> ll.setMatchResult(NOMATCH));
    }

    public void animateLabelGroup(List<Label> list) {
        SequentialTransition seq = new SequentialTransition();
        list.stream()
                .forEach(ll -> {
                    String letterText = ll.getText();
                    FadeTransition fade = new FadeTransition(
                            Duration.millis(100), ll);
                    fade.setAutoReverse(true);
                    fade.setCycleCount(2);
                    fade.setToValue(0);

                    fade.setDelay(Duration.millis(20));
                    fade.setOnFinished(x -> {
                        ll.setText("");
//                        ll.setLetterDisplay(ll.getMatchResult());
                    });

                    RotateTransition rotate = new RotateTransition(
                            Duration.millis(400), ll);
                    rotate.setAxis(Rotate.X_AXIS);
                    rotate.setByAngle(180);
                    rotate.setOnFinished(x -> {
                        ll.setRotate(0);
                        ll.setText(letterText);
                    });

                    ParallelTransition para = new ParallelTransition();
                    para.getChildren().addAll(fade, rotate);
                    seq.getChildren().add(para);
                });
        //seq.setOnFinished(x -> gameHouseKeeping(list));
        seq.play();
    }

    private void animateSuccessGroup(List<Label> list) {
        SequentialTransition seq = new SequentialTransition();
        list.stream()
                .forEach(ll -> {
                    RotateTransition rotate = new RotateTransition(
                            Duration.millis(100), ll);
                    rotate.setAxis(Rotate.Z_AXIS);
                    rotate.setByAngle(60);
                    rotate.setAutoReverse(true);
                    rotate.setCycleCount(4);
                    seq.getChildren().add(rotate);
                });
        seq.setDelay(Duration.millis(100));
        seq.play();
    }

    private void animateBadWord(List<Label> list) {
        ParallelTransition para = new ParallelTransition();
        list.stream()
                .forEach(ll -> {
                    TranslateTransition translate1 = new TranslateTransition(
                            Duration.millis(100), ll);
                    translate1.setByX(20);
                    translate1.setAutoReverse(true);
                    translate1.setCycleCount(6);
                    para.getChildren().add(translate1);
                });
        para.play();
    }

//    private void gameHouseKeeping(List<Label> list) {
//////        processWord.set(false);
//////        list.stream()
//////                .forEach(ll ->
//////                        keyButtons.get(ll.getText()).setWordDisplay(ll.getMatchResult())
//////                );
//////
//////        if ((list.stream()
//////                .filter(c -> !c.getMatchResult().equals(MATCHING))
//////                .count() == 0)) {   //match!
//////            System.out.println("Winner! You guessed the word " + RandomWord.getSingleWord());
//////            WordPopup.show(messages.get(rownum), enterButton);
//////            //animateSuccessGroup(list);
//////            //updateGameState(true);
////        } else if (rownum + 1 >= letterTilePane.getPrefRows()) {
////            // You took too many guesses
////            //WordPopup.show("Sorry! You lose this time!\nThe word is " + WordData.getTheWord(), enterButton);
////            //updateGameState(false);
////        } else // Update letter grid
////            if (squareSize.get() >= rowsize) {
////                squareSize.set(0);
////                rownum++;
////                if (rownum >= letterTilePane.getPrefRows()) {
////                    rownum = 0;
////                }
////            }
////    }


    public void switchToInfo(ActionEvent actionEvent) {

    }

    public void BachHome(ActionEvent actionEvent) {
    }
}



