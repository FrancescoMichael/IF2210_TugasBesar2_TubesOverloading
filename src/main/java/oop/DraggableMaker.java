package oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class DraggableMaker {
    private ImageView lastGlowingCell = null;
    private List<ImageView> glowingCells = new ArrayList<>();
    private Timeline timer;

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
    
    public void makeDraggable(Node node, ImageView[][] grid) {
        final Delta dragDelta = new Delta();
        final Delta initialPosition = new Delta();
        initialPosition.x = node.getLayoutX();
        initialPosition.y = node.getLayoutY();
    
        // custom size
        final double customWidth = 73; 
        final double customHeight = 120; 
    
        final double yOffset = 18;
    
        node.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = node.getTranslateX() - mouseEvent.getSceneX();
            dragDelta.y = node.getTranslateY() - mouseEvent.getSceneY();
        });
    
        node.setOnMouseDragged(mouseEvent -> {
            node.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
            node.setTranslateY(mouseEvent.getSceneY() + dragDelta.y);
    
            // Calculate grid position
            int col = (int) ((mouseEvent.getSceneX() - 48) / 100);
            int row = (int) ((mouseEvent.getSceneY() - 45) / 110);
    
            if (col >= 0 && col < grid[0].length && row >= 0 && row < grid.length) {
                ImageView currentCell = grid[row][col];
    
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
        });
    
        node.setOnMouseReleased(mouseEvent -> {
            if (lastGlowingCell != null) {
                setGlow(lastGlowingCell, false);
    
                // Snap the card to the grid cell
                double cellCenterX = lastGlowingCell.getLayoutX() + lastGlowingCell.getFitWidth() / 2;
                double cellCenterY = lastGlowingCell.getLayoutY() + lastGlowingCell.getFitHeight() / 2;
                node.setLayoutX(cellCenterX - customWidth / 2);
                node.setLayoutY(cellCenterY - customHeight / 2 + yOffset);
                node.setTranslateX(0);
                node.setTranslateY(0);
    
                // Set custom size for the card
                if (node instanceof ImageView) {
                    ImageView card = (ImageView) node;
                    card.setFitWidth(customWidth);
                    card.setFitHeight(customHeight);
                }
    
                // Disable dragging
                node.setOnMousePressed(null);
                node.setOnMouseDragged(null);
                node.setOnMouseReleased(null);
    
                lastGlowingCell = null;
            } else {
                // If not over a grid cell, reset to the initial position
                node.setTranslateX(0);
                node.setTranslateY(0);
            }
        });
    }
    
    class Delta {
        double x, y;
    }

    public void setRedGlow(Node node, boolean glow) {
        if (glow) {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.RED); // Glow color
            dropShadow.setRadius(20);
            dropShadow.setSpread(0.5);
            dropShadow.setBlurType(javafx.scene.effect.BlurType.GAUSSIAN);
            node.setEffect(dropShadow);
        } else {
            node.setEffect(null);
        }
    }

    public void setRedGlowOnRandomGroup(ImageView[][] grid, int rows, int cols) {
        if (timer != null) {
            timer.stop();
            for (ImageView cell : glowingCells) {
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
                ImageView cell = grid[i][j];
                glowingCells.add(cell);
                setRedGlow(cell, true);
            }
        }

        timer = new Timeline(new KeyFrame(Duration.seconds(30), event -> {
            for (ImageView cell : glowingCells) {
                setRedGlow(cell, false);
            }
            glowingCells.clear();
        }));
        timer.setCycleCount(1);
        timer.play();
    }
}
