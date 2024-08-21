module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;

    opens ServerSide to javafx.graphics, javafx.fxml;
    exports ServerSide;

    opens Application to javafx.graphics, javafx.fxml;
    exports Application;
}
