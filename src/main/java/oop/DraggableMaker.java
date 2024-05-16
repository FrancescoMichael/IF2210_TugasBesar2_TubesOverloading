package oop;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class DraggableMaker {
    private ImageView lastGlowingCell = null;

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
    
        node.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = node.getTranslateX() - mouseEvent.getSceneX();
            dragDelta.y = node.getTranslateY() - mouseEvent.getSceneY();
        });
    
        node.setOnMouseDragged(mouseEvent -> {
            int col = ((int)(mouseEvent.getSceneX() - 34.4) / 100) + 1;
            int row = ((int)(mouseEvent.getSceneY() - 40) / 110) + 1;

            if (col > 0 && col <= grid[0].length && row > 0 && row <= grid.length) {
                ImageView currentCell = grid[row - 1][col - 1];

                // Only change the glow if the cell has changed
                if (lastGlowingCell != currentCell) {
                    if (lastGlowingCell != null) {
                        setGlow(lastGlowingCell, false); // Remove glow from the last cell
                    }
                    setGlow(currentCell, true); // Set glow to the current cell
                    lastGlowingCell = currentCell; // Update the last glowing cell reference
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
                lastGlowingCell = null;
            }
            node.setTranslateX(0);
            node.setTranslateY(0);
        });
    }
    
    // A helper class to store the delta between the mouse press event and the node position
    class Delta {
        double x, y;
    }
}
