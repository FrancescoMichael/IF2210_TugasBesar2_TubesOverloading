module oop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens oop to javafx.fxml;
    exports oop;
}
