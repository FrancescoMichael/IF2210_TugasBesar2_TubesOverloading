package oop.card.product;

import oop.player.Player;
import oop.card.UsableCard;
import oop.card.creature.Animals;
import oop.exceptionkerajaan.*;
import oop.card.Card;

public class CarnivoreFood extends Product implements Food, UsableCard {

    //// contructors
    public CarnivoreFood() {
        super();
    }

    // without owner
    public CarnivoreFood(String name, int price, String type, int additionalWeight) {
        super(name, price, type, additionalWeight);
    }

    // with owner
    public CarnivoreFood(String name, int price, Player owner, String type, int additionalWeight) {
        super(name, price, owner, type, additionalWeight);
    }

    public CarnivoreFood(CarnivoreFood copyFood) {
        this.name = copyFood.name;
        this.price = copyFood.price;
        this.pathToImg = copyFood.pathToImg;
        this.owner = copyFood.owner;
        this.type = copyFood.type;
        this.additionalWeight = copyFood.additionalWeight;
    }

    // other methods
    public void beEaten(Animals creature) throws BaseException {
        creature.eat(this);
    }

    public void useCard(Card targetCard, int row, int col) throws BaseException {
        System.out.println("asdasdasdas");
        if (targetCard instanceof Animals && this.getOwner() == targetCard.getOwner()) {
            this.beEaten((Animals) targetCard);
        } else {
            throw new InvalidCardPlacementException();
        }
    }

}
