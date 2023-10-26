module cilent.dictionary.stages {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires mongo.java.driver;
    requires java.logging;


    opens cilent.dictionary.stages to javafx.fxml;
    exports cilent.dictionary.stages;
}