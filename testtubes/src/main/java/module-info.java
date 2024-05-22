module tubesoverloading.testtubes {
    requires javafx.controls;
    requires javafx.fxml;


    opens tubesoverloading.testtubes to javafx.fxml;
    exports tubesoverloading.testtubes;
}