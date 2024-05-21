package card.creature;

import card.food.*;
import exceptionkerajaan.BaseException;
import exceptionkerajaan.FoodException;


public class Herbivore extends Creature implements Animals {
    public void eat(CarnivoreFood food) throws BaseException{
        throw new FoodException(this);
    }

    public void eat(HerbivoreFood food) {
        System.out.println("Eating..");
        
    }

}
