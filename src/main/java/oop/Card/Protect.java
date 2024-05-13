package oop.Card;

public class Protect extends Item {
    
    public Protect(String pathToImg, String name) {
        super(pathToImg, name);
    }

    @Override
    void use() {
        System.out.println("Protect");
    }
}
