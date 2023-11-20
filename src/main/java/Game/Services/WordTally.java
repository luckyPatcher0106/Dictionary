package Game.Services;

public class WordTally {
    private String guess;//string input

    private String target;//right answer

    public WordTally() {
        this.guess = "";
        this.target = "";
    }

    public WordTally(String guess, String target) {
        this.guess = guess;
        this.target = target;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
