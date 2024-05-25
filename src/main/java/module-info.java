module oop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
//    requires org.json;
    // requires org.yaml.snakeyaml;

    opens oop to javafx.fxml;
    exports oop;
}