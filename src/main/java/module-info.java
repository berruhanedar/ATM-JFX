module com.berru.app.atmjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires java.sql;
    requires com.h2database;
    requires jbcrypt;
    requires org.apache.poi.ooxml;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires java.mail;

    opens com.berru.app.atmjfx.dto to javafx.base, lombok;
    opens com.berru.app.atmjfx.controller to javafx.fxml;
    opens com.berru.app.atmjfx.dao to java.sql;
    opens com.berru.app.atmjfx.database to java.sql;

    exports com.berru.app.atmjfx.dao;
    exports com.berru.app.atmjfx.database;
    exports com.berru.app.atmjfx;

    opens com.berru.app.atmjfx.utils to javafx.base, lombok;
}