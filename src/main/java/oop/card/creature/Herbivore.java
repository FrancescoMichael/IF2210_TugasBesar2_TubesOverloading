package oop.card.creature;

import oop.card.product.CarnivoreFood;
import oop.card.product.HerbivoreFood;
import oop.exceptionkerajaan.BaseException;
import oop.exceptionkerajaan.FoodException;
import oop.player.*;

public class Herbivore extends Creature implements Animals {

    // default constructor
    public Herbivore() {
        super();
    }

    // constructor with owner
    public Herbivore(String name, Player owner) {
        super(name, owner);
    }

    // without owner
    public Herbivore(String name) {
        super(name);
    }

    // other methods
    public void eat(CarnivoreFood food) throws BaseException {
        throw new FoodException(this);
    }

    public void eat(HerbivoreFood food) {
        System.out.println("Eating " + food.getName() + "...");
        this.increaseWeight(food.getAdditionalWeight());

    }

}