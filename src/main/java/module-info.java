module com.example.waplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.waplication to javafx.fxml;
    exports com.example.waplication;
}