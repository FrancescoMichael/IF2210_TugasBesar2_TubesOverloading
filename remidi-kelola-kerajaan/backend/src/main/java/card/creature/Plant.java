package card.creature;
import player.Player;

public class Plant extends Creature {

    // constructors
    public Plant() {

    }

    public Plant(String name, int price, String pathToImg, Player owner) {
        super(name, price, pathToImg, owner);
    }

}
