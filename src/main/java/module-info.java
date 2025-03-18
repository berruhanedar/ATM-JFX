module com.berru.app.atmjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires java.sql;
    requires java.desktop;
    requires eu.hansolo.tilesfx;
    requires com.h2database;

    opens com.berru.app.atmjfx to javafx.fxml;
    exports com.berru.app.atmjfx;
}