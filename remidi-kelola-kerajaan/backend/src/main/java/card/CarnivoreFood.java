package card;

import player.Player;

public class CarnivoreFood extends Product implements Food {

    //// contructors
    public CarnivoreFood() {
        super();
    }

    // without owner
    public CarnivoreFood(String name, int price, String pathToImg, String type, int additionalWeight) {
        super(name, price, pathToImg, type, additionalWeight);
    }

    // with owner
    public CarnivoreFood(String name, int price, String pathToImg, Player owner, String type, int additionalWeight) {
        super(name, price, pathToImg, owner, type, additionalWeight);
    }

    // other methods
    public void beEaten(Animals creature) {
        creature.eat(this);
    }

}
