package oop.Card;

public class Product extends Card {

    private int gulden;
    private int addedWeight;

    public Product(String pathToImg, String name) {
        super(pathToImg, name);
    }

    public Product(String pathToImg, String name, int gulden, int addedWeight) {
        super(pathToImg, name);
        this.gulden = gulden;
        this.addedWeight = addedWeight;
    }

    public int getGulden() {
        return gulden;
    }

    public int getAddedWeight() {
        return addedWeight;
    }
    
}
