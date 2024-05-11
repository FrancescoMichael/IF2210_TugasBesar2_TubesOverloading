package oop;

import javafx.scene.Node;

public class DraggableMaker {

    public void makeDraggable(Node node) {
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
            node.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
            node.setTranslateY(mouseEvent.getSceneY() + dragDelta.y);
        });

        node.setOnMouseReleased(mouseEvent -> {
            node.setTranslateX(0);
            node.setTranslateY(0);
        });
    }
    
    // A helper class to store the delta between the mouse press event and the node position
    class Delta {
        double x, y;
    }
}
