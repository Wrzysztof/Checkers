module com.example.checkers {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    //opens com.example.checkers to javafx.fxml;
    //exports com.example.checkers;
    exports com.example.checkers.client;
    opens com.example.checkers.client to javafx.fxml;
}