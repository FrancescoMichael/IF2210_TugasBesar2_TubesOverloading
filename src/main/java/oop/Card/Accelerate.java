package oop.Card;

public class Accelerate extends Item{
    public Accelerate(String pathToImg, String name) {
        super(pathToImg, name);
    }

    @Override
    void use() {
        System.out.println("Accelerate");
    }
    
}
