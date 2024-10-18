module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;

    opens application to javafx.fxml;
    opens model to javafx.base;
    exports application;
    
}
