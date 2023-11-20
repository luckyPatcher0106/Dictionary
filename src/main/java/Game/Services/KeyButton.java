package Game.Services;

import Game.Styles.LetterStyle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import static Game.Styles.LetterStyle.*;

public class KeyButton extends javafx.scene.control.Button {

    public KeyButton() {
        getStylesheets().add("matching-letter");
        getStylesheets().add("nomatch-letter");
        getStylesheets().add("partialmatch-letter");
        getStylesheets().add("plain-letter");
    }

    public final ObjectProperty<LetterStyle.DisplayType> wordDisplay = new SimpleObjectProperty<>(LetterStyle.DisplayType.PLAIN){

        @Override
        public void invalidated() {
            pseudoClassStateChanged( PLAIN_PSEUDO_CLASS, false);
            pseudoClassStateChanged(NOMATCH_PSEUDO_CLASS, false);
            pseudoClassStateChanged(PARTIALMATCH_PSEUDO_CLASS, false);
            pseudoClassStateChanged(MATCHING_PSEUDO_CLASS, false);
            switch (get()) {
                case PLAIN -> pseudoClassStateChanged(PLAIN_PSEUDO_CLASS, true);
                case NOMATCH -> pseudoClassStateChanged(NOMATCH_PSEUDO_CLASS, true);
                case PARTIALMATCH -> pseudoClassStateChanged(PARTIALMATCH_PSEUDO_CLASS, true);
                case MATCHING -> pseudoClassStateChanged(MATCHING_PSEUDO_CLASS, true);
                default -> pseudoClassStateChanged(PLAIN_PSEUDO_CLASS, true);
            }

        }

    };
    public LetterStyle.DisplayType getWordDisplay() {
        return wordDisplay.get();
    }
    public void setWordDisplay(LetterStyle.DisplayType labelType) {
        this.wordDisplay.set(labelType);
    }
}
