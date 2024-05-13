package oop.Card;

public class Delay extends Item {

    public Delay(String pathToImg, String name) {
        super(pathToImg, name);
    }

    @Override
    void use() {
        System.out.println("Delay");
    }
    
}
