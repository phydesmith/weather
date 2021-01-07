module weather {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.net.http;
    requires com.google.gson;
    requires java.desktop;

    opens io.javasmithy.controllers to javafx.base, javafx.fxml, javafx.controls, javafx.graphics, com.google.gson;
    opens io.javasmithy.geo to javafx.base, javafx.fxml, javafx.controls, javafx.graphics, com.google.gson;
    opens io.javasmithy to javafx.base, javafx.fxml, javafx.controls, javafx.graphics, com.google.gson;

    exports io.javasmithy.geo to com.google.gson;
    exports io.javasmithy.controllers to javafx.base, javafx.fxml, javafx.controls, javafx.graphics;

}