//package Game;
//
//import Game.GetDataFromFile.Data;
//import Game.Services.KeyButton;
//import Game.Services.LetterLabel;
//import Game.Services.WordTally;
//import javafx.beans.property.BooleanProperty;
//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleBooleanProperty;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.geometry.Pos;
//import javafx.scene.control.Button;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.TilePane;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static Game.Styles.LetterStyle.DisplayType.*;
//
//public class WordleController {
//
//    public Button enterButton;
//
//    public Button deleteButton;
//    public Button statsButton;
//
//    public Button infoButton;
//
//    public Button resetButton;
//
//    private FlowPane buttonFlowPane;
//
//    private TilePane letterTilePane;
//
//    private WordTally wordTally;
//
//    private List<LetterLabel> letterLabels;
//
//    private Map<String, KeyButton> keyButtons;
//
//    private final int ROWS = 6;
//
//    private int rownum = 0;
//
//    private final BooleanProperty isGameOver = new SimpleBooleanProperty(false);
//
//    private final BooleanProperty isGameReset = new SimpleBooleanProperty(true);
//
//    private final BooleanProperty processWord = new SimpleBooleanProperty(false);
//
//    private final IntegerProperty squareSize = new SimpleIntegerProperty(0);
//
//    private int rowsize;
//
//    private Data RandomWord;
//
//    public void initialize() {
//        letterTilePane.setAlignment(Pos.TOP_CENTER);
//        letterTilePane.setPrefRows(6);
//
//        letterLabels = letterTilePane.getChildren().stream().map(n -> (LetterLabel) n).collect(Collectors.toList());
//
//        rowsize = letterTilePane.getPrefColumns();
//
//        keyButtons = buttonFlowPane.getChildren()
//                .stream()
//                .map(n -> (KeyButton) n)
//                .filter(k -> k.getText().length()==1)
//                .collect(Collectors.toMap(KeyButton::getText, k -> k));
//
//        keyButtons.values().stream()
//                .forEach(k -> k.disableProperty().bind(isGameOver.or(isGameReset).or(processWord)));
//
//        enterButton.disableProperty().bind(squareSize.lessThan(ROWS)
//                .or(isGameOver));
//
//        deleteButton.disableProperty().bind(squareSize.isEqualTo(0)
//                .or(isGameOver));
//
//    }
//
//    public void restoreGame() {
//
//    }
//
//    public void resetGame() {
//        isGameOver.set(false);
//        doresetGame();
//    }
//
//    public void doresetGame(){}
//
//    public void selectLetter(ActionEvent actionEvent) {
//        String s = ((KeyButton) actionEvent.getSource()).getText();
//        doselectLetter(s);
//    }
//
//    public void doselectLetter(String letter) {
//
//    }
//
//
//    public void processEnter(ActionEvent actionEvent) {
//        doprocessEnter();
//    }
//
//    public void doprocessEnter(){
//
//    }
//
//
//    public void processDelete(ActionEvent actionEvent) {
//        doprocessDelete();
//    }
//
//    public void doprocessDelete(){
//
//    }
//
//    public void processWord(List<LetterLabel> list){
//
//        String input = list.stream().map(e -> e.getText()).reduce("", (a, b) -> a + b);
//        wordTally.setGuess(input);
//        wordTally.setTarget(RandomWord.getSingleWord());
//
//        if(!RandomWord.validWord(input)){
//            WordPopup("Invalid Word",enterButton);
//            animateBadWord(list);
//            processWord.set(false);
//            return;
//        }
//
//        doProcessMatching(list);
//        doProcessPartial(list);
//        doProcessNoMatch(list);
//        animateLabelGroup(list);
//    }
//    public void doProcessMatching(List<LetterLabel> list){
//        list.stream()
//                .filter(e -> wordTally.getGuess().charAt(list.indexOf(e)) == wordTally.getTarget().charAt(list.indexOf(e)))
//                .forEach(e -> {
//                    e.setMatchResult(MATCHING);
//
//                    int id = list.indexOf(e);
//
//        wordTally.setGuess(wordTally.getGuess().substring(0,id)
//                + wordTally.getGuess().substring(id+1));
//
//         wordTally.setTarget(wordTally.getTarget().substring(0,id)
//                 + wordTally.getTarget().substring(id+1));
//        });
//    }
//    public void doProcessPartial(List<LetterLabel> list){
//        list.stream()
//                .filter(e -> wordTally.getGuess().charAt(list.indexOf(e)) != '-')
//                .forEach(e -> {
//                char input = wordTally.getGuess().charAt(list.indexOf(e));
//
//                if(wordTally.getTarget().contains((Character.toString(input)))){
//                e.setMatchResult(PARTIALMATCH);
//
//                int id = wordTally.getGuess().indexOf(input);
//                int id2 = list.indexOf(e);
//
//                wordTally.setGuess(wordTally.getGuess().substring(0,id)
//                + wordTally.getGuess().substring(id+1));
//
//                wordTally.setTarget(wordTally.getTarget().substring(0,id2)
//                + wordTally.getTarget().substring(id2+1));
//
//                }
//        });
//    }
//
//    public void doProcessNoMatch(List<LetterLabel> list){
//        list.stream()
//                .filter(e -> wordTally.getGuess().charAt(list.indexOf(e)) != '-')
//                .forEach(ll -> ll.setMatchResult(NOMATCH));
//    }
//}
