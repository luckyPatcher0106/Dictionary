module client.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
//    requires javafx.web;
    requires javafx.graphics;
//    requires com.dlsc.formsfx;
//    requires org.apache.commons.text;
//    requires org.controlsfx.controls;
    requires org.json;
//    requires org.kordamp.bootstrapfx.core;
//    requires org.kordamp.ikonli.javafx;
//    requires validatorfx;
//    requires freetts;
    requires mongo.java.driver;
    requires java.desktop;
//    requires voicerss.tts;
//    requires javafx.media;
    requires java.logging;
    requires org.apache.commons.text;

    opens com.example to javafx.fxml;
    exports com.example.controllers;
    opens com.example.controllers to javafx.fxml;
    exports com.example.mainApp;
    opens com.example.mainApp to javafx.fxml;
}