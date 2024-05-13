package oop.Card;

public class Card {
    private String pathToImg;
    private String name;

    public Card(String pathToImg, String name) {
        this.pathToImg = pathToImg;
        this.name = name;
    }

    public String getPathToImg() {
        return pathToImg;
    }

    public String getName() {
        return name;
    }
}
