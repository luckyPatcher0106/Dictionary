package Game.Services;

import Game.Styles.LetterStyle;

public class LetterState {
    public LetterStyle.DisplayType displayType;

    private String letter;

    public LetterState(LetterStyle.DisplayType displayType, String letter) {
        this.displayType = displayType;
        this.letter = letter;
    }

    public LetterStyle.DisplayType displayType() {
        return displayType;
    }

    public void setDisplayType(LetterStyle.DisplayType displayType) {
        this.displayType = displayType;
    }

    public String letter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }


}
