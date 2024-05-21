package card;
import exceptionkerajaan.BaseException;
import exceptionkerajaan.FoodException;
import player.*;


public class Herbivore extends Creature implements Animals {
    
    public Herbivore() {
        super();
    }

    public Herbivore(String name, int price, String pathToImg, Player owner) {
        super(name, price, pathToImg, owner);
    }
    
    public void eat(CarnivoreFood food) throws BaseException{
        throw new FoodException(this);
    }

    public void eat(HerbivoreFood food) {
        System.out.println("Eating..");
        
    }

}
