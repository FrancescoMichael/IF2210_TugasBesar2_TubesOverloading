package card.creature;

import card.food.*;


public class Herbivore extends Creature implements Animals {
    public void eat(CarnivoreFood food) {
        System.out.println("Can't eat this food !");
    }

    public void eat(HerbivoreFood food) {
        System.out.println("Success !");

    }

}
