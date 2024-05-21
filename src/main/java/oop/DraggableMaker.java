package oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class DraggableMaker {
    private ImageView lastGlowingCell = null;
    private List<Pane> glowingCells = new ArrayList<>();
    private Timeline timer;

    private String[][] fieldList;
    private ArrayList<String> activeDeckName;

    public String[][] getFieldList() {
        return fieldList;
    }

    public ArrayList<String> getActiveDeckName() {
        return activeDeckName;
    }

    public interface CardUpdateListener {
        void onCardUpdated(ImageView card);
    }

    private CardUpdateListener cardUpdateListener;

    public void setCardUpdateListener(CardUpdateListener listener) {
        this.cardUpdateListener = listener;
    }

    private void setGlow(Node node, boolean glow) {
        if (glow) {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.YELLOW); // Glow color
            dropShadow.setRadius(20);
            dropShadow.setSpread(0.5);
            dropShadow.setBlurType(javafx.scene.effect.BlurType.GAUSSIAN);
            node.setEffect(dropShadow);
        } else {
            node.setEffect(null);
        }
    }
    
    public void makeDraggable(Node node, ImageView[][] grid, ArrayList<String> activeDeckName) {
        this.activeDeckName = activeDeckName;

        final Delta dragDelta = new Delta();
        final Delta initialPosition = new Delta();
        initialPosition.x = node.getLayoutX();
        initialPosition.y = node.getLayoutY();

        final double yOffset = 19;

        // Initialize the fieldList with the same dimensions as the grid
        fieldList = new String[grid.length][grid[0].length];

        node.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = node.getTranslateX() - mouseEvent.getSceneX();
            dragDelta.y = node.getTranslateY() - mouseEvent.getSceneY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            int col = ((int)(mouseEvent.getSceneX() - 34.4) / 100) + 1;
            int row = ((int)(mouseEvent.getSceneY() - 70) / 110) + 1;

            if (col > 0 && col <= grid[0].length && row > 0 && row <= grid.length) {
                ImageView currentCell = grid[row - 1][col - 1];

                // Only change the glow if the cell has changed
                if (lastGlowingCell != currentCell) {
                    if (lastGlowingCell != null) {
                        setGlow(lastGlowingCell, false);
                    }
                    setGlow(currentCell, true);
                    lastGlowingCell = currentCell;
                }
            } else {
                if (lastGlowingCell != null) {
                    setGlow(lastGlowingCell, false);
                    lastGlowingCell = null;
                }
            }

            node.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
            node.setTranslateY(mouseEvent.getSceneY() + dragDelta.y);
        });

        node.setOnMouseReleased(mouseEvent -> {
            if (lastGlowingCell != null) {
                setGlow(lastGlowingCell, false);

                // Snap the card to the grid cell
                node.setLayoutX(lastGlowingCell.getLayoutX());
                node.setLayoutY(lastGlowingCell.getLayoutY());
                node.setTranslateX(0);
                node.setTranslateY(0);

                // Set the card size to match the grid cell
                if (node instanceof ImageView) {
                    ImageView card = (ImageView) node;
                    card.setFitWidth(lastGlowingCell.getFitWidth());
                    card.setFitHeight(lastGlowingCell.getFitHeight());
                    if (cardUpdateListener != null) {
                        cardUpdateListener.onCardUpdated(card);
                    }

                    // print active deck name content
                    for (String name : activeDeckName) {
                        System.out.println(name);
                    }

                    String className = node.getId();
                    char lastChar = className.charAt(className.length() - 1);

                    // Convert the last character to an integer
                    int index = Character.getNumericValue(lastChar) - 1;
                    System.out.println(index);

                    // Update the fieldList with the card position
                    int col = ((int)(mouseEvent.getSceneX() - 34.4) / 100) + 1;
                    int row = ((int)(mouseEvent.getSceneY() - 70) / 110) + 1;
                    
                    fieldList[row - 1][col - 1] = activeDeckName.get(index);

                    activeDeckName.set(index, "");

                    // Print the matrix
                    printMatrix();
                }

                // Disable dragging
                lastGlowingCell = null;
            } else {
                // If not over a grid cell, reset to the initial position
                node.setTranslateX(0);
                node.setTranslateY(0);
            }
        });
    }

    private void printMatrix() {
        for (int row = 0; row < fieldList.length; row++) {
            for (int col = 0; col < fieldList[row].length; col++) {
                if (fieldList[row][col] != null) {
                    System.out.print("[" + fieldList[row][col] + "]");
                } else {
                    System.out.print("[ ] ");
                }
            }
            System.out.println();
        }
    }
    
    
    class Delta {
        double x, y;
    }

    public void setRedGlow(Node node, boolean glow) {
        if (node == null) {
            return; // or throw an exception or log an error
        }
        if (glow) {
            node.setStyle("-fx-border-color: red; -fx-border-width: 5;");
        } else {
            node.setStyle(null);
        }
    }
    

    public void setRedGlowOnRandomGroup(Pane[][] grid, int rows, int cols) {
        if (timer != null) {
            timer.stop();
            for (Pane cell : glowingCells) {
                setRedGlow(cell, false);
            }
            glowingCells.clear();
        }

        Random random = new Random();

        int gridRows = grid.length;
        int gridCols = grid[0].length;

        // Randomly choose a starting position for the 2x3 area
        int startRow = random.nextInt(gridRows - rows + 1);
        int startCol = random.nextInt(gridCols - cols + 1);

        // Add the cards in the chosen 2x3 area to the list
        for (int i = startRow; i < startRow + rows; i++) {
            for (int j = startCol; j < startCol + cols; j++) {
                Pane cell = grid[i][j];
                glowingCells.add(cell);
                setRedGlow(cell, true);
            }
        }

        timer = new Timeline(new KeyFrame(Duration.seconds(30), event -> {
            for (Pane cell : glowingCells) {
                setRedGlow(cell, false);
            }
            glowingCells.clear();
        }));
        timer.setCycleCount(1);
        timer.play();
    }
}