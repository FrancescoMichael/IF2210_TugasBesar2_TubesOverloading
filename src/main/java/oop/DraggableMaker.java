package oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import oop.exceptionkerajaan.BaseException;
import oop.gamemaster.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import oop.player.Player;

public class DraggableMaker {
    private ImageView lastGlowingCell = null;
    private List<Pane> glowingCells = new ArrayList<>();
    private Timeline timer;

    private String[][] fieldList;
    private ArrayList<String> activeDeckName;

    private FieldController fieldController;

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

    public DraggableMaker(FieldController fieldController) {
        this.fieldController = fieldController;
        this.fieldList = new String[fieldController.matrix_grid.length][fieldController.matrix_grid[0].length];
    }

    private void setGlow(ImageView sourceImageView, boolean glow) {
        if (glow) {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.YELLOW); // Glow color
            dropShadow.setRadius(20);
            dropShadow.setSpread(0.5);
            dropShadow.setBlurType(javafx.scene.effect.BlurType.GAUSSIAN);
            sourceImageView.setEffect(dropShadow);
        } else {
            sourceImageView.setEffect(null);
        }
    }

    public void makeDraggable(ImageView sourceImageView, ImageView[][] grid, ArrayList<String> activeDeckName,
            GameMaster gameMaster) {
        this.activeDeckName = activeDeckName;

        final Delta dragDelta = new Delta();
        final Delta initialPosition = new Delta();
        initialPosition.x = sourceImageView.getLayoutX();
        initialPosition.y = sourceImageView.getLayoutY();

        final double yOffset = 19;

        // Initialize the fieldList with the same dimensions as the grid

        sourceImageView.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = sourceImageView.getTranslateX() - mouseEvent.getSceneX();
            dragDelta.y = sourceImageView.getTranslateY() - mouseEvent.getSceneY();
        });

        sourceImageView.setOnMouseDragged(mouseEvent -> {
            int col = ((int) (mouseEvent.getSceneX() - 34.4) / 100) + 1;
            int row = ((int) (mouseEvent.getSceneY() - 70) / 110) + 1;

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

            sourceImageView.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
            sourceImageView.setTranslateY(mouseEvent.getSceneY() + dragDelta.y);
        });

        sourceImageView.setOnMouseReleased(mouseEvent -> {
            if (lastGlowingCell != null) {

                setGlow(lastGlowingCell, false);

                // Set the card size to match the grid cell
                if (sourceImageView instanceof ImageView) {
                    int col = ((int) (mouseEvent.getSceneX() - 34.4) / 100) + 1;
                    int row = ((int) (mouseEvent.getSceneY() - 70) / 110) + 1;

                    String idSourceImage = sourceImageView.getId();

                    int rowSource = (int) idSourceImage.charAt(idSourceImage.length() - 2) - '0';
                    int colSource = (int) idSourceImage.charAt(idSourceImage.length() - 1) - '0';

                    String className = sourceImageView.getId();
                    char lastChar = className.charAt(className.length() - 1);

                    // Convert the last character to an integer
                    int index = Character.getNumericValue(lastChar) - 1;

                    // Update the fieldList with the card position

                    if (col > 0 && col <= grid[0].length && row > 0 && row <= grid.length) {
                        ImageView targetImageView = fieldController.getImageViewById("kosong" + (row) + (col));

                        // Check if the target cell is empty
                        if (targetImageView.getImage() == null) {
                            Player CurrentPLayer = gameMaster.getCurrentPlayer();
                            System.out.println(rowSource);
                            System.out.println(colSource);
                            if (sourceImageView.getImage() != null) {
                                String sourceImageUrl = sourceImageView.getImage().getUrl();
                                targetImageView.setImage(new Image(sourceImageUrl));
                                sourceImageView.setImage(targetImageView.getImage());
                                sourceImageView.setImage(null);

                                if (idSourceImage.charAt(0) != 'k') {
                                    try {
                                        CurrentPLayer.invokeCard(index, row, col, gameMaster.getCurrentFieldPlayer());
                                        CurrentPLayer.removeCardAtActiveDeck(index);
                                        // this.fieldList[row - 1][col - 1] = activeDeckName.get(index);
                                        // activeDeckName.set(index, "");
                                        // fieldController.onCardUpdated(targetImageView);
                                        makeDraggable(targetImageView, grid, activeDeckName, gameMaster);
                                    } catch(BaseException e) {

                                    }
                                }

                            } else {
                                System.err.println("Error: Source image is null.");
                            }
                        }
                    }

                }

                // Disable dragging
                lastGlowingCell = null;
                sourceImageView.setTranslateX(0);
                sourceImageView.setTranslateY(0);
            } else {
                sourceImageView.setTranslateX(0);
                sourceImageView.setTranslateY(0);
                // If not over a grid cell, reset to the initial position
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

    public void setRedGlow(Node sourceImageView, boolean glow) {
        if (sourceImageView == null) {
            return; // or throw an exception or log an error
        }
        if (glow) {
            sourceImageView.setStyle("-fx-border-color: red; -fx-border-width: 5;");
        } else {
            sourceImageView.setStyle(null);
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