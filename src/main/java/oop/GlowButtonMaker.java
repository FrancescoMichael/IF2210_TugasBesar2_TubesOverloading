package oop;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class GlowButtonMaker {
    public void setGlow(Node node) {
        node.setOnMouseClicked(mouseEvent -> {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.GREEN); // Glow color
            dropShadow.setRadius(40);
            dropShadow.setSpread(0.5);
            dropShadow.setBlurType(javafx.scene.effect.BlurType.GAUSSIAN);
            node.setEffect(dropShadow);
            
            Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(80), // Delay of half a second
                event -> {
                    DropShadow grayGlow = new DropShadow();
                    grayGlow.setColor(Color.GRAY); // Glow color for hover
                    grayGlow.setRadius(20);
                    grayGlow.setSpread(0.5);
                    grayGlow.setBlurType(javafx.scene.effect.BlurType.GAUSSIAN);
                    node.setEffect(grayGlow);
                }
            ));
            timeline.setCycleCount(1); // Run the timeline only once
            timeline.play();
        });

        node.setOnMousePressed(mouseEvent -> {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.GREEN); // Glow color
            dropShadow.setRadius(40);
            dropShadow.setSpread(0.5);
            dropShadow.setBlurType(javafx.scene.effect.BlurType.GAUSSIAN);
            node.setEffect(dropShadow);
        });

        node.setOnMouseReleased(mouseEvent -> {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.GRAY); // Glow color
            dropShadow.setRadius(20);
            dropShadow.setSpread(0.5);
            dropShadow.setBlurType(javafx.scene.effect.BlurType.GAUSSIAN);
            node.setEffect(dropShadow);
        });

        node.setOnMouseEntered(mouseEvent -> {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.GRAY); // Glow color
            dropShadow.setRadius(20);
            dropShadow.setSpread(0.5);
            dropShadow.setBlurType(javafx.scene.effect.BlurType.GAUSSIAN);
            node.setEffect(dropShadow);
        });

        node.setOnMouseExited(mouseEvent -> {
            node.setEffect(null);
        });
    }
}
