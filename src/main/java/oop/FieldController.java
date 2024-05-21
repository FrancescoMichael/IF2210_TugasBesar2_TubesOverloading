package oop;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.fxml.Initializable;
import javafx.util.Duration;

public class FieldController implements Initializable, DraggableMaker.CardUpdateListener{

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
    private Pane plane11;

    @FXML
    private Pane plane12;

    @FXML
    private Pane plane13;

    @FXML
    private Pane plane14;

    @FXML
    private Pane plane15;

    @FXML
    private Pane plane21;

    @FXML
    private Pane plane22;

    @FXML
    private Pane plane23;

    @FXML
    private Pane plane24;

    @FXML
    private Pane plane25;

    @FXML
    private Pane plane31;

    @FXML
    private Pane plane32;

    @FXML
    private Pane plane33;

    @FXML
    private Pane plane34;

    @FXML
    private Pane plane35;

    @FXML
    private Pane plane41;

    @FXML
    private Pane plane42;

    @FXML
    private Pane plane43;

    @FXML
    private Pane plane44;

    @FXML
    private Pane plane45;

    @FXML
    private ImageView activeCard1;

    @FXML
    private ImageView nextTurnBtn;

    @FXML
    private Button bearAttackButton;

    @FXML
    private Label timerLabel;
    
    private Timeline countdownTimeline;
    
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
        
        draggableMaker.setCardUpdateListener(this);
        draggableMaker.makeDraggable(grid12, matrix_grid);
        draggableMaker.makeDraggable(activeCard1, matrix_grid);

        // bearAttackButton.setOnAction(event -> applyBearAttackEffect(card11));
        bearAttackButton.setOnAction(event -> simulateBearAttack());
    }

    @Override
    public void onCardUpdated(ImageView card) {
        // Handle the updated card
        System.out.println("Card updated: " + card);
    }

    @FXML
    private void simulateBearAttack() {
        Pane[][] matrix_pane = new Pane[][] {
            {plane11, plane12, plane13, plane14, plane15},
            {plane21, plane22, plane23, plane24, plane25},
            {plane31, plane32, plane33, plane34, plane35},
            {plane41, plane42, plane43, plane44, plane45}
        };
    
        for (Pane[] row : matrix_pane) {
            for (Pane pane : row) {
                if (pane == null) {
                    System.err.println("Pane is null in matrix_pane");
                }
            }
        }
    
        draggableMaker.setRedGlowOnRandomGroup(matrix_pane, 2, 3);
        startCountdown();
    }
    

    private void startCountdown() {
        if (countdownTimeline != null) {
            countdownTimeline.stop();
        }

        countdownTimeline = new Timeline(
            new KeyFrame(Duration.seconds(0), event -> timerLabel.setText("Timer: 30")),
            new KeyFrame(Duration.seconds(1), event -> timerLabel.setText("Timer: 29")),
            new KeyFrame(Duration.seconds(2), event -> timerLabel.setText("Timer: 28")),
            new KeyFrame(Duration.seconds(3), event -> timerLabel.setText("Timer: 27")),
            new KeyFrame(Duration.seconds(4), event -> timerLabel.setText("Timer: 26")),
            new KeyFrame(Duration.seconds(5), event -> timerLabel.setText("Timer: 25")),
            new KeyFrame(Duration.seconds(6), event -> timerLabel.setText("Timer: 24")),
            new KeyFrame(Duration.seconds(7), event -> timerLabel.setText("Timer: 23")),
            new KeyFrame(Duration.seconds(8), event -> timerLabel.setText("Timer: 22")),
            new KeyFrame(Duration.seconds(9), event -> timerLabel.setText("Timer: 21")),
            new KeyFrame(Duration.seconds(10), event -> timerLabel.setText("Timer: 20")),
            new KeyFrame(Duration.seconds(11), event -> timerLabel.setText("Timer: 19")),
            new KeyFrame(Duration.seconds(12), event -> timerLabel.setText("Timer: 18")),
            new KeyFrame(Duration.seconds(13), event -> timerLabel.setText("Timer: 17")),
            new KeyFrame(Duration.seconds(14), event -> timerLabel.setText("Timer: 16")),
            new KeyFrame(Duration.seconds(15), event -> timerLabel.setText("Timer: 15")),
            new KeyFrame(Duration.seconds(16), event -> timerLabel.setText("Timer: 14")),
            new KeyFrame(Duration.seconds(17), event -> timerLabel.setText("Timer: 13")),
            new KeyFrame(Duration.seconds(18), event -> timerLabel.setText("Timer: 12")),
            new KeyFrame(Duration.seconds(19), event -> timerLabel.setText("Timer: 11")),
            new KeyFrame(Duration.seconds(20), event -> timerLabel.setText("Timer: 10")),
            new KeyFrame(Duration.seconds(21), event -> timerLabel.setText("Timer: 9")),
            new KeyFrame(Duration.seconds(22), event -> timerLabel.setText("Timer: 8")),
            new KeyFrame(Duration.seconds(23), event -> timerLabel.setText("Timer: 7")),
            new KeyFrame(Duration.seconds(24), event -> timerLabel.setText("Timer: 6")),
            new KeyFrame(Duration.seconds(25), event -> timerLabel.setText("Timer: 5")),
            new KeyFrame(Duration.seconds(26), event -> timerLabel.setText("Timer: 4")),
            new KeyFrame(Duration.seconds(27), event -> timerLabel.setText("Timer: 3")),
            new KeyFrame(Duration.seconds(28), event -> timerLabel.setText("Timer: 2")),
            new KeyFrame(Duration.seconds(29), event -> timerLabel.setText("Timer: 1")),
            new KeyFrame(Duration.seconds(30), event -> timerLabel.setText("Timer: 0"))
        );
        countdownTimeline.setCycleCount(1);
        countdownTimeline.play();
    }
}