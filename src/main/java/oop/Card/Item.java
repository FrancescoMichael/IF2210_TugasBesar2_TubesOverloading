package oop.Card;

public class Item extends Card {

    public Item(String pathToImg, String name) {
        super(pathToImg, name);
    }

    public void use() {
        System.out.println("Item used");
    }
    
}
