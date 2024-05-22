package oop;

import java.net.URL;
import java.util.ArrayList;
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
    private ImageView grid45;

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
    private ImageView activeCard2;

    @FXML
    private ImageView activeCard3;

    @FXML
    private ImageView activeCard4;

    @FXML
    private ImageView activeCard5;

    @FXML
    private ImageView activeCard6;

    @FXML
    private ImageView nextTurnBtn;

    @FXML
    private Button bearAttackButton;

    @FXML
    private Label timerLabel;

    @FXML
    private ImageView LoadPlugin;

    @FXML
    private ImageView LoadState;

    @FXML
    private ImageView SaveState;

    @FXML
    private ImageView toLadangLawan;

    @FXML
    private ImageView toLadangku;

    @FXML
    private ImageView LoadPlugin1;

    @FXML
    private ImageView LoadState1;

    @FXML
    private ImageView SaveState1;

    @FXML
    private ImageView toLadangLawan1;

    @FXML
    private ImageView toLadangku1;

    @FXML
    private ImageView toToko;

    @FXML
    private ImageView toToko1;

    @FXML
    private ImageView PanenBtn;
    
    @FXML
    private ImageView AnimalImage;

    @FXML
    private Label AnimalName;

    @FXML
    private ImageView ContainerPanen;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;
    @FXML
    public ImageView kosong11;

    @FXML
    private ImageView kosong12;

    @FXML
    private ImageView kosong13;

    @FXML
    private ImageView kosong14;

    @FXML
    private ImageView kosong15;

    @FXML
    private ImageView kosong21;

    @FXML
    private ImageView kosong22;

    @FXML
    private ImageView kosong23;

    @FXML
    private ImageView kosong24;

    @FXML
    private ImageView kosong25;

    @FXML
    private ImageView kosong31;

    @FXML
    private ImageView kosong32;

    @FXML
    private ImageView kosong33;

    @FXML
    private ImageView kosong34;

    @FXML
    private ImageView kosong35;

    @FXML
    private ImageView kosong41;

    @FXML
    private ImageView kosong42;

    @FXML
    private ImageView kosong43;

    @FXML
    private ImageView kosong44;

    @FXML
    private ImageView kosong45;

    @FXML
    private ImageView CloseBtn;
 
    @FXML
    private ImageView clickableGrid11;
    
    private Timeline countdownTimeline;

    private ArrayList<String> activeDeckName = new ArrayList<>();
    private String[][] fieldList;
    
    // String imagePath = getClass().getResource("/assets/OOP 2/OOP 2/cards/hiu_darat.png").toExternalForm();

    GlowButtonMaker glowButtonMaker = new GlowButtonMaker();
    DraggableMaker draggableMaker;
    ImageView[][] matrix_grid;
    ImageView[][] matrix_card_in_ladang;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        String imagePath = getClass().getResource("/assets/OOP 2/OOP 2/cards/beruang.png").toExternalForm();
        String imagePath2 = getClass().getResource("/assets/OOP 2/OOP 2/cards/stroberi.png").toExternalForm();
        grid11.setStyle("-fx-image: url('" + imagePath2 + "');");
        grid22.setStyle("-fx-image: url('" + imagePath + "');");
        // setPanenPageVisibility(false);
        glowButtonMaker.setGlow(CloseBtn);
        
        CloseBtn.setOnMouseClicked(event -> setPanenPageVisibility(false));
        PanenBtn.setOnMouseClicked(null);
        
        glowButtonMaker.setGlow(nextTurnBtn);
        glowButtonMaker.setGlow(toLadangLawan);
        toLadangLawan.setOnMouseClicked(event -> {
            toLadangLawan1.setVisible(true);
            toLadangku1.setVisible(false);
            toToko1.setVisible(false);
            SaveState1.setVisible(false);
            LoadPlugin1.setVisible(false);
            LoadState1.setVisible(false);
        });
        glowButtonMaker.setGlow(toLadangku);
        toLadangku.setOnMouseClicked(event -> {
            toLadangku1.setVisible(true);
            toLadangLawan1.setVisible(false);
            toToko1.setVisible(false);
            SaveState1.setVisible(false);
            LoadPlugin1.setVisible(false);
            LoadState1.setVisible(false);
        });
        glowButtonMaker.setGlow(toToko);
        toToko.setOnMouseClicked(event -> {
            toToko1.setVisible(true);
            toLadangLawan1.setVisible(false);
            toLadangku1.setVisible(false);
            SaveState1.setVisible(false);
            LoadPlugin1.setVisible(false);
            LoadState1.setVisible(false);
        });
        glowButtonMaker.setGlow(SaveState);
        SaveState.setOnMouseClicked(event -> {
            SaveState1.setVisible(true);
            toToko1.setVisible(false);
            toLadangLawan1.setVisible(false);
            toLadangku1.setVisible(false);
            LoadPlugin1.setVisible(false);
            LoadState1.setVisible(false);
        });
        glowButtonMaker.setGlow(LoadPlugin);
        LoadPlugin.setOnMouseClicked(event -> {
            LoadPlugin1.setVisible(true);
            toToko1.setVisible(false);
            toLadangLawan1.setVisible(false);
            toLadangku1.setVisible(false);
            SaveState1.setVisible(false);
            LoadState1.setVisible(false);
        });
        glowButtonMaker.setGlow(LoadState);
        LoadState.setOnMouseClicked(event -> {
            LoadState1.setVisible(true);
            toToko1.setVisible(false);
            toLadangLawan1.setVisible(false);
            toLadangku1.setVisible(false);
            SaveState1.setVisible(false);
            LoadPlugin1.setVisible(false);
        });
        
        activeDeckName.add("beruang");
        activeDeckName.add("stroberi");
        activeDeckName.add("beruang");
        activeDeckName.add("stroberi");
        activeDeckName.add("stroberi");
        activeDeckName.add("stroberi");
        
        matrix_grid = new ImageView[][] {
            {grid11, grid12, grid13, grid14, grid15},
            {grid21, grid22, grid23, grid24, grid25},
            {grid31, grid32, grid33, grid34, grid35},
            {grid41, grid42, grid43, grid44, grid45}
        };
        draggableMaker = new DraggableMaker(this);
        fieldList = new String[matrix_grid.length][matrix_grid[0].length];
        
        matrix_card_in_ladang = new ImageView[][] {
            {kosong11, kosong12, kosong13, kosong14, kosong15},
            {kosong21, kosong22, kosong23, kosong24, kosong25},
            {kosong31, kosong32, kosong33, kosong34, kosong35},
            {kosong41, kosong42, kosong43, kosong44, kosong45}
        };
        
        draggableMaker.makeDraggable(activeCard1, matrix_grid, activeDeckName, true);
        draggableMaker.makeDraggable(activeCard2, matrix_grid, activeDeckName, true);
        draggableMaker.makeDraggable(activeCard3, matrix_grid, activeDeckName, true);
        draggableMaker.makeDraggable(activeCard4, matrix_grid, activeDeckName, true);
        draggableMaker.makeDraggable(activeCard5, matrix_grid, activeDeckName, true);
        draggableMaker.makeDraggable(activeCard6, matrix_grid, activeDeckName, true);
        
        // bearAttackButton.setOnAction(event -> applyBearAttackEffect(card11));
        bearAttackButton.setOnAction(event -> simulateBearAttack());
    }
    
    public void onCardUpdated(ImageView card) {
        // clickableGrid11.setOnMouseClicked(event -> System.out.println("click"));
        // card.setOnMouseClicked(event -> System.out.println("click"));
        // draggableMaker.makeDraggable(card, matrix_grid, null);
        // clickableGrid11.setOnMouseClicked(event -> setPanenPageVisibility(true));
        // Handle the updated card
        System.out.println("Card updated: " + card);
        // Access updated activeDeckName and fieldList
        ArrayList<String> updatedDeckNames = draggableMaker.getActiveDeckName();
        String[][] updatedFieldList = draggableMaker.getFieldList();
        // draggableMaker.makeDraggable(card, matrix_grid, updatedDeckNames);
        
        // Print updated activeDeckName
        System.out.println("Updated Active Deck Names: " + updatedDeckNames);
        
        // Print the updated fieldList
        System.out.println("Updated Field List:");
        for (int row = 0; row < updatedFieldList.length; row++) {
            for (int col = 0; col < updatedFieldList[row].length; col++) {
                System.out.print(updatedFieldList[row][col] + " ");
            }
            System.out.println();
        }
    }

    public ImageView getImageViewById(String id) {
        switch (id) {
            case "kosong11": return kosong11;
            case "kosong12": return kosong12;
            case "kosong13": return kosong13;
            case "kosong14": return kosong14;
            case "kosong15": return kosong15;
            case "kosong21": return kosong21;
            case "kosong22": return kosong22;
            case "kosong23": return kosong23;
            case "kosong24": return kosong24;
            case "kosong25": return kosong25;
            case "kosong31": return kosong31;
            case "kosong32": return kosong32;
            case "kosong33": return kosong33;
            case "kosong34": return kosong34;
            case "kosong35": return kosong35;
            case "kosong41": return kosong41;
            case "kosong42": return kosong42;
            case "kosong43": return kosong43;
            case "kosong44": return kosong44;
            case "kosong45": return kosong45;
            default: return null;
        }
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

    private void setPanenPageVisibility(boolean bool) {
        label1.setVisible(bool);
        label2.setVisible(bool);
        label3.setVisible(bool);
        label4.setVisible(bool);
        label5.setVisible(bool);
        label6.setVisible(bool);
        ContainerPanen.setVisible(bool);
        CloseBtn.setVisible(bool);
        PanenBtn.setVisible(bool);
        AnimalImage.setVisible(bool);
        AnimalName.setVisible(bool);
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