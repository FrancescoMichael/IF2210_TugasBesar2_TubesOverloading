package oop.card.creature;

import oop.card.product.CarnivoreFood;
import oop.card.product.HerbivoreFood;
import oop.exceptionkerajaan.BaseException;
import oop.player.*;

public class Omnivore extends Creature implements Animals {

    public Omnivore() {
        super();
    }

    public Omnivore(String name, Player owner) {
        super(name, owner);
    }

    // without owner
    public Omnivore(String name) {
        super(name);
    }

    public void eat(CarnivoreFood food) throws BaseException {
        System.out.println("Eating " + food.getName() + "...");
        this.increaseWeight(food.getAdditionalWeight());
    }

    public void eat(HerbivoreFood food) throws BaseException {
        System.out.println("Eating " + food.getName() + "...");
        this.increaseWeight(food.getAdditionalWeight());

    }

}