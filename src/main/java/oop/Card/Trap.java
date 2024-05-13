package oop.Card;

public class Trap extends Item {

    public Trap(String pathToImg, String name) {
        super(pathToImg, name);
    }

    @Override
    void use() {
        System.out.println("Trap");
    }
    
}
