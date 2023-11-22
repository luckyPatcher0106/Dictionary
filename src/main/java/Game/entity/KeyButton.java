package Game.entity;

import static Game.Styles.LetterStyle.MATCHING_PSEUDO_CLASS;
import static Game.Styles.LetterStyle.NOMATCH_PSEUDO_CLASS;
import static Game.Styles.LetterStyle.PARTIALMATCH_PSEUDO_CLASS;
import static Game.Styles.LetterStyle.PLAIN_PSEUDO_CLASS;
import Game.Styles.LetterStyle.DisplayType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;

public class KeyButton extends Button {

    public KeyButton() {
        super();
        getStylesheets().add("matching-letter");
        getStylesheets().add("nomatch-letter");
        getStylesheets().add("partialmatch-letter");
        getStylesheets().add("plain-letter");
    }

    public final ObjectProperty<DisplayType> wordDisplay = new SimpleObjectProperty<>(DisplayType.PLAIN){

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
    public DisplayType getWordDisplay() {
        return wordDisplay.get();
    }
    public void setWordDisplay(DisplayType labelType) {
        this.wordDisplay.set(labelType);
    }
}
