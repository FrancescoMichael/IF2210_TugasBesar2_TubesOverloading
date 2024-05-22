module tubesoverloading.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens tubesoverloading.demo to javafx.fxml;
    exports tubesoverloading.demo;
}