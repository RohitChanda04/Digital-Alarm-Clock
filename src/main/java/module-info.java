module com.example.digitalalarmclock {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.digitalalarmclock to javafx.fxml;
    exports com.example.digitalalarmclock;
}