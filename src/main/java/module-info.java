module com.example.dictionarydemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.google.speech.api;
    requires jlayer;
    requires java.desktop;
    requires java.logging;
    requires java.sql;
    requires java.xml;

    opens com.example.dictionarydemo to javafx.fxml;
    exports com.example.dictionarydemo;
}