package tubesoverloading.kerajaan;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.RotateEvent;

public class GUIController {

    @FXML
    private void switchToSecondary() throws IOException {
        GUI.setRoot("secondary");
    }
    @FXML
    void trvvt(RotateEvent event) {

    }
}