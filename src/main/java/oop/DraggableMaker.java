package oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import oop.exceptionkerajaan.BaseException;
import oop.gamemaster.*;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import oop.player.Player;

public class DraggableMaker {
    private ImageView lastGlowingCell = null;
    private List<Pane> glowingCells = new ArrayList<>();
    private Timeline timer;
    private boolean wasDragged;

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

    public DraggableMaker(FieldController fieldController) {
        this.fieldController = fieldController;
        this.fieldList = new String[fieldController.matrixGrid.length][fieldController.matrixGrid[0].length];
    }

    public void removeRedGlow(Pane[][] grid, int rows, int cols){
        // System.out.println("REMOVE RED");
        // System.out.println(rows);
        // System.out.println(cols);
        for (int row = 0 ; row < rows ; row++){
            for (int col = 0 ; col < cols ; cols++){
                setRedGlow(grid[row][col], false);
            }
        }
        // glowingCells.clear();
    }
    private void setGlow(ImageView sourceImageView, boolean glow) {
        if (sourceImageView != null) {
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
    }

    public void makeDraggable(ImageView sourceImageView, ImageView[][] matrix_grid, GameMaster gameMaster,
            boolean isInField) {
        final Delta dragDelta = new Delta();
        final Delta initialPosition = new Delta();
        initialPosition.x = sourceImageView.getLayoutX();
        initialPosition.y = sourceImageView.getLayoutY();

        sourceImageView.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = sourceImageView.getTranslateX() - mouseEvent.getSceneX();
            dragDelta.y = sourceImageView.getTranslateY() - mouseEvent.getSceneY();
        });

        if (isInField) {
            // fieldController.glowButtonMaker.setGlow(sourceImageView);
            sourceImageView.setOnMouseClicked(event -> {
                if (!wasDragged && !fieldController.isInEnemyField()) {
                    
                    int row = ((int) (event.getSceneY() - 70) / 100) + 1;
                    int col = ((int) (event.getSceneX() - 34.4) / 100) + 1;

                    fieldController.getPanenBtn().setOnMouseClicked(e -> {
                        try {
                            gameMaster.getCurrentFieldPlayer().getCardGrid(row - 1, col - 1).harvestCreature(row - 1, col - 1);
                            fieldController.loadGridActiveDeck();
                            fieldController.setPanenPageVisibility(false);
                        } catch (Exception exception) {
                            // TODO: handle exception
                        }
                    });

                    // fieldController.creatureClicked = gameMaster.getCurrentFieldPlayer().getCardGrid(row - 1, col - 1);

                    Image currentImage = sourceImageView.getImage();
                    String currentImageUrl = currentImage.getUrl();

                    // Edit the image URL (assuming you want to change the image name or path)
                    String newImageUrl = currentImageUrl.replace("cards", "icons");

                    // Set the new image to the sourceImageView
                    fieldController.getAnimalImage().setImage(new Image(newImageUrl));
                    // System.out.println(gameMaster.getCurrentFieldPlayer().getName());

                    fieldController.setAllLabel(row - 1, col - 1);
                    fieldController.setPanenPageVisibility(true);

                }
                wasDragged = false;
            });
        }

        sourceImageView.setOnMouseDragged(mouseEvent -> {
            wasDragged = true;
            int row = ((int) (mouseEvent.getSceneY() - 70) / 100) + 1;
            int col = ((int) (mouseEvent.getSceneX() - 34.4) / 100) + 1;

            if (col > 0 && col <= matrix_grid[0].length && row > 0 && row <= matrix_grid.length) {
                ImageView currentCell = matrix_grid[row - 1][col - 1];

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
                    int row = ((int) (mouseEvent.getSceneY() - 70) / 100) + 1;
                    int col = ((int) (mouseEvent.getSceneX() - 34.4) / 100) + 1;

                    String idSourceImage = sourceImageView.getId();

                    int rowSource = (int) idSourceImage.charAt(idSourceImage.length() - 2) - '0';
                    int colSource = (int) idSourceImage.charAt(idSourceImage.length() - 1) - '0';

                    String className = sourceImageView.getId();
                    char lastChar = className.charAt(className.length() - 1);

                    // Convert the last character to an integer
                    int index = Character.getNumericValue(lastChar) - 1;

                    if (col > 0 && col <= matrix_grid[0].length && row > 0 && row <= matrix_grid.length) {
                        ImageView targetImageView = fieldController.getImageViewById("kosong" + (row) + (col));

                        // Check if the target cell is empty
                        Player CurrentPLayer = gameMaster.getCurrentPlayer();
                        if (true) {
                            if (sourceImageView.getImage() != null) {
                                String sourceImageUrl = sourceImageView.getImage().getUrl();
                                
                                if (idSourceImage.charAt(0) != 'k') {
                                    try {
                                        CurrentPLayer.invokeCard(index, row - 1, col - 1,
                                        gameMaster.getCurrentFieldPlayer());
                                        // System.out.println("USING CARC");
                                        // CurrentPLayer.removeCardAtActiveDeck(index);

                                        fieldController.loadGridActiveDeck();
                                        
                                        makeDraggable(targetImageView, matrix_grid, gameMaster, true);
                                    } catch (BaseException e) {
                                        System.out.println(e.getMessage());
                                    }
                                } else {
                                    try {
                                        // System.out.println("HERE");
                                        // System.out.println("MOVE CARD GRID TO GRID");
                                        CurrentPLayer.moveCardGridtoGrid(rowSource - 1, colSource - 1, row - 1, col - 1, CurrentPLayer);
                                        fieldController.loadGridActiveDeck();
                                        makeDraggable(targetImageView, matrix_grid, gameMaster, true);
                                    } catch (BaseException e) {
                                        System.out.println(e.getMessage());
                                    }

                                }

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

            new Timeline(new KeyFrame(Duration.millis(50), e -> wasDragged = false)).play();
        });
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

    public Integer[] setRedGlowOnRandomGroup(Pane[][] grid) {
        Integer temp[] = new Integer[4];
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
        List<Integer> rowsPossible = new ArrayList<>(List.of(1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4));
        List<Integer> colsPossible = new ArrayList<>(List.of(1, 2, 3, 4, 5, 1, 2, 3, 1, 2, 1));
        // rows =4;
        // cols = 5;
        int randomNumber = random.nextInt(11);
        int rows = rowsPossible.get(randomNumber);
        int cols = colsPossible.get(randomNumber);
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
        temp[0] = startRow;
        temp[1] = startCol;
        temp[2] = rows;
        temp[3] = cols;

        return temp;

        // timer = new Timeline(new KeyFrame(Duration.seconds(30), event -> {
        //     for (Pane cell : glowingCells) {
        //         setRedGlow(cell, false);
        //     }
        //     glowingCells.clear();
        // }));
        // timer.setCycleCount(1);
        // timer.play();
    }

    public void removeGlowAll(){
        for (Pane cell : glowingCells) {
            setRedGlow(cell, false);
        }
        glowingCells.clear();
    }
}