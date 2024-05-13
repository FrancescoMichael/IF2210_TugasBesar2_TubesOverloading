package oop.Card;

abstract class Item extends Card {

    public Item(String pathToImg, String name) {
        super(pathToImg, name);
    }

    abstract void use(); 
}
