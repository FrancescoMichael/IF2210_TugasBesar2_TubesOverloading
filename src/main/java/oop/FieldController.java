package oop;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
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

    @FXML
    private ImageView activeCard1;

    @FXML
    private ImageView nextTurnBtn;

    @FXML
    private Button bearAttackButton;
    
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
    GlowButtonMaker glowButtonMaker = new GlowButtonMaker();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGridtoShark();

        glowButtonMaker.setGlow(nextTurnBtn);

        // nextTurnBtn.setOnAction(event -> {
        //     System.out.println("Button clicked!");
        // });

        ImageView[][] matrix_grid = new ImageView[][] {
            {grid11, grid12, grid13, grid14, grid15},
            {grid21, grid22, grid23, grid24, grid25},
            {grid31, grid32, grid33, grid34, grid35},
            {grid41, grid42, grid43, grid44, grid55}
        };
        
        draggableMaker.makeDraggable(activeCard1, matrix_grid);

        bearAttackButton.setOnAction(event -> applyBearAttackEffect(card11));
    }

    public void applyBearAttackEffect(ImageView card) {
        draggableMaker.setRedGlow(card, true);
    }

    @FXML
    private void simulateBearAttack() {
        applyBearAttackEffect(grid11); // Replace grid11 with the actual card being attacked
    }
}
