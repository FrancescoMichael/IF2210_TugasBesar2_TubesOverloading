package oop;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;

public class FieldController implements Initializable{

    @FXML
    private ImageView ActiveDeck1;

    @FXML
    private ImageView ActiveDeck2;

    @FXML
    private ImageView ActiveDeck3;

    @FXML
    private ImageView ActiveDeck4;

    @FXML
    private ImageView ActiveDeck5;

    @FXML
    private ImageView ActiveDeck6;

    @FXML
    private ImageView grid11;

    @FXML
    private ImageView grid12;

    @FXML
    private ImageView grid13;

    @FXML
    private ImageView grid14;

    @FXML
    private ImageView grid15;

    @FXML
    private ImageView grid21;

    @FXML
    private ImageView grid22;

    @FXML
    private ImageView grid23;

    @FXML
    private ImageView grid24;

    @FXML
    private ImageView grid25;

    @FXML
    private ImageView grid31;

    @FXML
    private ImageView grid32;

    @FXML
    private ImageView grid33;

    @FXML
    private ImageView grid34;

    @FXML
    private ImageView grid35;

    @FXML
    private ImageView grid41;

    @FXML
    private ImageView grid42;

    @FXML
    private ImageView grid43;

    @FXML
    private ImageView grid44;

    @FXML
    private ImageView grid55;

    @FXML
    private ImageView card11;
    
    void setGridtoShark() {
        String imagePath = getClass().getResource("/assets/OOP 2/OOP 2/cards/2.png").toExternalForm();
        card11.setStyle("-fx-image: url('" + imagePath + "');");
        card11.setFitWidth(76);
        card11.setFitHeight(89);
        card11.setLayoutX(40);
        card11.setLayoutY(40);
        card11.setVisible(true);
    }

    DraggableMaker draggableMaker = new DraggableMaker();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGridtoShark();
        draggableMaker.makeDraggable(grid55);
    }
}
