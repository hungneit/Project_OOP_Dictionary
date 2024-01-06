module App {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires org.kordamp.bootstrapfx.core;
    requires freetts;
    requires java.sql;

    opens App to javafx.fxml;
    exports App;
}