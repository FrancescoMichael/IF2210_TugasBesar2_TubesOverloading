module tubesoverloading.kerajaan {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens tubesoverloading.kerajaan to javafx.fxml;
    exports tubesoverloading.kerajaan;
}