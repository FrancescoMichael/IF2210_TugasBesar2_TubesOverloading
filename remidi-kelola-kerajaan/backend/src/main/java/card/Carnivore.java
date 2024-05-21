package card;
import exceptionkerajaan.*;
import player.*;

public class Carnivore extends Creature implements Animals {
    public Carnivore() {
        super();
    }

    public Carnivore(String name, int price, String pathToImg, Player owner) {
        super(name, price, pathToImg, owner);
    }
    public void eat(CarnivoreFood food) {
        System.out.println("Eating ...");
        
    }

    public void eat(HerbivoreFood food) throws BaseException {
            throw new FoodException(this);

    }

}
