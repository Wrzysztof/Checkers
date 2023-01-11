module com.example.checkers {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.checkers.server to javafx.fxml;
    exports com.example.checkers.server;
    exports com.example.checkers.client;
    opens com.example.checkers.client to javafx.fxml;
    exports com.example.checkers.client.controller;
    opens com.example.checkers.client.controller to javafx.fxml;
}