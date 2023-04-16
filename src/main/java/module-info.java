module carrent.carapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens carrent.carapp to javafx.fxml;
    exports carrent.carapp;
    exports controllers;
    exports Domain;
    opens controllers to javafx.fxml;
}