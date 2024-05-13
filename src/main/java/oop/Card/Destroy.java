package oop.Card;

public class Destroy extends Item {

    public Destroy(String pathToImg, String name) {
        super(pathToImg, name);
    }

    @Override
    void use() {
        System.out.println("Destroy");
    }
    
}
