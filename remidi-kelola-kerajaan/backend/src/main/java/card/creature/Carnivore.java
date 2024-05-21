package card.creature;
import card.product.CarnivoreFood;
import card.product.HerbivoreFood;
import exceptionkerajaan.*;
import player.*;

public class Carnivore extends Creature implements Animals{
    public Carnivore() {
        super();
    }

    public Carnivore(String name, String pathToImg, Player owner) {
        super(name, pathToImg, owner);
    }
    public void eat(CarnivoreFood food) {
        System.out.println("Eating ...");
        
    }

    public void eat(HerbivoreFood food) throws BaseException {
            throw new FoodException(this);
    }
}
