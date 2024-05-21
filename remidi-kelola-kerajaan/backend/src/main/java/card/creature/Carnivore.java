package card.creature;

import card.food.CarnivoreFood;
import card.food.HerbivoreFood;
import exceptionkerajaan.BaseException;
import exceptionkerajaan.FoodException;

public class Carnivore extends Creature implements Animals {
    public void eat(CarnivoreFood food) {
        System.out.println("Eating ...");
        
    }

    public void eat(HerbivoreFood food) throws BaseException {
            throw new FoodException(this);

    }

}
