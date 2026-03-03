module com.example.licznikslow {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.licznikslow to javafx.fxml;
    exports com.example.licznikslow;
}