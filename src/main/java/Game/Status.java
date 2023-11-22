package Game;

import Game.Services.LetterState;
import Game.Styles.LetterStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Status {

    private static Status instance = null;

    public static Status getInstance() {
        if (instance == null) {
            instance = new Status();
        }
        return instance;
    }

    private List<LetterState> WordLabel = new ArrayList<>();

    private Map<String, LetterStyle.DisplayType> KeyBoardState = new HashMap<>();


    public List<LetterState> getWordLabel() {
        return WordLabel;
    }

    public Map<String, LetterStyle.DisplayType> getKeyBoardState() {
        return KeyBoardState;
    }


    public void setWordLabel(List<LetterState> WordLabel) {
        this.WordLabel = WordLabel;
    }

    public void setKeyBoardState(Map<String, LetterStyle.DisplayType> KeyBoardState) {
        this.KeyBoardState = KeyBoardState;
    }

}
