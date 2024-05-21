package card.product;

import player.Player;
import card.UsableCard;
import card.creature.Animals;
import exceptionkerajaan.*;
import card.Card;

public class CarnivoreFood extends Product implements Food, UsableCard {

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
    public void beEaten(Animals creature) throws BaseException{
        creature.eat(this);
    }

    public void useCard(Card targetCard,int row, int col) throws BaseException{
        
    }


}
