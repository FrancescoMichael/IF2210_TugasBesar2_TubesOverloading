package oop.Card;

public class InstantHarvest extends Item {

    public InstantHarvest(String pathToImg, String name) {
        super(pathToImg, name);
    }

    @Override
    void use() {
        System.out.println("InstantHarvest");
    }
    
}
