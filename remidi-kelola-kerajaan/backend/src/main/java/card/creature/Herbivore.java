package card.creature;
import card.product.CarnivoreFood;
import card.product.HerbivoreFood;
import exceptionkerajaan.BaseException;
import exceptionkerajaan.FoodException;
import player.*;


public class Herbivore extends Creature implements Animals{
    
    // default constructor
    public Herbivore() {
        super();
    }
    // constructor with owner
    public Herbivore(String name, String pathToImg, Player owner) {
        super(name, pathToImg, owner);
    }

    // without owner
    public Herbivore(String name, String pathToImg) {
        super(name, pathToImg);
    }

    // other methods
    public void eat(CarnivoreFood food) throws BaseException{
        throw new FoodException(this);
    }

    public void eat(HerbivoreFood food) {
        System.out.println("Eating..");
        this.increaseWeight(food.getAdditionalWeight());
        
    }

}
