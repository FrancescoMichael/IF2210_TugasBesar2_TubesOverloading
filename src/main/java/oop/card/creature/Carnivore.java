package oop.card.creature;
import oop.card.product.CarnivoreFood;
import oop.card.product.HerbivoreFood;
import oop.exceptionkerajaan.*;
import oop.player.*;

public class Carnivore extends Creature implements Animals{

    // Constructors
    public Carnivore() {
        super();
    }
    // with owner
    public Carnivore(String name, String pathToImg, Player owner) {
        super(name, pathToImg, owner);
    }

    // without owner
    public Carnivore(String name, String pathToImg) {
        super(name, pathToImg);
    }
    
    public void eat(CarnivoreFood food) {
        System.out.println("Eating " + food.getName() + "..." ); 
        this.increaseWeight(food.getAdditionalWeight()); 
    }

    public void eat(HerbivoreFood food) throws BaseException {
            throw new FoodException(this);
    }
}
